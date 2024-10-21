package com.example.demo.services.impl;


import com.example.demo.models.entity.Envace;
import com.example.demo.models.dto.EnvaceDTO;
import com.example.demo.repository.EnvaceRepository;
import com.example.demo.services.EnvaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvaceServiceImpl implements EnvaceService {

    @Autowired
    private EnvaceRepository envaceRepository;

    @Override
    public List<EnvaceDTO> findAll() {
        return envaceRepository.findAll()
                .stream()
                    .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnvaceDTO findById(Long id) {
        return envaceRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public EnvaceDTO save(EnvaceDTO envaceDTO) {
        Envace envace = convertToEntity(envaceDTO);
        envace = envaceRepository.save(envace);
        return convertToDTO(envace);
    }

    @Override
    public void delete(Long id) {
        envaceRepository.deleteById(id);
    }

    private EnvaceDTO convertToDTO(Envace envace) {
        EnvaceDTO dto = new EnvaceDTO();
        dto.setId(envace.getId());

        return dto;
    }

    private Envace convertToEntity(EnvaceDTO dto) {
        Envace envace = new Envace();
        envace.setId(dto.getId());

        return envace;
    }
}
