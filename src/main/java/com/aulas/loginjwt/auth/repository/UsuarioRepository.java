package com.aulas.loginjwt.auth.repository;

import com.aulas.loginjwt.auth.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByLogin(String login);

    @Query("SELECT p FROM Usuario p WHERE p.login = :login AND p.id <> :id")
    Usuario findByLoginAndIdNot(@Param("login") String nome, @Param("id") Long id);

}
