package com.example.demo.repository;

import com.example.demo.models.entity.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduccionRepository extends JpaRepository<Produccion,Long> {
}
