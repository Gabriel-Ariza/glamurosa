package com.store.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.repository.entity.Venta;


@Configuration
public class VentaConversion {
    @Autowired
    private ModelMapper dbm;

    public Venta convertirDTOVenta(VentaDTO VentaDTO) {
        return dbm.map(VentaDTO, Venta.class);
    }


    public VentaDTO convertirVentaDTO(Venta Venta) {
        VentaDTO VentaDTO = dbm.map(Venta, VentaDTO.class);
        VentaDTO.setId_venta(Venta.getId_venta());
        VentaDTO.setCreatedAt(Venta.getCreatedAt());
        VentaDTO.setTotal(Venta.getTotal());
        VentaDTO.setCliente(Venta.getCliente());


        return VentaDTO;
    }

}