package com.kaique.ifood.services.factory;

import java.util.Arrays;

import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Usuario;

public class GrupoFactory {

	/* TODO ; trocar o nulo por um obj real depois */

	public static Grupo createUse1() {
		Grupo grupo = new Grupo();
		grupo.setId(1L);
		grupo.setNome("Admin");
		grupo.setPermissoes(null);

		return grupo;
	}
	
	public static Grupo createUse2() {
		Grupo grupo = new Grupo();
		grupo.setId(1L);
		grupo.setNome("User");
		grupo.setPermissoes(null);

		return grupo;
	}
}
