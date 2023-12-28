package com.aulas.loginjwt.normal.repository;

import com.aulas.loginjwt.normal.models.Normal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalRepository extends JpaRepository<Normal,Long> {
    List<Normal> findAllByOrderByCpfAsc();
    //TODO List<Cliente> findAllByOrderBy[item01][metodo]Asc();
    // item01=item a ser organizado. metodo: tipo de organização
    Normal findByCpf(String cpf);
    @Query("SELECT p FROM Normal p WHERE p.cpf = :cpf AND p.id <> :id")
    Normal findByCpfAndIdNot(@Param("cpf") String cpf, @Param("id") Long id);

}
