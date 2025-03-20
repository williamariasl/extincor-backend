package com.example.demo.controller;

import com.example.demo.exceptions.CorreoYaExisteException;
import com.example.demo.models.dto.OperarioIngresoDto;
import com.example.demo.services.OperarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operarios")
public class OperarioController {

    @Autowired
    private OperarioService operarioService;

    // Muestra la lista de operarios y el formulario de creación
    @GetMapping
    public String listOperarios(Model model) {
        model.addAttribute("operarios", operarioService.findAll());
        model.addAttribute("operario", new OperarioIngresoDto());
        return "operarios";
    }
    

    @GetMapping("/ventanaoperario")
    public String ventanaOperario(@RequestParam("id") Long id, Model model) {
        OperarioIngresoDto operario = operarioService.findById(id);
        if (operario != null) {
            model.addAttribute("operario", operario);
            return "ventanaoperario"; // Debe coincidir con el nombre de la plantilla
        } else {
            return "errorPage"; // Muestra una página de error si no encuentra el operario
        }
    }



    // Guarda o actualiza un operario
    @PostMapping("/guardar")
    public String saveOrUpdateOperario(@ModelAttribute OperarioIngresoDto operarioDTO, Model model) {
        try {
            operarioService.save(operarioDTO);
            return "redirect:/operarios";
        } catch (CorreoYaExisteException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("operarios", operarioService.findAll());
            model.addAttribute("operario", operarioDTO);
            return "operarios";
        }
    }
    

    // Edición de un operario
    @GetMapping("/editar/{id}")
    public String editOperario(@PathVariable Long id, Model model) {
        model.addAttribute("operarios", operarioService.findAll());
        model.addAttribute("operario", operarioService.findById(id));
        return "operarios";
    }
    
    // Eliminar un operario
    @GetMapping("/eliminar/{id}")
    public String deleteOperario(@PathVariable Long id) {
        operarioService.deleteById(id);
        return "redirect:/operarios";
    }
    

    // Maneja la excepción de correo duplicado
    @ExceptionHandler(CorreoYaExisteException.class)
    public String handleCorreoYaExisteException(CorreoYaExisteException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("operarios", operarioService.findAll());
        model.addAttribute("operario", new OperarioIngresoDto()); // Mantiene el formulario limpio
        return "operarios";
    }
}
