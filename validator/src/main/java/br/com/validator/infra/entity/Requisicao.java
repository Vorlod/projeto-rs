package br.com.validator.infra.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REQUISICAO", schema = "VALIDACAO")
@SequenceGenerator(name = "sequence_requisicao", sequenceName = "REQUISICAO_SEQUENCE", schema = "VALIDACAO", allocationSize = 1)
public class Requisicao {

    @Id
    @Column(name = "ID_REQUISICAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_requisicao")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID_USUARIO", name = "ID_USUARIO")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID_REQUISICAO_PRECO", name = "ID_REQUISICAO_PRECO")
    private RequisicaoPreco idRequisicaoPreco;

    @Column( name = "RESULTADO")
    private Integer resultado;

    @Column(name = "NUM_DOC")
    private String numdoc;

    @Column( name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RequisicaoPreco getIdRequisicaoPreco() {
        return idRequisicaoPreco;
    }

    public void setIdRequisicaoPreco(RequisicaoPreco idRequisicaoPreco) {
        this.idRequisicaoPreco = idRequisicaoPreco;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
