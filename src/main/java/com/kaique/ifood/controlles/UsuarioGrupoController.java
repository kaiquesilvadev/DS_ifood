package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.UsuarioGrupoOpenAPI;
import com.kaique.ifood.dto.conversor.GrupoDtoConversor;
import com.kaique.ifood.dto.conversor.UsuarioDtoConversor;
import com.kaique.ifood.dto.responce.GrupoDtoResconse;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.services.UsuarioGrupoService;

@RestController
@RequestMapping("v1/usuario/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoOpenAPI{

	@Autowired
	private UsuarioGrupoService service;

	@Autowired
	private UsuarioDtoConversor conversor;
	
	@Autowired
	private GrupoDtoConversor conversorGrupo;
	
	@Override
	@GetMapping
	public List<GrupoDtoResconse> ListaGrupo(@PathVariable Long usuarioId) {
		return conversorGrupo.lista(service.ListaGrupos(usuarioId));
	}

	@Override
	@PostMapping("/{grupoId}")
	public UsuarioDtoResponce adicionaGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		return conversor.converteUsuario(service.adicionarGrupos(usuarioId, grupoId));
	}

	@Override
	@DeleteMapping("/{grupoId}")
	public UsuarioDtoResponce demoverGrupo(@PathVariable Long usuarioId,@PathVariable Long grupoId) {
		return conversor.converteUsuario(service.removerGrupos(usuarioId, grupoId));
	}
}
