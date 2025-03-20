package com.example.demo.models.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String password; // Campo de contraseña
    private String tipoUsuario; // Campo para el tipo de usuario (por ejemplo, "ADMINISTRADOR", "CLIENTE")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion = new Date(); // Fecha de creación predeterminada a la fecha actual
}
