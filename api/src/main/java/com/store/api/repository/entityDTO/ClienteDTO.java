package com.store.api.repository.entityDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id_cliente"
)
public class ClienteDTO {


    private Long id_cliente;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;

    private String email;

    @JsonInclude(Include.NON_NULL)
    private UsuarioDTO usuario;

    @JsonInclude(Include.NON_NULL)
    private List<VentaDTO> compras;


}