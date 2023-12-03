package com.kaique.ifood.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaique.ifood.entities.Grupo;

public interface GrupoRepositorie extends JpaRepository<Grupo, Long> {

	@Query("FROM Grupo g "
			+ "left Join fetch g.permissoes")
	List<Grupo> findAll();
	
	@Query("FROM Grupo g "
			+ "LEFT JOIN FETCH g.permissoes WHERE g.id = :id")
	Optional<Grupo> findById(@Param("id") Long id);
}
