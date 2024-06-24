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

import com.kaique.ifood.documentation.FormaPagamentoOpenAPI;
import com.kaique.ifood.dto.conversor.FormaPagamentoDtoConversor;
import com.kaique.ifood.dto.request.FormaPagamentoDtoRequest;
import com.kaique.ifood.dto.responce.FormaPagamentoDtoResponce;
import com.kaique.ifood.services.FormaPagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController implements FormaPagamentoOpenAPI{

	@Autowired
	private FormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoDtoConversor conversor;

	@Override
	@GetMapping
	public List<FormaPagamentoDtoResponce> lista() {
		return conversor.listaDto(service.lista());
	}

	@Override
	@GetMapping("/{id}")
	public FormaPagamentoDtoResponce buscaPorId(@PathVariable Long id) {
		return conversor.ConversorEntity(service.buscaPorId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FormaPagamentoDtoResponce adiciona(@Valid @RequestBody FormaPagamentoDtoRequest formaPagamento) {
		return conversor.ConversorEntity(service.adiciona(formaPagamento));
	}

	@Override
	@PutMapping("/{id}")
	public FormaPagamentoDtoResponce atualiza(@Valid @RequestBody FormaPagamentoDtoRequest formaPagamento, @PathVariable Long id) {
		return conversor.ConversorEntity(service.atualiza(formaPagamento, id));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
