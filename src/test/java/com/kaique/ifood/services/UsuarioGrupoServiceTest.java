package com.kaique.ifood.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kaique.ifood.entities.Grupo;
import com.kaique.ifood.entities.Usuario;
import com.kaique.ifood.exception.UsuarioNaoEncontradoException;
import com.kaique.ifood.repositories.UsuarioRepositorie;
import com.kaique.ifood.services.factory.GrupoFactory;
import com.kaique.ifood.services.factory.UserFactory;

@ExtendWith(SpringExtension.class)
public class UsuarioGrupoServiceTest {

	@InjectMocks
	private UsuarioGrupoService serviceTest;

	@Mock
	private GrupoService grupoService;

	@Mock
	private UsuarioRepositorie usuarioRepositorie;

	@Mock
	private UsuarioService usuarioService;

	private Long idExistenteDeUser, idUserInexistente;
	private Usuario usuario;
	private Grupo grupo1, grupo2;
	private List<Grupo> grupos;

	@BeforeEach
	void setup() throws Exception {

		idExistenteDeUser = 1l;
		idUserInexistente = 100000l;
		usuario = UserFactory.createUser();
		grupo1 = GrupoFactory.createUse1();
		grupo2 = GrupoFactory.createUse2();
		grupos = Arrays.asList(grupo1, grupo2);
		
		Mockito.when(usuarioService.buscarPorId(idExistenteDeUser)).thenReturn(usuario);
		Mockito.doThrow(UsuarioNaoEncontradoException.class).when(usuarioService).buscarPorId(idUserInexistente);

		Mockito.when(usuarioRepositorie.listaGruposDeUsuario(idExistenteDeUser)).thenReturn(grupos);

	}

	@Test
	public void ListaGruposDeveRetornaExceptionQuandoIdDeUserForInvalido() {

		assertThrows(UsuarioNaoEncontradoException.class, () -> {
			serviceTest.ListaGrupos(idUserInexistente);
		});
	}
	
	@Test
	public void ListaGruposDeveRetornaLIstadeGrupoQuandoidForExistente() {

		    List<Grupo> ListGrupos = serviceTest.ListaGrupos(idExistenteDeUser);
		    
		    assertNotNull(ListGrupos);
	}
}
