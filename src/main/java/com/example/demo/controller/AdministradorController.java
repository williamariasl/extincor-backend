package com.example.demo.controller;

import com.example.demo.exceptions.CorreoYaExisteException;
import com.example.demo.models.dto.AdministradorDTO;
import com.example.demo.services.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    /**
     * Listar administradores y mostrar formulario vacío
     */
    @GetMapping
    public String listAdministradores(Model model) {
        model.addAttribute("administradores", administradorService.findAll());
        model.addAttribute("administrador", new AdministradorDTO()); // Objeto vacío para el formulario
        return "administradores";
    }

    /**
     * Mostrar formulario para editar un administrador
     */
 
@GetMapping("/editar/{id}")
public String showEditForm(@PathVariable Long id, Model model) {
    AdministradorDTO administrador = administradorService.findById(id);
    if (administrador == null) {
        return "redirect:/administradores?error=Administrador no encontrado";
    }
    model.addAttribute("administrador", administrador); // Administrador cargado para editar
    model.addAttribute("administradores", administradorService.findAll()); // Lista de todos los administradores
    return "administradores";
}
    

    /**
     * Guardar administrador (crear o actualizar)
     */
    @PostMapping("/guardar")
    public String saveAdministrador(
            @Valid @ModelAttribute("administrador") AdministradorDTO administradorDTO,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("administradores", administradorService.findAll());
            return "administradores"; // Mostrar errores en la misma vista
        }
    
        try {
            administradorService.save(administradorDTO);
        } catch (CorreoYaExisteException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("administradores", administradorService.findAll());
            return "administradores";
        }
    
        return "redirect:/administradores";
    }
    
    /**
     * Eliminar administrador
     */
    @GetMapping("/eliminar/{id}")
    public String deleteAdministrador(@PathVariable Long id) {
        administradorService.deleteById(id);
        return "redirect:/administradores";
    }
}
