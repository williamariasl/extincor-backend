package com.example.demo.models.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ProduccionDTO {
    private Long id;
    private String producto_nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
}
