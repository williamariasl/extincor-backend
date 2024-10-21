package com.example.demo.models.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detallecompras")
public class Detallecompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
