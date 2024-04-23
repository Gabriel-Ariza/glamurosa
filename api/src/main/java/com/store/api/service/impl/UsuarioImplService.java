package com.store.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import com.store.api.config.UsuarioConversion;
import com.store.api.exception.NombreInvalidoException;
import com.store.api.exception.NotFoundElementsException;
import com.store.api.exception.TipoDatoIncorrectoException;
import com.store.api.repository.UsuarioRepository;
import com.store.api.repository.entity.Usuario;
import com.store.api.repository.entityDTO.UsuarioDTO;
import com.store.api.service.UsuarioService;

@Service
@AllArgsConstructor
public class UsuarioImplService implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioConversion usuarioConversion;


    @Override
    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioCurrent = usuarioConversion.convertirDTOUsuario(usuarioDTO);
            usuarioRepository.save(usuarioCurrent);
            return usuarioConversion.convertirUsuarioDTO(usuarioCurrent, true, true);
        } catch (DataIntegrityViolationException ex) {
            throw new TipoDatoIncorrectoException("Error de integridad de datos", ex);
        } catch (IllegalArgumentException ex) {
            throw new NombreInvalidoException("Argumento inválido", ex);
        } catch (NullPointerException ex) {
            throw new NotFoundElementsException("Referencia nula", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    //* AQUI DEBE HABER LOGICA EXTRA, SI EL QUE CONSULTA TIENE ROL DE ADMINISTRADOR
    //* TIENE LA POTESTAD DE CAMBIAR ROL, EMAIL, CONTRASEÑA
    @Override
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        try {
            Optional<Usuario> usuarioCurrentOptional = usuarioRepository.findById(id);
            if (usuarioCurrentOptional.isPresent()) {
                Usuario usuarioCurrent = usuarioCurrentOptional.get();
                usuarioCurrent.setContrasena(usuarioDTO.getContrasena());
                usuarioRepository.save(usuarioCurrent);
                return usuarioConversion.convertirUsuarioDTO(usuarioCurrent, true, true);
            } else {
                throw new NotFoundElementsException("Usuario no encontrado");
            }
        } catch (DataIntegrityViolationException ex) {
            throw new TipoDatoIncorrectoException("Error de integridad de datos", ex);
        } catch (IllegalArgumentException ex) {
            throw new NombreInvalidoException("Argumento inválido", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundElementsException("Usuario no encontrado", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll(boolean cargarCliente){
        try {
            List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
            return usuarios.stream()
                        .map(usuario -> usuarioConversion.convertirUsuarioDTO(usuario, cargarCliente, false))
                        .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id, boolean cargarCliente){
        try {
            Optional<Usuario> usuarioCurrentOptional = usuarioRepository.findById(id);

            if (usuarioCurrentOptional.isPresent()) {
                return usuarioConversion.convertirUsuarioDTO(usuarioCurrentOptional.get(), cargarCliente, false);
            } else {
                throw new NotFoundElementsException("Usuario no encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAllcargandoClientesAndCompras() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarios.stream()
                        .map(usuario -> usuarioConversion.convertirUsuarioDTO(usuario, true, true))
                        .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByIdCargandoClienteAndCompras(Long id) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                UsuarioDTO usuarioDTO = usuarioConversion.convertirUsuarioDTO(usuarioOptional.get(), true, true);
                return Optional.of(usuarioDTO);
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


}