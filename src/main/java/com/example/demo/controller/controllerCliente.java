package com.example.demo.controller;

import com.example.demo.models.dto.ClienteDTO;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class controllerCliente {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO getById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) {return clienteService.save(clienteDTO);}

    @PostMapping("/{id}")
    public ClienteDTO update(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        clienteDTO.setId(id);
        return clienteService.save(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { clienteService.delete(id); }
}
