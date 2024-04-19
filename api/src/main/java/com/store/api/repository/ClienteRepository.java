package com.store.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.store.api.repository.entity.Cliente;


/* -- CREAR, LISTAR, ACTUALIZAR  solamente -- */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    /* -- Mostrar clientes segun su cantidad de compras -- */

    @Query(value = "SELECT c.* FROM Cliente c JOIN Venta v ON c.id_cliente = v.id_cliente GROUP BY c.id_cliente HAVING COUNT(v.id_venta) >= :cantidadCompras", nativeQuery = true)
    List<Cliente> clientesXcantidadCompras(@Param("cantidadCompras") Long cantidadCompras);



}