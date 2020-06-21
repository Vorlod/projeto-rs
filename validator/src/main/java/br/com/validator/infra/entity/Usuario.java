package br.com.validator.infra.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO", schema = "VALIDACAO")
@SequenceGenerator(name = "sequence_usuario", sequenceName = "USUARIO_SEQUENCE", schema = "VALIDACAO", allocationSize = 1)
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_usuario")
    private Long id;

    @Column( name = "CLIENTE")
    private String cliente;

    @Column( name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
