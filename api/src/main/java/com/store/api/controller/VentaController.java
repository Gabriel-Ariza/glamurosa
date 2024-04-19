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
    public List<VentaDTO> findAll() {
        return ventaService.findAll();
    }


    @GetMapping("/{id}")
    public VentaDTO findById(@PathVariable Long id) {
        return ventaService.findById(id);
    }


}
