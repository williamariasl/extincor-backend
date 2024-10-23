package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "insumo")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @OneToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;*/

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Detallecompras> detallecompras;

    private int cantidad;
    private int unidades;
}
