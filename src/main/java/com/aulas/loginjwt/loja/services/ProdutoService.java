package com.aulas.loginjwt.loja.services;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import com.aulas.loginjwt.loja.models.Categoria;
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
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }
    public Produto addProduto(Produto produto){
        Produto existingProduto = produtoRepository.findByNome(produto.getNome());
        if(existingProduto != null){
            throw new RuntimeException("Já existe um produto com o mesmo nome: " + produto.getNome());
        }
        if(produto.getPreco() == 0){
            throw new RuntimeException("Não pode valor 0 no preço: " + produto.getPreco());
        }
        Usuario usuario = usuarioRepository
                .findById(produto.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        produto.setNomeUsuario(usuario.getNome());
        Optional<Categoria> categoria = categoriaService.findById(produto.getIdcategoria());
        if(categoria.isEmpty()){
            throw new RuntimeException("Categoria não encontrado com o ID: " + produto.getIdcategoria());
        }
        produto.setCategoria(categoria.get().getNome());
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
        existingProduto.setEstoque(produto.getEstoque());
        existingProduto.setPreco(produto.getPreco());

        Usuario usuario = usuarioRepository
                .findById(produto.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        produto.setNomeUsuario(usuario.getNome());

        Optional<Categoria> categoria = categoriaService.findById(produto.getIdcategoria());
        if(categoria.isEmpty()){
            throw new RuntimeException("Categoria não encontrado com o ID: " + produto.getIdcategoria());
        }
        existingProduto.setIdcategoria(produto.getIdcategoria());
        existingProduto.setCategoria(categoria.get().getNome());
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
