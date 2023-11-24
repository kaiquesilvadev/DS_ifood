package com.kaique.ifood.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.ifood.entities.Usuario;

public interface UsuarioRepositorie extends JpaRepository<Usuario, Long>{

    Boolean existsByEmail(String email);
    
    Boolean existsBySenha(String senha);
    
    Optional<Usuario> findByEmail(String email);;
}
