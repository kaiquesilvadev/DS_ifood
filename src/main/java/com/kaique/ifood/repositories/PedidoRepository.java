package com.kaique.ifood.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.entities.Restaurante;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	/*
	 * join fetch no jpql serve para que apenas uma consulta seja feita retornando
	 * todos os relacionamentos de uma só vez, ao contrário da consulta sem o fetch
	 * que faz vários selects separadamente. mais detalhes do README do projeto
	 */
	
	//TODO: mudar consulta depois 

	@Query("FROM Pedido p " 
	+ "join fetch p.enderecoEntrega e "
	+ "join fetch e.cidade "
	+ "join fetch p.restaurante r "
	+ "LEFT join fetch r.cozinha "
	+ "LEFT join fetch r.formaPagamentos "
	+ "LEFT JOIN p.usuarioCliente"
	)
	List<Pedido> findAll();
	
	
	Optional<Pedido> validaRestaunteFP(Long restaunteId , Long formaPagamentoId);
}
