package br.com.validator.service;

import br.com.validator.infra.dto.CustoRequisicaoDTO;
import br.com.validator.infra.dto.RequisicaoConsultaDTO;
import br.com.validator.infra.dto.RequisicaoDTO;
import br.com.validator.infra.dto.RequisicaoPrecoDTO;
import br.com.validator.infra.entity.Requisicao;
import br.com.validator.infra.entity.RequisicaoPreco;
import br.com.validator.infra.entity.Usuario;
import br.com.validator.infra.exception.BadRequestException;
import br.com.validator.infra.jdbc.JdbcRequisicao;
import br.com.validator.infra.repository.RequisicaoPrecoRepository;
import br.com.validator.infra.repository.RequisicaoRepository;
import br.com.validator.infra.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.validator.infra.util.Util.*;
import static java.util.Objects.isNull;

@Service
public class ValidatorService {

    private final RequisicaoPrecoRepository requisicaoPrecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RequisicaoRepository requisicaoRepository;
    private final JdbcRequisicao jdbcRequisicao;

    @Autowired
    public ValidatorService(RequisicaoPrecoRepository requisicaoPrecoRepository,
                            UsuarioRepository usuarioRepository,
                            RequisicaoRepository requisicaoRepository,
                            JdbcRequisicao jdbcRequisicao){
        this.requisicaoPrecoRepository = requisicaoPrecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.requisicaoRepository = requisicaoRepository;
        this.jdbcRequisicao = jdbcRequisicao;
    }

    public List<RequisicaoPrecoDTO> findAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<RequisicaoPreco> requisicaoPreco = requisicaoPrecoRepository.findAll();
        List<RequisicaoPrecoDTO> result = new ArrayList<>();
        if (!requisicaoPreco.isEmpty()){
            for (RequisicaoPreco req : requisicaoPreco) {
                result.add(modelMapper.map(req, RequisicaoPrecoDTO.class));
            }
        }
        return result;
    }

    public boolean requisicao(RequisicaoDTO requisicaoDTO){

        requisicaoDTOValidator(requisicaoDTO);

        requisicaoDTO.setNumDoc(requisicaoDTO.getNumDoc().replaceAll("[-.//]", ""));

        Usuario usuario = usuarioRepository.findByCliente(requisicaoDTO.getCliente());

        if(usuario == null){
            usuario = new Usuario();
            usuario.setCliente(requisicaoDTO.getCliente());
            usuario.setDataCadastro(new Date());

            try {
                usuarioRepository.save(usuario);
            }catch (Exception e){
                throw new BadRequestException("system.error", e.getLocalizedMessage());
            }
        }

        RequisicaoPreco requisicaoPreco = requisicaoPrecoRepository.findByDataFimIsNull();

        if(isNull(requisicaoPreco)){
            throw new BadRequestException("requisicao.preco.null");
        }

        boolean resultado = false;

         resultado = requisicaoDTO.getNumDoc().length() == 11 ? isCPF(requisicaoDTO.getNumDoc()) : isCNPJ(requisicaoDTO.getNumDoc());

        Requisicao requisicao = new Requisicao();
        requisicao.setIdUsuario(usuario);
        requisicao.setIdRequisicaoPreco(requisicaoPreco);
        requisicao.setNumdoc(requisicaoDTO.getNumDoc());
        requisicao.setResultado( resultado ? 1 : 0);
        requisicao.setDataCadastro(new Date());

        try {
            requisicaoRepository.save(requisicao);
        }catch (Exception e){
            throw new BadRequestException("system.error", e.getLocalizedMessage());
        }

        return resultado;

    }

    public Map<String, Set<CustoRequisicaoDTO>> consultaRequisicao(Integer mes){
        List<RequisicaoConsultaDTO> retorno = jdbcRequisicao.consultaRequisicoes(mes);
        return
                retorno.stream().collect(
                        Collectors.groupingBy(RequisicaoConsultaDTO::getCliente,
                                Collectors.mapping(RequisicaoConsultaDTO::getCustoRequisicaoDTO, Collectors.toSet())
                        )
                );
    }

}
