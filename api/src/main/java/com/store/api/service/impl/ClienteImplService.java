package com.store.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import com.store.api.config.ClienteConversion;
import com.store.api.repository.ClienteRepository;
import com.store.api.repository.entity.Cliente;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.service.ClienteService;



@Service
@AllArgsConstructor
public class ClienteImplService implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    private ClienteConversion clienteConversion;


    @Override
    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente clienteCurrent = clienteConversion.convertirDTOCliente(clienteDTO);
        clienteRepository.save(clienteCurrent);
        return clienteConversion.convertirClienteDTO(clienteCurrent, true);
    }


    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteCurrentOptional = clienteRepository.findById(id);
        if(clienteCurrentOptional.isPresent()){
            Cliente clienteCurrent = clienteConversion.convertirDTOCliente(clienteDTO);
            
            clienteCurrent.setId_cliente(id);
            clienteCurrent.setNombres(clienteDTO.getNombres());
            clienteCurrent.setApellidos(clienteDTO.getApellidos());
            clienteCurrent.setDni(clienteDTO.getDni());
            clienteCurrent.setTelefono(clienteDTO.getTelefono());
            clienteCurrent.setEmail(clienteDTO.getEmail());
    
            clienteRepository.save(clienteCurrent);
            
            return clienteConversion.convertirClienteDTO(clienteCurrent, true);
        }
        return null;
    }


    @Override
    public void deleteById(Long id){
        clienteRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(boolean cargarCompras){
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes.stream()
            .map(cliente -> clienteConversion.convertirClienteDTO(cliente, cargarCompras))
            .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id, boolean cargarCompras){
        Optional<Cliente> clienteCurrentOptional = clienteRepository.findById(id);
    
        if (clienteCurrentOptional.isPresent()) {
            return clienteConversion.convertirClienteDTO(clienteCurrentOptional.get(), cargarCompras);
        } else {
            return null;
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
