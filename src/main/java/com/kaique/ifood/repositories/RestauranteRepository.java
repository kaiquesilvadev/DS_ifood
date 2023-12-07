package com.kaique.ifood.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaique.ifood.entities.Restaurante;

public interface RestauranteRepository
		extends JpaRepository<Restaurante, Long>, RestaurantesRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	@Query("FROM Restaurante r left join fetch r.cozinha")
	List<Restaurante> findAll();

	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	@Query("FROM Restaurante r "
	        + "JOIN FETCH r.formaPagamentos f "
	        + "WHERE r.id = :restauranteId AND f.id = :formaPagamentoId")
	Optional<Restaurante> validaRestauranteFP(@Param("restauranteId") Long restauranteId, @Param("formaPagamentoId") Long formaPagamentoId);

	@Query("FROM Restaurante r " 
			+ "JOIN FETCH r.produtos p "
			+ "WHERE r.id = :restauranteId AND p.id = :produtoId")
	Optional<Restaurante> validaProduto(@Param("restauranteId") Long restauranteId ,@Param("produtoId") Long produtoId);

	/*
	 * nessa consulta agora esta em um arquivo xlm na pasta META-INF para treino e
	 * organização
	 */
	// @Query("SELECT r FROM Restaurante r WHERE r.nome LIKE %:nome% AND
	// r.cozinha.id = :id")
	//List<Restaurante> consultarPorNome(@Param("nome") String nome, @Param("id") BigDecimal id);

	/*
	 * esse método tem como intuído fazer a mesma busca que o de cima mais sem a
	 * necessidade de usar o @Query
	 */

	// List<Restaurante> findByNomeContainingAndCozinhaId(String nome, BigDecimal
	// id);
}
