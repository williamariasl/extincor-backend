package com.example.demo.controller;

import com.example.demo.models.dto.OrdenPedidoDTO;
import com.example.demo.services.OrdenPedidoService;
import com.example.demo.services.ClienteService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ordenes")
public class OrdenPedidoController {

    @Autowired
    private OrdenPedidoService ordenPedidoService;

    @Autowired
    private ClienteService clienteService;

    // Listar órdenes
    @GetMapping
    public String listOrdenes(Model model) {
        model.addAttribute("ordenes", ordenPedidoService.findAll());
        model.addAttribute("orden", new OrdenPedidoDTO());
        model.addAttribute("clientes", clienteService.findAll());
        return "ordenes";
    }

    // Guardar o actualizar una orden
    @PostMapping("/guardar")
    public String saveOrUpdateOrden(@ModelAttribute("orden") OrdenPedidoDTO ordenPedidoDTO, Model model) {
        try {
            ordenPedidoService.save(ordenPedidoDTO);
            return "redirect:/ordenes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la orden.");
            model.addAttribute("ordenes", ordenPedidoService.findAll());
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("orden", ordenPedidoDTO);
            return "ordenes";
        }
    }

    // Editar una orden
    @GetMapping("/editar/{id}")
    public String editOrden(@PathVariable Long id, Model model) {
        OrdenPedidoDTO ordenPedidoDTO = ordenPedidoService.findById(id);
        if (ordenPedidoDTO == null) {
            return "redirect:/ordenes";
        }
        model.addAttribute("orden", ordenPedidoDTO);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("ordenes", ordenPedidoService.findAll());
        return "ordenes";
    }

    // Eliminar una orden
    @GetMapping("/eliminar/{id}")
    public String deleteOrden(@PathVariable Long id) {
        ordenPedidoService.deleteById(id);
        return "redirect:/ordenes";
    }

    // Cambiar el estado de la orden (Activo, Inactivo, Pendiente)
    @PostMapping("/cambiar-estado")
    @ResponseBody
    public ResponseEntity<?> cambiarEstado(@RequestParam Long id, @RequestParam String nuevoEstado) {
        try {
            OrdenPedidoDTO ordenActualizada = ordenPedidoService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(ordenActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cambiar el estado de la orden.");
        }
    }
    

    // Actualizar la fecha de entrega
    @PostMapping("/actualizar-fecha-entrega")
    @ResponseBody
    public ResponseEntity<?> actualizarFechaEntrega(@RequestParam Long id, @RequestParam String nuevaFechaEntrega) {
        try {
            Date nuevaFecha = Date.valueOf(nuevaFechaEntrega); // Convertir String a Date
            OrdenPedidoDTO ordenActualizada = ordenPedidoService.actualizarFechaEntrega(id, nuevaFecha);
            return ResponseEntity.ok(ordenActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la fecha de entrega.");
        }
    }
}
