package com.store.api.service;

import java.util.List;
import com.store.api.repository.entityDTO.VentaDTO;


public interface VentaService {

    /* -- CREAR WITH JPArepository -- */
    public VentaDTO save(VentaDTO VentaDTO);


    /* -- ACTUALIZAR WITH JPArepository -- */
    public VentaDTO update(Long id, VentaDTO VentaDTO);


    /* -- ELIMINAR WITH JPArepository -- */
    void deleteById(Long id);


    /* -- LISTAR WITH JPArepository -- */
    List<VentaDTO> findAll();


    /* -- ENCONTRAR 1 WITH JPArepository -- */
    VentaDTO findById(Long id);

}