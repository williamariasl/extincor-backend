package com.example.demo.models.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ordenpedido")
public class Ordenpedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long io;

    private Date fecha_pedido;
    private String estado_pedido;
    private float monto_total;
    private Date fecha_entrega;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Detallepedido> detallepedidos;
}
