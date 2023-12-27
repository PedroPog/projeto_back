package com.aulas.loginjwt.auth.models.dto;

import com.aulas.loginjwt.auth.models.Usuario;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

public class UsuarioDTO {
    private String login;
    private String nome;
    private String role;
    private String cnpj;

    public UsuarioDTO convert(Usuario usuario){
        BeanUtils.copyProperties(usuario, this);
        return this;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
