package com.aulas.loginjwt.normal.controller;

import com.aulas.loginjwt.cliente.models.Cliente;
import com.aulas.loginjwt.cliente.services.ClienteService;
import com.aulas.loginjwt.normal.models.Normal;
import com.aulas.loginjwt.normal.service.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/normal")
@CrossOrigin
public class NormalController {

    @Autowired
    NormalService normalService;

    @GetMapping("/listall")
    public List<Normal> listAll(){
        return normalService.listAll();
    }
    @PostMapping("/add")
    public Normal addUsuario(@RequestBody Normal normal){
       return normalService.save(normal);
    }
    @PutMapping("/atualizar")
    public Normal atualizarUsuario(@RequestBody Normal normal){
        return normalService.update(normal);
    }
    @GetMapping("/localizar")
    public Normal localizarCliente(@RequestParam String cpf){
        return normalService.findByCpfCliente(cpf);
    }



}
