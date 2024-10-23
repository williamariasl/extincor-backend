package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String usuario;
    private String correo;
    private String telefono;
    private String role;


    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Ordenpedido> ordenpedidos;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Compra> compras;
}
