package com.example.demo.services;

import com.example.demo.models.dto.OrdenPedidoDTO;

import java.util.List;

public interface OrdenPedidoService {
    List<OrdenPedidoDTO> findAll();
    OrdenPedidoDTO findById(Long id);
    OrdenPedidoDTO save(OrdenPedidoDTO ordenPedidoDTO);
    void delete(Long ordenPedidoDTO);
}
