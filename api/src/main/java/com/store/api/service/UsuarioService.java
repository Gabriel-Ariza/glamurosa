package com.store.api.service;

import java.util.List;
import java.util.Optional;

import com.store.api.repository.entityDTO.UsuarioDTO;


public interface UsuarioService {

    /* -- CREAR WITH JPArepository -- */
    public UsuarioDTO save(UsuarioDTO UsuarioDTO);


    /* -- ACTUALIZAR WITH JPArepository -- */
    public UsuarioDTO update(Long id, UsuarioDTO UsuarioDTO);


    /* -- ELIMINAR WITH JPArepository -- */
    void deleteById(Long id);


    /* -- LISTAR WITH JPArepository -- */
    List<UsuarioDTO> findAll(boolean cargarCliente);


    /* -- ENCONTRAR 1 WITH JPArepository -- */
    UsuarioDTO findById(Long id, boolean cargarCliente);


    /* -- LISTAR WTIH clientes y sus compras -- */
    List<UsuarioDTO> findAllcargandoClientesAndCompras();


    /* -- ENCONTRAR 1 WITH cliente y sus compras -- */
    Optional<UsuarioDTO> findByIdCargandoClienteAndCompras(Long id);

}
