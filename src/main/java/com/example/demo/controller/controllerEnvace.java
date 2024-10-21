package com.example.demo.controller;

import com.example.demo.models.dto.EnvaceDTO;
import com.example.demo.services.EnvaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envaces")
public class controllerEnvace {

    @Autowired
    private EnvaceService envaceService;

    @GetMapping
    public List<EnvaceDTO> getAll() {
        return envaceService.findAll();
    }

    @GetMapping("/{id}")
    public EnvaceDTO getById(@PathVariable Long id) {
        return envaceService.findById(id);
    }

    @PostMapping
    public EnvaceDTO create(@RequestBody EnvaceDTO envaceDTO) {
        return envaceService.save(envaceDTO);
    }

    @PutMapping("/{id}")
    public EnvaceDTO update(@PathVariable Long id, @RequestBody EnvaceDTO envaceDTO) {
        envaceDTO.setId(id);
        return envaceService.save(envaceDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        envaceService.delete(id);
    }
}