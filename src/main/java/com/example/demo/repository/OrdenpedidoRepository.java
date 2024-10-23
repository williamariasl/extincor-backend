package com.example.demo.repository;

import com.example.demo.models.entity.Ordenpedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenpedidoRepository extends JpaRepository<Ordenpedido, Long> {
}
