package com.example.demo.controller;

import com.example.demo.models.dto.CompraDTO;
import com.example.demo.models.dto.DetalleCompraDTO;
import com.example.demo.models.dto.InsumoDTO;
import com.example.demo.services.CompraService;
import com.example.demo.services.DetalleCompraService;
import com.example.demo.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detalles-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public String listDetalles(Model model) {
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        model.addAttribute("detalle", detalleCompraDTO);
        model.addAttribute("detalles", detalleCompraService.findAll());
        model.addAttribute("compras", compraService.findAll());
        model.addAttribute("insumos", insumoService.findAll());
        return "detalles-compra";
    }

    @PostMapping("/guardar")
    public String saveOrUpdateDetalle(@ModelAttribute DetalleCompraDTO detalleCompraDTO) {
        detalleCompraService.save(detalleCompraDTO);
        return "redirect:/detalles-compra";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DetalleCompraDTO detalle = detalleCompraService.findById(id);

        // Asegura que los campos ID no sean null para evitar problemas en la vista
        if (detalle.getCompraId() == null) {
            detalle.setCompraId(null); // Establece compraId en lugar de crear CompraDTO
        }
        if (detalle.getInsumo() == null) {
            detalle.setInsumo(new InsumoDTO());
        }

        model.addAttribute("detalles", detalleCompraService.findAll());
        model.addAttribute("detalle", detalle);
        model.addAttribute("compras", compraService.findAll());
        model.addAttribute("insumos", insumoService.findAll());
        return "detalles-compra";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteDetalle(@PathVariable Long id) {
        detalleCompraService.deleteById(id);
        return "redirect:/detalles-compra";
    }
}
