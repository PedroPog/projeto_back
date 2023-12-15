package com.aulas.loginjwt.auth.services;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import com.aulas.loginjwt.auth.security.DetalhesUsuarioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalhesUsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByLogin(username));
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario [" + username + "] não encontrato!");
        }
        return new DetalhesUsuarioData(usuario);
    }
}
