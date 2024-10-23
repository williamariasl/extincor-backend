package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity // Add the @Entity annotation to mark this as a JPA entity

@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private float capacidad;
    private int precio;
    private Date fecha_fabricacion;
    private String estado;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Detallepedido> detallepedidos;

   /* @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Insumo insumo;*/

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Envace> envaces;

    public Producto() {}

    public Producto(long id, String nombre, String tipo, float capacidad, int precio, Date fecha_fabricacion, String estado, List<Detallepedido> detallepedidos) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.fecha_fabricacion = fecha_fabricacion;
        this.estado = estado;
        this.detallepedidos = detallepedidos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }

    public Date getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(Date fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Detallepedido> getDetallepedidos() {
        return detallepedidos;
    }

    public void setDetallepedidos(List<Detallepedido> detallepedidos) {
        this.detallepedidos = detallepedidos;
    }
}
