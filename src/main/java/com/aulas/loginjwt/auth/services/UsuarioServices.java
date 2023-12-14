package com.aulas.loginjwt.auth.services;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder encoder;

    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }
    public Usuario addUsuario(Usuario usuario){
        Usuario existingUsuario = usuarioRepository.findByLogin(usuario.getLogin());
        if(existingUsuario != null){
            throw new RuntimeException("Já existe um usuario com o mesmo login: " + usuario.getLogin());
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario usuario) {
        Optional<Usuario> existingUsuarioOptional = usuarioRepository.findById(usuario.getId());
        if (existingUsuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado com o ID: " + usuario.getId());
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario existingUsuario = existingUsuarioOptional.get();

        // Verifica se outro Categoria com o mesmo nome já existe
        if (!findByLogin(usuario.getLogin(),usuario.getId())) {
            throw new RuntimeException("Já existe um Usuario com o mesmo login: " + usuario.getLogin());
        }
        // Atualiza os dados do Categoria existente
        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setLogin(usuario.getLogin());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setRole(usuario.getRole());
        return usuarioRepository.save(existingUsuario);
    }

    public void deleteUsuario(Long id) {
        Optional<Usuario> UsuarioOptional = usuarioRepository.findById(id);
        if (UsuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    public Usuario validarSenha(String login,String senha){

        Usuario option = usuarioRepository.findByLogin(login);
        if(option == null){
            throw new RuntimeException("Login não encontrado! " +login);
        }
        boolean valid = encoder.matches(senha,option.getSenha());

        //HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        if(valid){
            return option;
        }
        throw new RuntimeException("Senha errada! " +login);
    }
    public boolean findByLogin(String login, Long id) {
        Usuario UsuarioExistente = usuarioRepository.findByLoginAndIdNot(login, id);
        return UsuarioExistente == null;
    }
}
