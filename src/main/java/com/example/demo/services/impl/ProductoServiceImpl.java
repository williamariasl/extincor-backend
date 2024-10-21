package com.example.demo.services.impl;


import com.example.demo.models.dto.ProductoDTO;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


}
