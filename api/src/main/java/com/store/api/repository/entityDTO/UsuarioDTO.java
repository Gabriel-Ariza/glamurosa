package com.store.api.repository.entityDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.store.api.repository.entity.Usuario;
import lombok.Data;


@Data
public class UsuarioDTO {


    private Long id_usuario;
    private String email;
    private String contrasena;
    private Usuario.Rol rol;


    @JsonInclude(Include.NON_NULL)
    private ClienteDTO cliente;


}
