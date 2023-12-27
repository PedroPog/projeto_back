package com.aulas.loginjwt.cliente.models;

import com.aulas.loginjwt.auth.models.dto.UsuarioDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true,length = 14)
    private String cnpj;
    @Column(nullable = false)
    private String rasaosocial;
    @Column(nullable = false)
    private String fantasia;
    @Column(nullable = false,length = 14)
    private String tel;
    @Column(nullable = false,length = 100)
    private String email;
    @Column(nullable = false)
    private String fundacao;
    @Column(nullable = false,length = 100)
    private String endereco;
    @Column(nullable = false,length = 50)
    private String bairro;
    @Column(nullable = false,length = 50)
    private String cidade;
    @Column(nullable = false,length = 2)
    private String uf;
    @Column(nullable = false,length = 8)
    private String cep;
    @Column(nullable = false)
    private String sobre;
    @Transient
    private List<UsuarioDTO> usuarios;
    @Transient
    private Long codigoUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRasaosocial() {
        return rasaosocial;
    }

    public void setRasaosocial(String rasaosocial) {
        this.rasaosocial = rasaosocial;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
