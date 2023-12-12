package com.aulas.loginjwt.loja.services;

import com.aulas.loginjwt.loja.models.Produto;
import com.aulas.loginjwt.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }
    public Produto addProduto(Produto produto){
        Produto existingProduto = produtoRepository.findByNome(produto.getNome());
        if(existingProduto != null){
            throw new RuntimeException("Já existe um produto com o mesmo nome: " + produto.getNome());
        }
       return produtoRepository.save(produto);
    }

    public Produto alterarProduto(Produto produto) {
        Optional<Produto> existingProdutoOptional = produtoRepository.findById(produto.getId());
        if (existingProdutoOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado com o ID: " + produto.getId());
        }

        Produto existingProduto = existingProdutoOptional.get();

        // Verifica se outro produto com o mesmo nome já existe
        if (!findByName(produto.getNome(),produto.getId())) {
            throw new RuntimeException("Já existe um produto com o mesmo nome: " + produto.getNome());
        }

        // Atualiza os dados do produto existente
        existingProduto.setNome(produto.getNome());
        existingProduto.setImagem(produto.getImagem());
        existingProduto.setVarialvel(produto.getVarialvel());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setCategoria(produto.getCategoria());

        return produtoRepository.save(existingProduto);
    }

    public void deleteProduto(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }

        produtoRepository.deleteById(id);
    }
    public boolean findByName(String nome, Long id) {
        Produto produtoExistente = produtoRepository.findByNomeAndIdNot(nome, id);
        return produtoExistente == null;
    }
}
