package com.example.demo.controller;

import com.example.demo.exceptions.CorreoYaExisteException;
import com.example.demo.models.dto.UsuarioDTO;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listUsuarios(Model model, Authentication authentication) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("usuario", new UsuarioDTO()); // Objeto vacío para el formulario
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "usuarios";
    }

    @PostMapping
    public String saveOrUpdateUsuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        try {
            usuarioService.save(usuarioDTO); // Intenta guardar el usuario
            return "redirect:/usuarios";
        } catch (CorreoYaExisteException ex) {
            // Manejo de la excepción si el correo ya existe
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("usuarios", usuarioService.findAll());
            model.addAttribute("usuario", usuarioDTO); // Mantener los datos ingresados
            return "usuarios"; // Regresa a la vista de usuarios con el mensaje de error
        }
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("usuario", usuarioService.findById(id)); // Cargar usuario existente para editar
        return "usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }

    // Manejo de la excepción para correo duplicado
    @ExceptionHandler(CorreoYaExisteException.class)
    public String handleCorreoYaExisteException(CorreoYaExisteException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuarios";
    }
}
