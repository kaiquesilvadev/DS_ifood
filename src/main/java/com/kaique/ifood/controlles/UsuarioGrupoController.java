package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.GrupoDtoConversor;
import com.kaique.ifood.dto.conversor.UsuarioDtoConversor;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.services.UsuarioGrupoService;

@RestController
@RequestMapping("/usuario/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private UsuarioGrupoService service;

	@Autowired
	private UsuarioDtoConversor conversor;
	
	@Autowired
	private GrupoDtoConversor conversorGrupo;
	
	@GetMapping
	public List<GrupoDtoRequest> ListaGrupo(@PathVariable Long usuarioId) {
		return conversorGrupo.listaDto(service.ListaGrupos(usuarioId));
	}

	@PostMapping("/{grupoId}")
	public UsuarioDtoResponce adicionaGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		return conversor.converteUsuario(service.adicionarGrupos(usuarioId, grupoId));
	}

	@DeleteMapping("/{grupoId}")
	public UsuarioDtoResponce demoverGrupo(@PathVariable Long usuarioId,@PathVariable Long grupoId) {
		return conversor.converteUsuario(service.removerGrupos(usuarioId, grupoId));
	}
}
