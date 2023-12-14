package com.aulas.loginjwt.auth.controller;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServices usuarioServices;

    @GetMapping("/listall")
    public List<Usuario> listAll(){
        return usuarioServices.listAll();
    }
    @PostMapping("/add")
    public Usuario addUsuario(@RequestBody Usuario usuario){
       return usuarioServices.addUsuario(usuario);
    }
    @PutMapping("/atualizar")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return usuarioServices.alterarUsuario(usuario);
    }

    @GetMapping("/login")
    public Usuario validarSenha(@RequestParam String login,
                                                @RequestParam String senha){
        return usuarioServices.validarSenha(login,senha);
    }
    @DeleteMapping("/delete")
    public void deleteUsuario(@RequestParam Long id){
        usuarioServices.deleteUsuario(id);
    }


}
