package com.store.api.config;

import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.repository.entity.Cliente;


@Configuration
public class ClienteConversion {
    @Autowired
    private ModelMapper dbm;

    public Cliente convertirDTOCliente(ClienteDTO ClienteDTO) {
        return dbm.map(ClienteDTO, Cliente.class);
    }


    public ClienteDTO convertirClienteDTO(Cliente Cliente, boolean cargarCompras) {
        ClienteDTO ClienteDTO = dbm.map(Cliente, ClienteDTO.class);
        ClienteDTO.setId_cliente(Cliente.getId_cliente());
        ClienteDTO.setNombres(Cliente.getNombres());
        ClienteDTO.setApellidos(Cliente.getApellidos());
        ClienteDTO.setDni(Cliente.getDni());
        ClienteDTO.setTelefono(Cliente.getTelefono());
    
        // Solo obtenemos el correo del usuario, no el usuario completo
        if (Cliente.getUsuario() != null) {
            ClienteDTO.setEmail(Cliente.getUsuario().getEmail());
        }
    
        if (cargarCompras) {
            Hibernate.initialize(Cliente.getCompras());
            Long id_cliente = Cliente.getId_cliente();
    
            ClienteDTO.setCompras(Cliente.getCompras().stream()
            .map(venta -> {
                VentaDTO ventaDTO = dbm.map(venta, VentaDTO.class);
                ventaDTO.setCreatedAt(venta.getCreatedAt());
                ventaDTO.setTotal(venta.getTotal());
                ventaDTO.setCliente(null);
                ventaDTO.setId_cliente(id_cliente);
    
                return ventaDTO;
            })
            .collect(Collectors.toList()));
    
        } else {
            ClienteDTO.setCompras(null);
        }
    
        return ClienteDTO;
    }


}