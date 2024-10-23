package com.example.demo.controller;


import com.example.demo.models.dto.OrdenPedidoDTO;
import com.example.demo.models.entity.Ordenpedido;
import com.example.demo.services.OrdenPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenespedidos")
public class controllerOrdenPedido {

    @Autowired
    private OrdenPedidoService ordenPedidoService;

    @GetMapping
    public List<OrdenPedidoDTO> getOrdenPedidos() {
        return ordenPedidoService.findAll();
    }

    @GetMapping("/{id}")
    public OrdenPedidoDTO getOrdenPedidoById(@RequestParam Long id) {
        return ordenPedidoService.findById(id);
    }

    @PostMapping
    public OrdenPedidoDTO createOrdenPedido(@RequestBody OrdenPedidoDTO ordenPedidoDTO) {
        return ordenPedidoService.save(ordenPedidoDTO);
    }

    @PutMapping("/{id}")
    public OrdenPedidoDTO updateOrdenPedido(@PathVariable Long id, @RequestBody OrdenPedidoDTO ordenPedidoDTO) {
        ordenPedidoDTO.setId(id);
        return ordenPedidoService.save(ordenPedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdenPedido(@PathVariable Long id) {
        ordenPedidoService.delete(id);
    }
}
