package com.kaique.ifood.controlles;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.documentation.RestauranteOpenAPI;
import com.kaique.ifood.dto.conversor.RestauranteDtoConversor;
import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
import com.kaique.ifood.dto.responce.RestauranteDtoResponce;
import com.kaique.ifood.dto.responce.RestauranteResumoDtoResponce;
import com.kaique.ifood.entities.Restaurante;
import com.kaique.ifood.services.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/restaurantes")
public class RestauranteController implements RestauranteOpenAPI{

	@Autowired
	private RestauranteService service;

	@Autowired
	private RestauranteDtoConversor conversor;

	@Override
	@GetMapping
	public Page<RestauranteResumoDtoResponce> listar(Pageable pageable) {
		Page<Restaurante> pageRestaurante = service.listar(pageable);
	  	List<RestauranteResumoDtoResponce> restaurante = conversor.listaDtoResumo(pageRestaurante.getContent());
	  	return new PageImpl<>(restaurante, pageable , pageRestaurante.getTotalElements());
	}

	@Override
	@GetMapping("/{id}")
	public RestauranteDtoResponce buscaPorId(@PathVariable Long id) {
		return  conversor.converteEntity(service.buscaPorId(id));
	}

	@Override
	@GetMapping("/filtroTaxa/por-taxa-frete")
	public List<RestauranteDtoResponce> filtraPorTaxas(BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
		return conversor.listaDtoEmtity(service.filtraPorTaxas(taxaInicial, taxaFinal));
	}

	/*
	 * @GetMapping("/filtra/nome-e-id") public ResponseEntity<List<Restaurante>>
	 * buscaPorNomeEIdDeCozinha(String nome, BigDecimal id) { return
	 * ResponseEntity.ok().body(service.buscaPorNomeEIdDeCozinha(nome, id)); }
	 */

	@Override
	@GetMapping("/filtra/por-nome-e-frete")
	public List<RestauranteDtoResponce> buscaRTTPorNomeFrete(String nome, @RequestParam BigDecimal taxaFreteInicia,
			BigDecimal taxaFreteFinal) {
		return conversor.listaDtoEmtity(service.buscaRTTPorNomeFrete(nome, taxaFreteInicia, taxaFreteFinal));
	}

	@Override
	@GetMapping("/filtra/com-frete-gratis")
	public List<RestauranteDtoResponce> restaurantesComFreteGratis(String nome) {
		return conversor.listaDtoEmtity(service.restaurantesComFreteGratis(nome));
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public RestauranteDtoResponce adiciona(@Valid @RequestBody RestaurantesDtoRequest restauranteDto) {
		return conversor.converteEntity(service.adiciona(restauranteDto));
	}

	@Override
	@PutMapping("/{restauranteId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public RestauranteDtoResponce atualiza(@PathVariable Long restauranteId,
			@Valid @RequestBody RestaurantesDtoRequest restaurante) {
		return conversor.converteEntity(service.atualiza(restauranteId, restaurante));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/ativa")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void ativa(@PathVariable Long restauranteId) {
		service.ativa(restauranteId);

	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/desativa")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void desativa(@PathVariable Long restauranteId) {
		service.desativa(restauranteId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
