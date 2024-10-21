package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "envace")
public class Envace {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String material;
    private String capacidad;
    private float peso;
    private String estado;



}
