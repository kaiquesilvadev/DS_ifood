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

import com.kaique.ifood.documentation.EstadoOpenAPI;
import com.kaique.ifood.dto.conversor.EstadoDtoConversor;
import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.dto.responce.EstadoDtoResponce;
import com.kaique.ifood.services.EstadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/estados")
public class EstadoController implements EstadoOpenAPI{

	@Autowired
	private EstadoService service;
	
	@Autowired
	private EstadoDtoConversor conversor;
	
	@Override
	@GetMapping
	public List<EstadoDtoResponce> listar() {
		return conversor.listaDto(service.listar());
	}

	@Override
	@GetMapping("/{id}")
	public EstadoDtoResponce buscaPorId(@PathVariable Long id) {
		return conversor.conversorEntety(service.buscaPorId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public EstadoDtoResponce adiciona(@Valid @RequestBody EstadoDtoRequest estado) {
		return conversor.conversorEntety(service.adiciona(estado));
	}

	@Override
	@PutMapping("/{estadiId}")
	public EstadoDtoResponce atualiza(@PathVariable Long estadiId,@Valid @RequestBody EstadoDtoRequest estado) {
		return conversor.conversorEntety(service.atualiza(estadiId, estado));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
