package com.store.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.store.api.repository.entityDTO.VentaDTO;
import com.store.api.service.VentaService;



@AllArgsConstructor
@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;


    @PostMapping("/")
    public VentaDTO save(@RequestBody VentaDTO ventaDTO) {
        return ventaService.save(ventaDTO);
    }


    @PutMapping("/{id}")
    public VentaDTO update(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return ventaService.update(id, ventaDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ventaService.deleteById(id);
    }


    @GetMapping("/")
    public List<VentaDTO> findAll(@RequestParam(required = false, defaultValue = "false") boolean cargarCliente) {
        return ventaService.findAll(cargarCliente);
    }
    /*
    http://localhost:8080/ventas/?cargarCliente=true
    */


    @GetMapping("/{id}")
    public VentaDTO findById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean cargarCliente) {
        return ventaService.findById(id, cargarCliente);
    }
    /*
    http://localhost:8080/ventas/1/?cargarCliente=true
    */


}