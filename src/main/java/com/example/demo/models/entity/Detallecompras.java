package com.example.demo.models.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detallecompras")
public class Detallecompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insumo_id", insertable = false, updatable = false, nullable = false)
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "compra_id",insertable = false, updatable = false, nullable = false)
    private Compra compra;
}
