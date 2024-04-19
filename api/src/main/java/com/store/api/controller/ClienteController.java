package com.store.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.store.api.repository.entityDTO.ClienteDTO;
import com.store.api.service.ClienteService;



@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/")
    public ClienteDTO save(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.save(clienteDTO);
    }


    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.update(id, clienteDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
    }


    @GetMapping("/")
    public List<ClienteDTO> findAll(@RequestParam(required = false, defaultValue = "false") boolean cargarCompras) {
        return clienteService.findAll(cargarCompras);
    }
    /*
    http://localhost:8080/clientes/?cargarCompras=true
    */


    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean cargarCompras) {
        return clienteService.findById(id, cargarCompras);
    }
    /*
    http://localhost:8080/clientes/1?cargarCompras=true
    */


    @GetMapping("/cantidad")
    public List<ClienteDTO> clientesXcantidadCompras(@RequestParam Long compras) {
        return clienteService.clientesXcantidadCompras(compras, false);
    }
    /*
    http://localhost:8080/clientes/cantidad?compras=2
    */


    @GetMapping("/compras")
    public List<ClienteDTO> clientesXcantidadComprasCargado(@RequestParam Long cantidad) {
        return clienteService.clientesXcantidadCompras(cantidad, true);
    }
    /*
    http://localhost:8080/clientes/compras?cantidad=2
    */




}