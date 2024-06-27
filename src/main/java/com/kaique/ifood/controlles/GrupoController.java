package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.GrupoOpenAPI;
import com.kaique.ifood.dto.conversor.GrupoDtoConversor;
import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.GrupoDtoResconse;
import com.kaique.ifood.services.GrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController implements  GrupoOpenAPI{

	@Autowired
	private GrupoService service;
	
	@Autowired
	private GrupoDtoConversor conversor;
	
	@Override
	@GetMapping
	public List<GrupoDtoResconse> listar() {
		return conversor.lista(service.lista());
	}
	
	@Override
	@GetMapping("/{id}")
	public GrupoDtoResconse buscaPorId(@PathVariable Long id) {
		return conversor.converteGrupo(service.buscaPorId(id));
	}
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public GrupoDtoResconse adiciona(@Valid @RequestBody GrupoDtoRequest dto) {
		return conversor.converteGrupo(service.adiciona(dto));
	}
	
	@Override
	@PutMapping("/{id}")
	public GrupoDtoResconse atualiza(@PathVariable Long id , @Valid @RequestBody GrupoDtoRequest dto ) {
		return conversor.converteGrupo(service.atualiza(dto, id));
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleta(@PathVariable Long id) {
		service.deleta(id);
	}
	
	@Override
	@DeleteMapping("/{grupoId}/permissoes/{permissaoId}")
	public GrupoDtoResconse removerPermissao(@PathVariable Long grupoId , @PathVariable Long permissaoId) {
		return conversor.converteGrupo(service.removerPermissao(grupoId, permissaoId));
	}
	
	@Override
	@PostMapping("/{grupoId}/permissoes/{permissaoId}")
	public GrupoDtoResconse adicionaPermissao(@PathVariable Long grupoId , @PathVariable Long permissaoId) {
		return conversor.converteGrupo(service.adicionaPermissao(grupoId, permissaoId));
	}
	
}
