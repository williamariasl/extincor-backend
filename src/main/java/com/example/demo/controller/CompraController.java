package com.example.demo.controller;

import com.example.demo.models.dto.CompraDTO;
import com.example.demo.services.AdministradorService;
import com.example.demo.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private AdministradorService administradorService;


    @GetMapping
    public String listCompras(Model model) {
        model.addAttribute("compras", compraService.findAll());
        model.addAttribute("compra", new CompraDTO());
        return "compras";
    }

    @PostMapping
    public String saveOrUpdateCompra(@ModelAttribute CompraDTO compraDTO) {
        compraService.save(compraDTO);
        return "redirect:/compras";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("compras", compraService.findAll());
        model.addAttribute("compra", compraService.findById(id));
        return "compras";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCompra(@PathVariable Long id) {
        compraService.deleteById(id);
        return "redirect:/compras";
    }
}
