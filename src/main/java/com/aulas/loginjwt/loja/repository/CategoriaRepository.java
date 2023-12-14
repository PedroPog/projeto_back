package com.aulas.loginjwt.loja.repository;

import com.aulas.loginjwt.loja.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByNome(String nome);

    @Query("SELECT p FROM Categoria p WHERE p.nome = :nome AND p.id <> :id")
    Categoria findByNomeAndIdNot(@Param("nome") String nome, @Param("id") Long id);
}
