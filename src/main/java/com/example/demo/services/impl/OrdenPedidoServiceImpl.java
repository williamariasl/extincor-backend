package com.example.demo.services.impl;


import com.example.demo.models.entity.Administrador;
import com.example.demo.models.entity.Cliente;
import com.example.demo.models.entity.Ordenpedido;
import com.example.demo.models.dto.OrdenPedidoDTO;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.OrdenpedidoRepository;
import com.example.demo.services.OrdenPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenPedidoServiceImpl implements OrdenPedidoService {

    @Autowired
    private OrdenpedidoRepository ordenpedidoRepository;
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<OrdenPedidoDTO> findAll() {
        return ordenpedidoRepository.findAll()
                .stream()
                    .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenPedidoDTO findById(Long id) {
        return ordenpedidoRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public OrdenPedidoDTO save(OrdenPedidoDTO ordenPedidoDTO) {
        Ordenpedido ordenPedido = new Ordenpedido();
        ordenPedido.setFecha_pedido(ordenPedidoDTO.getFecha_pedido());
        ordenPedido.setFecha_entrega(ordenPedidoDTO.getFecha_entrega());
        ordenPedido.setEstado_pedido(ordenPedidoDTO.getEstado_pedido());
        ordenPedido.setMonto_total(ordenPedidoDTO.getMonto_total());

        // Obtener el administrador y cliente por ID y asignarlos
        Administrador administrador = administradorRepository.findById(ordenPedidoDTO.getAdministrador_id())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        Cliente cliente = clienteRepository.findById(ordenPedidoDTO.getCliente_id())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        ordenPedido.setAdministrador(administrador);
        ordenPedido.setCliente(cliente);

        ordenPedido = ordenpedidoRepository.save(ordenPedido);
        return convertToDTO(ordenPedido);
    }

    @Override
    public void delete(Long id) {
        ordenpedidoRepository.deleteById(id);
    }

    private OrdenPedidoDTO convertToDTO(Ordenpedido ordenPedido) {
        OrdenPedidoDTO dto = new OrdenPedidoDTO();
        dto.setId(ordenPedido.getId());
        dto.setFecha_pedido(ordenPedido.getFecha_pedido());
        dto.setEstado_pedido(ordenPedido.getEstado_pedido());
        dto.setFecha_entrega(ordenPedido.getFecha_entrega());
        dto.setMonto_total(ordenPedido.getMonto_total());
        dto.setCliente_id(ordenPedido.getCliente().getId());
        return dto;
    }

    private Ordenpedido convertToEntity(OrdenPedidoDTO dto) {
        Ordenpedido ordenPedido = new Ordenpedido();
        ordenPedido.setId(dto.getId());
        ordenPedido.setFecha_pedido(dto.getFecha_pedido());
        ordenPedido.setEstado_pedido(dto.getEstado_pedido());
        ordenPedido.setFecha_entrega(dto.getFecha_entrega());
        ordenPedido.setMonto_total(dto.getMonto_total());
        return ordenPedido;
    }
}
