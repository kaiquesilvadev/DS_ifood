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

import com.kaique.ifood.dto.request.FormaPagamentoDtoRequest;
import com.kaique.ifood.entities.FormaPagamento;
import com.kaique.ifood.services.FormaPagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoService service;

	@GetMapping
	public List<FormaPagamento> lista() {
		return service.lista();
	}

	@GetMapping("/{id}")
	public FormaPagamento buscaPorId(@PathVariable Long id) {
		return service.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FormaPagamento adiciona(@Valid @RequestBody FormaPagamentoDtoRequest formaPagamento) {
		return service.adiciona(formaPagamento);
	}

	@PutMapping("/{id}")
	public FormaPagamento atualiza(@Valid @RequestBody FormaPagamentoDtoRequest formaPagamento, @PathVariable Long id) {
		return service.atualiza(formaPagamento, id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
