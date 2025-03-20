package com.example.demo.controller;

import com.example.demo.exceptions.CorreoYaExisteException;
import com.example.demo.models.dto.ClienteDTO;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("cliente", new ClienteDTO());
        return "clientes";
    }

    @ExceptionHandler(CorreoYaExisteException.class)
    public String handleCorreoYaExisteException(CorreoYaExisteException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("cliente", new ClienteDTO());
        return "clientes"; // Retorna a la página de clientes con el mensaje de error
    }

    @PostMapping
    public String saveOrUpdateCliente(@ModelAttribute ClienteDTO clienteDTO, Model model) {
        try {
            clienteService.save(clienteDTO);
            return "redirect:/clientes";
        } catch (CorreoYaExisteException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("cliente", clienteDTO); // Mantiene los datos ingresados en el formulario
            return "clientes"; // Vuelve a la página de clientes con el error
        }
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("cliente", clienteService.findById(id));
        return "clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes";
    }
}
