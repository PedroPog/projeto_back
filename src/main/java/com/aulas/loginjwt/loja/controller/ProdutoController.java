package com.aulas.loginjwt.loja.controller;

import com.aulas.loginjwt.loja.models.Produto;
import com.aulas.loginjwt.loja.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/listall")
    public List<Produto> listAll(@RequestHeader String cnpj){
        if(cnpj.isBlank()){
            throw new RuntimeException("Falta de cnpj!");
        }return produtoService.listAll(cnpj);
    }
    @PostMapping("/add")
    public Produto addProduto(@RequestBody Produto produto){
       return produtoService.addProduto(produto);
    }
    @PutMapping("/atualizar")
    public Produto atualizarProduto(@RequestBody Produto produto){
        return produtoService.alterarProduto(produto);
    }
    @DeleteMapping("/delete")
    public void deleteProduto(@RequestParam Long id){
        produtoService.deleteProduto(id);
    }
}
