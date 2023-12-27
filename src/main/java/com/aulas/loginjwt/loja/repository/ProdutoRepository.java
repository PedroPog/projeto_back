package com.aulas.loginjwt.loja.repository;

import com.aulas.loginjwt.loja.models.Categoria;
import com.aulas.loginjwt.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Produto findByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.cnpj = :cnpj")
    List<Produto> findAllCnpj(@Param("cnpj") String cnpj);

    @Query("SELECT p FROM Produto p WHERE p.nome = :nome AND p.id <> :id")
    Produto findByNomeAndIdNot(@Param("nome") String nome, @Param("id") Long id);

    @Query("SELECT p FROM Produto p WHERE p.idcategoria = :idcategoria")
    List<Produto> findByListProduto(@Param("idcategoria") Long idcategoria);
}
