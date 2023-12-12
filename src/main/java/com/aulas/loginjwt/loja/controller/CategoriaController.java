package com.aulas.loginjwt.loja.controller;

import com.aulas.loginjwt.loja.models.Categoria;
import com.aulas.loginjwt.loja.models.Produto;
import com.aulas.loginjwt.loja.services.CategoriaService;
import com.aulas.loginjwt.loja.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listall")
    public List<Categoria> listAll(){
        return categoriaService.listAll();
    }
    @PostMapping("/add")
    public Categoria addProduto(@RequestBody Categoria categoria){
       return categoriaService.addCategoria(categoria);
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
