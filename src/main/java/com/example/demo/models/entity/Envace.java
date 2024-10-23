package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "envace")
public class Envace {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String material;
    private String capacidad;
    private float peso;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "ordenpedido_id", insertable = false, updatable = false, nullable = false)
    private Ordenpedido ordenpedido;


    public Envace() {}

    public Envace(String material, String capacidad, float peso, String estado) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Ordenpedido getOrdenpedido() {
        return ordenpedido;
    }

    public void setOrdenpedido(Ordenpedido ordenpedido) {
        this.ordenpedido = ordenpedido;
    }
}
