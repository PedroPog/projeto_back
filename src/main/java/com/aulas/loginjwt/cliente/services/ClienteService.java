package com.aulas.loginjwt.cliente.services;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.models.dto.UsuarioDTO;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import com.aulas.loginjwt.cliente.models.Cliente;
import com.aulas.loginjwt.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Cliente> listAll() {
        return clienteRepository.findAllByOrderByCnpjAsc();
        //List<Cliente> clientes = clienteRepository.findAllByOrderByCnpjAsc();
        /*for (Cliente cliente : clientes) {
            try {
                List<UsuarioDTO> listUsuario = findByListUsuario(cliente.getCnpj());
                cliente.setUsuarios(listUsuario);
            } catch (Exception e) {
                e.printStackTrace();
                // Se ocorrer um erro, você pode lidar com ele aqui, por exemplo, logando o erro.
            }
        }*/
    }
    public Cliente save(Cliente cliente){
        if (findByCnpj(cliente.getCnpj(), cliente.getId())) {
            throw new RuntimeException("Já existe um Cliente com o mesmo cnpj: " + cliente.getCnpj());
        }
        Usuario usuario = usuarioRepository
                .findById(cliente.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));//TODO verificação de usuario
        return clienteRepository.save(cliente);
    }
    public Cliente update(Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(cliente.getId());
        if (clienteExistente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + cliente.getId());
        }

        if (findByCnpj(cliente.getCnpj(), cliente.getId())) {
            throw new RuntimeException("Já existe um Cliente com o mesmo cnpj: " + cliente.getCnpj());
        }

        Usuario usuario = usuarioRepository
                .findById(cliente.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));//TODO verificação de usuario
        return clienteRepository.save(cliente);
    }
    public Cliente findByCnpjCliente(String cnpj){
        Cliente cliente = clienteRepository.findByCnpj(cnpj);
        if(cliente == null){
            throw new RuntimeException("Não localizamos o cnpj: " + cliente.getCnpj());
        }
        return cliente;
    }
    public boolean findByCnpj(String cnpj, Long id) {
        Cliente clienteExistente = clienteRepository.findByCnpjAndIdNot(cnpj, id);
        return clienteExistente != null;
    }
    private List<UsuarioDTO> findByListUsuario(String cnpj){
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findByListUsuario(cnpj);
        for(Usuario usuario:usuarios){//TODO for serve para converter o usuario em dto
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.convert(usuario);
            usuarioDTOS.add(usuarioDTO);
        }
        if(usuarios.isEmpty()){
            return usuarioDTOS;//tem que mandar a list vazia
        }
        return usuarioDTOS;
    }
}
