package com.store.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.store.api.repository.entity.Cliente;



/* -- CREAR, LISTAR, ACTUALIZAR  solamente -- */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {



    // listarclientesconxcantidaddecompras
}