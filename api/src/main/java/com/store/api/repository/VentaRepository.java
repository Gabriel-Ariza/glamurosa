package com.store.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.store.api.repository.entity.Venta;



/* -- CREAR, LISTAR, ACTUALIZAR  solamente -- */

public interface VentaRepository extends JpaRepository<Venta, Long> {


}
