package br.com.validator.infra.jdbc;

import br.com.validator.infra.dto.CustoRequisicaoDTO;
import br.com.validator.infra.dto.RequisicaoConsultaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcRequisicao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RequisicaoConsultaDTO> consultaRequisicoes(Integer mes) {
        try {

            StringBuilder query = new StringBuilder()
                    .append("select rp.preco, usu.cliente, count (rp.preco) qtd from requisicao r inner join ")
                    .append("requisicao_preco rp on r.id_requisicao_preco = rp.id_requisicao_preco inner join ")
                    .append("usuario usu on r.id_usuario = usu.id_usuario ")
                    .append("where extract(month from r.data_cadastro) = ?")
                    .append("GROUP by r.num_doc, rp.preco, usu.cliente ");

            return jdbcTemplate.query(query.toString(), new Object[]{mes}, (resultSet, i) -> {
                    RequisicaoConsultaDTO dto = new RequisicaoConsultaDTO();
                    dto.setCliente(resultSet.getString("CLIENTE"));
                    CustoRequisicaoDTO custoRequisicaoDTO = new CustoRequisicaoDTO();
                    custoRequisicaoDTO.setPreco(resultSet.getString("PRECO"));
                    custoRequisicaoDTO.setQtd(resultSet.getInt("QTD"));
                    dto.setCustoRequisicaoDTO(custoRequisicaoDTO);
                return dto;
            } );

        } catch (Exception e) {
            LOG.error("Erro buscando requisições: {} ", e.getMessage());
            return Collections.emptyList();
        }
    }
}
