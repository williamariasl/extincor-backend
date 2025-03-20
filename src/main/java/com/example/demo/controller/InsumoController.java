package com.example.demo.controller;

import com.example.demo.models.dto.InsumoDTO;
import com.example.demo.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public String listInsumos(Model model) {
        model.addAttribute("insumos", insumoService.findAll());
        model.addAttribute("insumo", new InsumoDTO()); // Añadir objeto vacío para el formulario
        return "insumos";
    }

    @PostMapping
    public String saveOrUpdateInsumo(@ModelAttribute InsumoDTO insumoDTO) {
        insumoService.save(insumoDTO);
        return "redirect:/insumos";
    }

  
    

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("insumos", insumoService.findAll());
        model.addAttribute("insumo", insumoService.findById(id)); // Cargar insumo existente para editar
        return "insumos";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteInsumo(@PathVariable Long id) {
        insumoService.deleteById(id);
        return "redirect:/insumos";
    }
}
