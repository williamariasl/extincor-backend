package com.example.demo.services;

import com.example.demo.models.dto.ClienteDTO;


import java.util.List;

public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(Long id);
    ClienteDTO save(ClienteDTO clienteDTO);
    void delete(Long id);
}
