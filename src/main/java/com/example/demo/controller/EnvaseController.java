package com.example.demo.controller;

import com.example.demo.models.dto.EnvaseDTO;
import com.example.demo.services.EnvaseService;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/envases")
public class    EnvaseController {

    @Autowired
    private EnvaseService envaseService;

    @Autowired
    private ProductoService productoService; // Inyecta ProductoService para cargar productos

    @GetMapping
    public String listEnvases(Model model) {
        model.addAttribute("envases", envaseService.findAll());
        model.addAttribute("envase", new EnvaseDTO()); // Se usa para el formulario de creación/edición
        model.addAttribute("productos", productoService.findAll()); // Cargar productos para el formulario
        return "envase"; // Vista única para el CRUD
    }

    @PostMapping
    public String saveEnvase(@ModelAttribute EnvaseDTO envaseDTO) {
        envaseService.save(envaseDTO);
        return "redirect:/envases";
    }

    @GetMapping("/editar/{id}")
    public String editEnvase(@PathVariable Long id, Model model) {
        model.addAttribute("envases", envaseService.findAll());
        model.addAttribute("envase", envaseService.findById(id)); // Cargar envase en el formulario para editar
        model.addAttribute("productos", productoService.findAll()); // Cargar productos para el formulario
        return "envase";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEnvase(@PathVariable Long id) {
        envaseService.deleteById(id);
        return "redirect:/envases";
    }
}
