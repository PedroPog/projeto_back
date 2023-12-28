package com.aulas.loginjwt.normal.service;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.models.dto.UsuarioDTO;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import com.aulas.loginjwt.auth.services.UsuarioServices;
import com.aulas.loginjwt.cliente.models.Cliente;
import com.aulas.loginjwt.cliente.repository.ClienteRepository;
import com.aulas.loginjwt.normal.models.Normal;
import com.aulas.loginjwt.normal.repository.NormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NormalService {
    @Autowired
    NormalRepository normalRepository;
    @Autowired
    UsuarioServices usuarioServices;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Normal> listAll() {
        return normalRepository.findAllByOrderByCpfAsc();
    }
    public Normal save(Normal normal){
        if (normal == null) {
            throw new IllegalArgumentException("Os objetos Normal e Usuario não podem ser nulos.");
        }
        if (findByCpf(normal.getCpf(), normal.getId())) {
            throw new DuplicateKeyException("Já existe um Cliente com o mesmo cpf: " + normal.getCpf());
        }
        Usuario usuario = new Usuario();
        usuario.setLogin(normal.getLogin());
        usuario.setSenha(normal.getSenha());
        usuario.setRole(normal.getRole());
        usuario.setCnpj(normal.getCpf());
        usuario.setNome(normal.getNome());
        usuarioServices.addUsuario(usuario);//TODO a ideia é criar tanto o login quanto o usuario
        return normalRepository.save(normal);
    }
    public Normal update(Normal normal){
        Optional<Normal> normalExistente = normalRepository.findById(normal.getId());
        if (normalExistente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + normal.getId());
        }
        if (findByCpf(normal.getCpf(), normal.getId())) {
            throw new RuntimeException("Já existe um Cliente com o mesmo cpf: " + normal.getCpf());
        }
        return normalRepository.save(normal);
    }
    public Normal findByCpfCliente(String cpf){
        Normal normal = normalRepository.findByCpf(cpf);
        if(normal == null){
            throw new RuntimeException("Não localizamos o cpf: " + normal.getCpf());
        }
        Usuario usuario = findByUsuario(cpf);
        normal.setUsuarios(usuario);
        return normal;
    }
    public boolean findByCpf(String cpf, Long id) {
        Normal normalExistente = normalRepository.findByCpfAndIdNot(cpf, id);
        return normalExistente != null;
    }
    private Usuario findByUsuario(String cnpj){
        return usuarioRepository.findByUsuario(cnpj);
    }
}
