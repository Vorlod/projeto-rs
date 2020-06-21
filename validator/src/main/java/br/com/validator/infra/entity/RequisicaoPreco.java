package br.com.validator.infra.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REQUISICAO_PRECO", schema = "VALIDACAO")
@SequenceGenerator(name = "sequence_requisicao_preco", sequenceName = "REQUISICAO_PRECO_SEQUENCE", schema = "VALIDACAO", allocationSize = 1)
public class RequisicaoPreco {

    @Id
    @Column(name = "ID_REQUISICAO_PRECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_requisicao_preco")
    private Long id;

    @Column( name = "PRECO")
    private Double preco;

    @Column( name = "DATA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIni;

    @Column( name = "DATA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
