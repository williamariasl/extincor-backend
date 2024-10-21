package com.example.demo.services;

import com.example.demo.models.dto.EnvaceDTO;

import java.util.List;

public interface EnvaceService {
    List<EnvaceDTO> findAll();
    EnvaceDTO findById(Long id);
    EnvaceDTO save(EnvaceDTO envaceDTO);
    void delete(Long id);
}
