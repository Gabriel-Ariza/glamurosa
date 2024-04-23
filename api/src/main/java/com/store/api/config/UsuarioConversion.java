package com.store.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.repository.entityDTO.UsuarioDTO;
import com.store.api.repository.entity.Usuario;


@Configuration
public class UsuarioConversion {

    @Autowired
    private ModelMapper dbm;
    private ClienteConversion ClienteConversion;

    public Usuario convertirDTOUsuario(UsuarioDTO usuarioDTO) {
        return dbm.map(usuarioDTO, Usuario.class);
    }


    public UsuarioDTO convertirUsuarioDTO(Usuario usuario, boolean cargarCliente, boolean cargarCompras) {
        UsuarioDTO usuarioDTO = dbm.map(usuario, UsuarioDTO.class);
        usuarioDTO.setId_usuario(usuario.getId_usuario());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setRol(usuario.getRol());
    
        if (cargarCliente && usuario.getCliente() != null) {
            ClienteDTO clienteDTO = ClienteConversion.convertirClienteDTO(usuario.getCliente(), cargarCompras);
            usuarioDTO.setCliente(clienteDTO);
        }
    
        return usuarioDTO;
    }


}