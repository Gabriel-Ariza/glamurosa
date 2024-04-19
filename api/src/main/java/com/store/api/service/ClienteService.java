package com.store.api.service;

import java.util.List;
import com.store.api.repository.entityDTO.ClienteDTO;


public interface ClienteService {

    /* -- CREAR WITH JPArepository -- */
    public ClienteDTO save(ClienteDTO ClienteDTO);


    /* -- ACTUALIZAR WITH JPArepository -- */
    public ClienteDTO update(Long id, ClienteDTO clienteDTO);


    /* -- ELIMINAR WITH JPArepository -- */
    void deleteById(Long id);


    /* -- LISTAR WITH JPArepository -- */
    List<ClienteDTO> findAll(boolean cargarCompras);


    /* -- ENCONTRAR 1 WITH JPArepository -- */
    ClienteDTO findById(Long id, boolean cargarCompras);

}