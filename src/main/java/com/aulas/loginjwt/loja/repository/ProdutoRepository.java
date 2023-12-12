package com.aulas.loginjwt.loja.repository;

import com.aulas.loginjwt.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Produto findByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.nome = :nome AND p.id <> :id")
    Produto findByNomeAndIdNot(@Param("nome") String nome, @Param("id") Long id);
}
