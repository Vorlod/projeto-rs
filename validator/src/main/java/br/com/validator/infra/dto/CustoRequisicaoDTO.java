package br.com.validator.infra.dto;

public class CustoRequisicaoDTO {

    String preco;
    int qtd;

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
