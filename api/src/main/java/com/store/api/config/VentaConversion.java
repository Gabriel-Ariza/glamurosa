package com.store.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.service.ClienteService;
import com.store.api.repository.entity.Cliente;
import com.store.api.repository.entity.Venta;


@Configuration
public class VentaConversion {
    @Autowired
    private ModelMapper dbm;

    @Autowired
    private ClienteService clienteService;


    public Venta convertirDTOVenta(VentaDTO ventaDTO) {
        Venta venta = dbm.map(ventaDTO, Venta.class);

        if (ventaDTO.getId_cliente() != null) {
            ClienteDTO clienteDTO = clienteService.findById(ventaDTO.getId_cliente(), false);
            if (clienteDTO == null) {
                throw new IllegalArgumentException("Cliente with id " + ventaDTO.getId_cliente() + " not found");
            }
            venta.setCliente(dbm.map(clienteDTO, Cliente.class));
        }

        return venta;
    }



    public VentaDTO convertirVentaDTO(Venta Venta, boolean cargarCliente) {
        
        if (Venta == null) {
            throw new IllegalArgumentException("Venta cannot be null");
        }
        
        VentaDTO VentaDTO = dbm.map(Venta, VentaDTO.class);
        VentaDTO.setId_venta(Venta.getId_venta());
        VentaDTO.setCreatedAt(Venta.getCreatedAt());
        VentaDTO.setTotal(Venta.getTotal());

        if (cargarCliente && Venta.getCliente() != null) {

            Cliente cliente = Venta.getCliente();
            ClienteDTO clienteDTO = dbm.map(cliente, ClienteDTO.class);
            clienteDTO.setCompras(null);
            VentaDTO.setCliente(clienteDTO);

        } else {
            VentaDTO.setCliente(null);
        }

        return VentaDTO;
    }

}