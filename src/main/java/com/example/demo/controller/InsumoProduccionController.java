package com.example.demo.controller;

import com.example.demo.models.dto.InsumoProduccionDTO;
import com.example.demo.services.InsumoProduccionService;
import com.example.demo.services.InsumoService;
import com.example.demo.services.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos-produccion")
public class InsumoProduccionController {

    @Autowired
    private InsumoProduccionService insumoProduccionService;

    @Autowired
    private InsumoService insumoService; // Inyectar servicio de Insumo

    @Autowired
    private ProduccionService produccionService; // Inyectar servicio de Produccion

    @GetMapping
    public String listInsumosProduccion(Model model) {
        model.addAttribute("insumosProduccion", insumoProduccionService.findAll());
        model.addAttribute("insumoProduccion", new InsumoProduccionDTO()); // Objeto para el formulario
        model.addAttribute("insumos", insumoService.findAll()); // Lista de insumos para el formulario
        model.addAttribute("producciones", produccionService.findAll()); // Lista de producciones para el formulario
        return "insumos-produccion"; // Vista combinada
    }

    @PostMapping("/guardar")
    public String saveOrUpdateInsumoProduccion(@ModelAttribute InsumoProduccionDTO insumoProduccionDTO) {
        insumoProduccionService.save(insumoProduccionDTO);
        return "redirect:/insumos-produccion";
    }

    @GetMapping("/editar/{id}")
    public String editInsumoProduccion(@PathVariable Long id, Model model) {
        model.addAttribute("insumosProduccion", insumoProduccionService.findAll());
        model.addAttribute("insumoProduccion", insumoProduccionService.findById(id)); // Objeto específico para edición
        model.addAttribute("insumos", insumoService.findAll()); // Lista de insumos para selección
        model.addAttribute("producciones", produccionService.findAll()); // Lista de producciones para selección
        return "insumos-produccion";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteInsumoProduccion(@PathVariable Long id) {
        insumoProduccionService.deleteById(id);
        return "redirect:/insumos-produccion";
    }
}
