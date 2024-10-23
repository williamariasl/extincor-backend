package com.example.demo.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrdenPedidoDTO {

    private Long id;
    private String estado_pedido;
    private Date fecha_entrega;
    private Date fecha_pedido;
    private float monto_total;
    private Long administrador_id;
    private Long cliente_id;

    public OrdenPedidoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(String estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Long getAdministrador_id() {
        return administrador_id;
    }

    public void setAdministrador_id(Long administrador_id) {
        this.administrador_id = administrador_id;
    }

    public float getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(float monto_total) {
        this.monto_total = monto_total;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
