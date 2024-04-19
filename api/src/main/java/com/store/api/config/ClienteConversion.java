package com.store.api.config;

import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.repository.entity.Venta;
import com.store.api.repository.entity.Cliente;


@Configuration
public class ClienteConversion {
    @Autowired
    private ModelMapper dbm;

    public Cliente convertirDTOCliente(ClienteDTO ClienteDTO) {
        return dbm.map(ClienteDTO, Cliente.class);
    }

    public ClienteDTO convertirClienteDTOSinCompras(Cliente Cliente) {
        ClienteDTO ClienteDTO = dbm.map(Cliente, ClienteDTO.class);
        ClienteDTO.setId_cliente(Cliente.getId_cliente());
        ClienteDTO.setNombres(Cliente.getNombres());
        ClienteDTO.setApellidos(Cliente.getApellidos());
        ClienteDTO.setDni(Cliente.getDni());
        ClienteDTO.setTelefono(Cliente.getTelefono());
        ClienteDTO.setEmail(Cliente.getEmail());
        return ClienteDTO;
    }


    public ClienteDTO convertirClienteDTO(Cliente Cliente, boolean cargarCompras) {
        ClienteDTO ClienteDTO = dbm.map(Cliente, ClienteDTO.class);
        ClienteDTO.setId_cliente(Cliente.getId_cliente());
        ClienteDTO.setNombres(Cliente.getNombres());
        ClienteDTO.setApellidos(Cliente.getApellidos());
        ClienteDTO.setDni(Cliente.getDni());
        ClienteDTO.setTelefono(Cliente.getTelefono());
        ClienteDTO.setEmail(Cliente.getEmail());

        if (cargarCompras) {
/*             Hibernate.initialize(Cliente.getCompras());
            ClienteDTO.setCompras(Cliente.getCompras().stream()
                .map(venta -> dbm.map(venta, VentaDTO.class))
                .collect(Collectors.toList())); */
            Hibernate.initialize(Cliente.getCompras());
            // Evitar la serialización de las compras dentro de cada venta
            ClienteDTO.setCompras(Cliente.getCompras().stream()
                    .map(Venta::getId_venta)
                    .map(id -> {
                        VentaDTO ventaDTO = new VentaDTO();
                        ventaDTO.setId_venta(id);
                        return ventaDTO;
                    })
                    .collect(Collectors.toList()));

        } else {
            ClienteDTO.setCompras(null);
        }


        return ClienteDTO;
    }

}