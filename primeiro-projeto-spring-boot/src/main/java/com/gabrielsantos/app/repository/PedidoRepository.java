package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
