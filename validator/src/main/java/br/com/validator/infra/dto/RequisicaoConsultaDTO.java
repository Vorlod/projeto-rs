package br.com.validator.infra.dto;

public class RequisicaoConsultaDTO {

    String cliente;
    CustoRequisicaoDTO custoRequisicaoDTO;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public CustoRequisicaoDTO getCustoRequisicaoDTO() {
        return custoRequisicaoDTO;
    }

    public void setCustoRequisicaoDTO(CustoRequisicaoDTO custoRequisicaoDTO) {
        this.custoRequisicaoDTO = custoRequisicaoDTO;
    }
}
