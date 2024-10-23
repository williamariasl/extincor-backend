package com.example.demo.models.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "produccion")
public class Produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int cantidad_producida;
    private String producto_nombre;
    private String estado;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Detallepedido> detallepedidos;

    @ManyToOne
    @JoinColumn(name = "operario_id", insertable = false, updatable = false, nullable = false)
    private Operario operario;

}
