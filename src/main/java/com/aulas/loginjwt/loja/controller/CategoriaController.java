package com.aulas.loginjwt.loja.controller;

import com.aulas.loginjwt.loja.models.Categoria;
import com.aulas.loginjwt.loja.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listall")
    public List<Categoria> listAll(@RequestHeader String cnpj){
        if(cnpj.isBlank()){
            throw new RuntimeException("Falta de cnpj!");
        }
        return categoriaService.listAll(cnpj);
    }
    @PostMapping("/add")
    public ResponseEntity<Categoria> addProduto(@RequestBody Categoria categoria){
       try{
           return ResponseEntity
                   .status(HttpStatus.CREATED).body(categoriaService.addCategoria(categoria));
       }catch (IllegalArgumentException e){
           return ResponseEntity
                   .notFound().build();
       }
    }
    @PutMapping("/atualizar")
    public Categoria atualizarProduto(@RequestBody Categoria categoria){
        return categoriaService.alterarCategoria(categoria);
    }
    @DeleteMapping("/delete")
    public void deleteProduto(@RequestParam Long id){
        categoriaService.deleteCategoria(id);
    }


}
