package com.kaique.ifood.services.factory;

import java.time.OffsetDateTime;
import java.util.Arrays;

import com.kaique.ifood.entities.Usuario;

public class UserFactory {
	
	 public static Usuario createUser() {
		  Usuario usuario = new Usuario();
	        usuario.setId(1L);
	        usuario.setNome("João Silva");
	        usuario.setEmail("joao.silva@example.com");
	        usuario.setSenha("senha123");
	        usuario.setDataCadastro(OffsetDateTime.now());
	        usuario.setGrupos(Arrays.asList(GrupoFactory.createUse1() , GrupoFactory.createUse2()));

	        return usuario;
	    }
}
