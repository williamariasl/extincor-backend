package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha_compra;
    private String proveedor;
    private float monto;
    private String estado;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Detallecompras> detallecompras;

    @ManyToOne
    @JoinColumn(name = "administrador_id", insertable = false, updatable = false, nullable = false)
    private Administrador administrador;
}
