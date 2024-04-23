package com.store.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import com.store.api.config.ClienteConversion;
import com.store.api.exception.NombreInvalidoException;
import com.store.api.exception.NotFoundElementsException;
import com.store.api.exception.TipoDatoIncorrectoException;
import com.store.api.repository.ClienteRepository;
import com.store.api.repository.UsuarioRepository;
import com.store.api.repository.entity.Cliente;
import com.store.api.repository.entity.Usuario;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.service.ClienteService;



@Service
@AllArgsConstructor
public class ClienteImplService implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    private UsuarioRepository UsuarioRepository;
    private ClienteConversion clienteConversion;


    @Override
    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        try {
            // Comprueba si el UsuarioDTO existe
            if (clienteDTO.getUsuario() != null) {
                Optional<Usuario> usuarioOptional = UsuarioRepository.findById(clienteDTO.getUsuario().getId_usuario());
                if (!usuarioOptional.isPresent()) {
                    throw new NotFoundElementsException("Usuario no encontrado");
                }
            }
    
            Cliente clienteCurrent = clienteConversion.convertirDTOCliente(clienteDTO);
            Cliente clienteSaved = clienteRepository.save(clienteCurrent);
            return clienteConversion.convertirClienteDTO(clienteSaved, true);
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

    @Override
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        try {
            Optional<Cliente> clienteCurrentOptional = clienteRepository.findById(id);
            if(clienteCurrentOptional.isPresent()){
                Cliente clienteCurrent = clienteConversion.convertirDTOCliente(clienteDTO);
                
                clienteCurrent.setId_cliente(id);
                clienteCurrent.setNombres(clienteDTO.getNombres());
                clienteCurrent.setApellidos(clienteDTO.getApellidos());
                clienteCurrent.setDni(clienteDTO.getDni());
                clienteCurrent.setTelefono(clienteDTO.getTelefono());
        
                clienteRepository.save(clienteCurrent);
                
                return clienteConversion.convertirClienteDTO(clienteCurrent, true);
            } else {
                throw new NotFoundElementsException("Cliente no encontrado");
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
    public void deleteById(Long id){
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundElementsException("Cliente no encontrado", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }
    


    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(boolean cargarCompras){
        try {
            List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
            return clientes.stream()
                .map(cliente -> clienteConversion.convertirClienteDTO(cliente, cargarCompras))
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id, boolean cargarCompras){
        try {
            Optional<Cliente> clienteCurrentOptional = clienteRepository.findById(id);
        
            if (clienteCurrentOptional.isPresent()) {
                return clienteConversion.convertirClienteDTO(clienteCurrentOptional.get(), cargarCompras);
            } else {
                throw new NotFoundElementsException("Cliente no encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> clientesXcantidadCompras(Long cantidadCompras, boolean cargarCompras){
        List<Cliente> clientes = clienteRepository.clientesXcantidadCompras(cantidadCompras);
        return clientes.stream()
            .map(cliente -> clienteConversion.convertirClienteDTO(cliente, cargarCompras))
            .collect(Collectors.toList());
    }
}
