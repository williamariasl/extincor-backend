package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detallepedido")
public class Detallepedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private Produccion produccion;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private Ordenpedido ordenpedido;

    private float presion;

}
