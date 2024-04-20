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
import com.store.api.config.VentaConversion;
import com.store.api.exception.NombreInvalidoException;
import com.store.api.exception.NotFoundElementsException;
import com.store.api.exception.TipoDatoIncorrectoException;
import com.store.api.repository.ClienteRepository;
import com.store.api.repository.VentaRepository;
import com.store.api.repository.entity.Cliente;
import com.store.api.repository.entity.Venta;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.service.VentaService;

@Service
@AllArgsConstructor
public class VentaImplService implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    private VentaConversion ventaConversion;
    private ClienteRepository ClienteRepository;


    @Override
    @Transactional
    public VentaDTO save(VentaDTO ventaDTO) {
        try {
            Venta ventaCurrent = ventaConversion.convertirDTOVenta(ventaDTO);
            ventaRepository.save(ventaCurrent);
            return ventaConversion.convertirVentaDTO(ventaCurrent, true);
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
    public VentaDTO update(Long id, VentaDTO ventaDTO) {
        try {
            Optional<Venta> ventaCurrentOptional = ventaRepository.findById(id);
            if (ventaCurrentOptional.isPresent()) {
                Venta ventaCurrent = ventaCurrentOptional.get();
                if (ventaDTO.getTotal() != null) {
                    ventaCurrent.setTotal(ventaDTO.getTotal());
                }
                if (ventaDTO.getId_cliente() != null) {
                    if (ventaCurrent.getCliente() != null) {
                        throw new IllegalArgumentException("No se puede editar el cliente de una venta que ya tiene un cliente asociado");
                    }
                    Optional<Cliente> clienteOptional = ClienteRepository.findById(ventaDTO.getId_cliente());
                    if (clienteOptional.isPresent()) {
                        ventaCurrent.setCliente(clienteOptional.get());
                    } else {
                        throw new NotFoundElementsException("Cliente con id " + ventaDTO.getId_cliente() + " no encontrado");
                    }
                }
                ventaRepository.save(ventaCurrent);
                return ventaConversion.convertirVentaDTO(ventaCurrent, true);
            } else {
                throw new NotFoundElementsException("Venta no encontrada");
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
            ventaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundElementsException("Venta no encontrada", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> findAll(boolean cargarCliente){
        try {
            List<Venta> ventas = (List<Venta>) ventaRepository.findAll();
            return ventas.stream()
                        .map(venta -> ventaConversion.convertirVentaDTO(venta, cargarCliente))
                        .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public VentaDTO findById(Long id, boolean cargarCliente){
        try {
            Optional<Venta> ventaCurrentOptional = ventaRepository.findById(id);

            if (ventaCurrentOptional.isPresent()) {
                return ventaConversion.convertirVentaDTO(ventaCurrentOptional.get(), cargarCliente);
            } else {
                throw new NotFoundElementsException("Venta no encontrada");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado", ex);
        }
    }

}
