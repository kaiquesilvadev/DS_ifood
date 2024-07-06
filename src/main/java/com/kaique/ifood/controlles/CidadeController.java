package com.kaique.ifood.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.CidadeOpenAPI;
import com.kaique.ifood.dto.conversor.CidadeDtoConversor;
import com.kaique.ifood.dto.request.CidadeDtoRequest;
import com.kaique.ifood.dto.responce.CidadeResponce;
import com.kaique.ifood.services.CidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cidades", description = "Gerencia as cidades")
@RestController
@RequestMapping("v1/cidades")
public class CidadeController implements CidadeOpenAPI{

	@Autowired
	private CidadeService service;
	
	@Autowired
	private CidadeDtoConversor conversor;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<CidadeResponce> listar() {
		return conversor.ConverteListEntity(service.listar()) ;
	}

	@Override
	@GetMapping("/{id}")
	public CidadeResponce buscarPorId(@PathVariable Long id) {
		return conversor.converteEntity(service.buscaPorId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeResponce adiciona(@Valid @RequestBody CidadeDtoRequest cidade) {
		return conversor.converteEntity(service.adiciona(cidade));
	}

	@Override
	@PutMapping("/{cidadeId}")
	public CidadeResponce atualiza(@PathVariable Long cidadeId, @Valid @RequestBody CidadeDtoRequest cidade) {
		return conversor.converteEntity(service.atualiza(cidadeId, cidade));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
