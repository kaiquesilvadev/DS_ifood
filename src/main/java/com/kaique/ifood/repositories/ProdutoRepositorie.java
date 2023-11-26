package com.kaique.ifood.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaique.ifood.entities.Produto;

public interface ProdutoRepositorie extends JpaRepository<Produto, Long> {

	@Query("from Produto where id = :produtoId and restaurante.id = :restauranteId")
	Optional<Produto> buscaProdutoPorIdEmRestaurante(@Param("produtoId") Long produtoId, @Param("restauranteId") Long restauranteId);

}
