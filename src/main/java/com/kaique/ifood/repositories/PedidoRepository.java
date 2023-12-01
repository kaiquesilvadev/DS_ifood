package com.kaique.ifood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.ifood.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
