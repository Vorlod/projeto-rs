package br.com.validator.controller;

import br.com.validator.infra.dto.CustoRequisicaoDTO;
import br.com.validator.infra.dto.RequisicaoConsultaDTO;
import br.com.validator.infra.dto.RequisicaoDTO;
import br.com.validator.infra.dto.RequisicaoPrecoDTO;
import br.com.validator.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ValidatorController {

    private final ValidatorService validatorService;

    @Autowired
    public ValidatorController(ValidatorService validatorService){
        this.validatorService = validatorService;
    }


    @GetMapping("requisicao-preco")
    public List<RequisicaoPrecoDTO> findAll(){
        return validatorService.findAll();
    }

    @PostMapping("requisicao")
    public boolean requisicao(@RequestBody RequisicaoDTO requisicaoDTO){
        return validatorService.requisicao(requisicaoDTO);
    }

    @GetMapping("requisicao-mes/{mes}")
    public Map<String, Set<CustoRequisicaoDTO>> requisicaoMes(@PathVariable Integer mes){
        return validatorService.consultaRequisicao(mes);
    }


}
