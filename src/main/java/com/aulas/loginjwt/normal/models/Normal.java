package com.aulas.loginjwt.normal.models;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.models.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
public class Normal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true,length = 11)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;
    @Column(nullable = false,length = 14)
    private String celular;
    @Column(nullable = false,length = 100)
    private String email;
    @Column(nullable = false)
    private String nascimento;
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
    @Transient
    private Usuario usuarios;

    @Transient
    @Column(nullable = false)
    private String login;
    @Transient
    @Column(nullable = false)
    private String senha;
    @Transient
    @Column(nullable = false)
    private String role = "normal";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
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

}
