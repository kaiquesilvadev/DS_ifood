package com.kaique.ifood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.ifood.entities.Produto;

public interface ProdutoRepositorie extends JpaRepository<Produto, Long> {

}
