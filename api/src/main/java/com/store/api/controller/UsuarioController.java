package com.store.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.store.api.repository.entityDTO.UsuarioDTO;
import com.store.api.service.UsuarioService;



@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/")
    public UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.save(usuarioDTO);
    }


    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.update(id, usuarioDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }


    @GetMapping("/")
    public List<UsuarioDTO> findAll(@RequestParam(required = false, defaultValue = "false") boolean cargarCliente) {
        return usuarioService.findAll(cargarCliente);
    }


    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean cargarCliente) {
        return usuarioService.findById(id, cargarCliente);
    }


    @GetMapping("/cargarClientes")
    public List<UsuarioDTO> cargarClientes() {
        return usuarioService.findAllcargandoClientesAndCompras();
    }


    @GetMapping("/cargarCliente/{id}")
    public Optional<UsuarioDTO> cargarClientesById(@PathVariable Long id) {
        return usuarioService.findByIdCargandoClienteAndCompras(id);
    }
}