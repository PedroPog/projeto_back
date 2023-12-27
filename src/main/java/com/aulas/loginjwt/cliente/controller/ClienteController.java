package com.aulas.loginjwt.cliente.controller;

import com.aulas.loginjwt.cliente.models.Cliente;
import com.aulas.loginjwt.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listall")
    public List<Cliente> listAll(){
        return clienteService.listAll();
    }
    @PostMapping("/add")
    public Cliente addUsuario(@RequestBody Cliente cliente){
       return clienteService.save(cliente);
    }
    @PutMapping("/atualizar")
    public Cliente atualizarUsuario(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }
    @GetMapping("/localizar")
    public Cliente localizarCliente(@RequestParam String cnpj){
        return clienteService.findByCnpjCliente(cnpj);
    }



}
