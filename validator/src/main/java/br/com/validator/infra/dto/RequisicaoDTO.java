package br.com.validator.infra.dto;

public class RequisicaoDTO {

    String cliente;
    String numDoc;

    public RequisicaoDTO(String cliente, String numDoc) {
        this.cliente = cliente;
        this.numDoc = numDoc;
    }
    public RequisicaoDTO() {

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
}
