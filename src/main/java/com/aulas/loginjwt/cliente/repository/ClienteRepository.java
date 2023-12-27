package com.aulas.loginjwt.cliente.repository;

import com.aulas.loginjwt.cliente.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findAllByOrderByCnpjAsc();
    //TODO List<Cliente> findAllByOrderBy[item01][metodo]Asc();
    // item01=item a ser organizado. metodo: tipo de organização
    Cliente findByCnpj(String cnpj);
    @Query("SELECT p FROM Cliente p WHERE p.cnpj = :cnpj AND p.id <> :id")
    Cliente findByCnpjAndIdNot(@Param("cnpj") String cnpj, @Param("id") Long id);

}
