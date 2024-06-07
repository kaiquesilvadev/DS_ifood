package com.kaique.ifood.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Usuario;

public interface UsuarioRepositorie extends JpaRepository<Usuario, Long>{

    Boolean existsByEmail(String email);
    
    Boolean existsBySenha(String senha);
    
    Optional<Usuario> findByEmail(String email);
    
    @Query("FROM Usuario u "
    		+ "Left join fetch u.grupos ")
    List<Usuario> findAll();
    
    @Query("SELECT u FROM Usuario u "
    		+ "LEFT JOIN FETCH u.grupos WHERE u.id = :usuarioId")
    Optional<Usuario> findById(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT u.grupos FROM Usuario u WHERE u.id = :usuarioId")
    List<Grupo> listaGruposDeUsuario(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT u FROM Usuario u JOIN FETCH u.grupos WHERE u.email =:email")
	Usuario validaEmail(String email);
    
}
