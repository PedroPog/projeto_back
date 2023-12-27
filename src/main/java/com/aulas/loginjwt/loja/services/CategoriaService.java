package com.aulas.loginjwt.loja.services;

import com.aulas.loginjwt.auth.models.Usuario;
import com.aulas.loginjwt.auth.repository.UsuarioRepository;
import com.aulas.loginjwt.cliente.models.Cliente;
import com.aulas.loginjwt.cliente.repository.ClienteRepository;
import com.aulas.loginjwt.loja.models.Categoria;
import com.aulas.loginjwt.loja.models.Produto;
import com.aulas.loginjwt.loja.repository.CategoriaRepository;
import com.aulas.loginjwt.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<Categoria> listAll(String cnpj){
        boolean valid =findByCnpj(cnpj);
        if(!valid){
            throw new RuntimeException("Cnpj do cliente não existe" + cnpj);
        }
        List<Categoria> categorias = categoriaRepository.findAllCnpj(cnpj);
        for (Categoria categoria : categorias) {
            try {
                List<Produto> listProduto = findByListProduto(categoria.getId());
                categoria.setProduto(listProduto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return categorias;
    }
    public Categoria addCategoria(Categoria categoria){
        Categoria existingCategoria = categoriaRepository.findByNome(categoria.getNome());
        if(existingCategoria != null){
            throw new RuntimeException("Já existe um categoria com o mesmo nome: " + categoria.getNome());
        }
        Usuario usuario = usuarioRepository
                .findById(categoria.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        categoria.setNomeUsuario(usuario.getNome());
        categoria.setCnpj(usuario.getCnpj());
        boolean valid =findByCnpj(categoria.getCnpj());
        if(!valid){
            throw new RuntimeException("Cnpj do cliente não existe" + categoria.getCnpj());
        }
       return categoriaRepository.save(categoria);
    }

    public Categoria alterarCategoria(Categoria categoria) {
        Optional<Categoria> existingCategoriaOptional = categoriaRepository.findById(categoria.getId());
        if (existingCategoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria não encontrado com o ID: " + categoria.getId());
        }
        // Verifica se outro Categoria com o mesmo nome já existe
        if (!findByName(categoria.getNome(),categoria.getId())) {
            throw new RuntimeException("Já existe um Categoria com o mesmo nome: " + categoria.getNome());
        }
        // Atualiza os dados do Categoria existente
        Usuario usuario = usuarioRepository
                .findById(categoria.getCodigoUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        categoria.setNomeUsuario(usuario.getNome());
        categoria.setCnpj(usuario.getCnpj());
        boolean valid =findByCnpj(categoria.getCnpj());
        if(!valid){
            throw new RuntimeException("Cnpj do cliente não existe" + categoria.getCnpj());
        }

        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        Optional<Categoria> CategoriaOptional = categoriaRepository.findById(id);
        if (CategoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria não encontrado com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
    public boolean findByName(String nome, Long id) {
        Categoria CategoriaExistente = categoriaRepository.findByNomeAndIdNot(nome, id);
        return CategoriaExistente == null;
    }
    public Optional<Categoria> findById(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isEmpty()){
            throw new RuntimeException("Categoria não encontrado com o ID: " + id);
        }
        return categoria;
    }
    private Boolean findByCnpj(String cnpj){
        Cliente cliente = clienteRepository.findByCnpj(cnpj);
        if(cliente == null){
            throw new RuntimeException("Cnpj do cliente não existe" + cnpj);
        }
        return true;
    }
    private List<Produto> findByListProduto(Long idCategoria){
        List<Produto> produtos = produtoRepository.findByListProduto(idCategoria);
        if(produtos.isEmpty()){
            return produtos;//tem que mandar a list vazia
        }
        return produtos;
    }
}
