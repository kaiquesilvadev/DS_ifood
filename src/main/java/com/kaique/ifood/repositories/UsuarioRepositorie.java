package com.kaique.ifood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.ifood.entities.Usuario;

public interface UsuarioRepositorie extends JpaRepository<Usuario, Long>{

}
