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

import com.kaique.ifood.documentation.PermissaoOpenAPI;
import com.kaique.ifood.dto.conversor.PermissaodtoConversor;
import com.kaique.ifood.dto.request.PermissaoDtoRequest;
import com.kaique.ifood.dto.responce.PermissaoDtoResponce;
import com.kaique.ifood.services.PermissaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/permissoes")
public class PermissaoController implements PermissaoOpenAPI{

	@Autowired
	private PermissaoService service;
	
	@Autowired
	private PermissaodtoConversor conversor;

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping
	public List<PermissaoDtoResponce> lista() {
		return conversor.listaDto(service.lista());
	}

	@Override
	@GetMapping("/{id}")
	public PermissaoDtoResponce buscaPorId(@PathVariable Long id) {
		return conversor.converteEntity(service.buscaPorId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping
	public PermissaoDtoResponce adiciona(@Valid @RequestBody PermissaoDtoRequest dtoRequest) {
		return conversor.converteEntity(service.adiciona(dtoRequest));
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public PermissaoDtoResponce atualiza(@Valid @RequestBody PermissaoDtoRequest dtoRequest, @PathVariable Long id) {
		return conversor.converteEntity(service.atualiza(dtoRequest, id));
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleta(@PathVariable Long id) {
		service.deleta(id);
	}
}
