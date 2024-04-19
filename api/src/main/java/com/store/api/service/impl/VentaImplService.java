package com.store.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import com.store.api.config.VentaConversion;
import com.store.api.repository.VentaRepository;
import com.store.api.repository.entity.Venta;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.service.VentaService;

@Service
@AllArgsConstructor
public class VentaImplService implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    private VentaConversion ventaConversion;

    @Override
    @Transactional
    public VentaDTO save(VentaDTO ventaDTO) {
        Venta ventaCurrent = ventaConversion.convertirDTOVenta(ventaDTO);
        ventaRepository.save(ventaCurrent);
        return ventaConversion.convertirVentaDTO(ventaCurrent, true);
    }

    @Override
    public VentaDTO update(Long id, VentaDTO ventaDTO) {
        Optional<Venta> ventaCurrentOptional = ventaRepository.findById(id);
        if (ventaCurrentOptional.isPresent()) {
            Venta ventaCurrent = ventaConversion.convertirDTOVenta(ventaDTO);
            ventaCurrent.setId_venta(id);
            // Set other attributes as needed
            ventaRepository.save(ventaCurrent);
            return ventaConversion.convertirVentaDTO(ventaCurrent, true);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> findAll(boolean cargarCliente){
        List<Venta> ventas = (List<Venta>) ventaRepository.findAll();
        return ventas.stream()
                    .map(venta -> ventaConversion.convertirVentaDTO(venta, cargarCliente))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VentaDTO findById(Long id, boolean cargarCliente){
        Optional<Venta> ventaCurrentOptional = ventaRepository.findById(id);

        if (ventaCurrentOptional.isPresent()) {
            return ventaConversion.convertirVentaDTO(ventaCurrentOptional.get(), cargarCliente);
        } else {
            return null;
        }
    }

}
