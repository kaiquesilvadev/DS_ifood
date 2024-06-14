package com.kaique.ifood.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
import com.kaique.ifood.dto.responce.RestauranteDtoResponce;
import com.kaique.ifood.dto.responce.RestauranteResumoDtoResponce;
import com.kaique.ifood.entities.Cozinha;
import com.kaique.ifood.entities.Restaurante;

/*
 * Classe que converte Entidades para DTO
 */

@Component
public class RestauranteDtoConversor {

	private ModelMapper modelMapper = new ModelMapper();

	/*
	 * Recebe um DTO e instancia uma entidade à partir desse DTO.
	 */
	public Restaurante converteParaRestaurante(RestaurantesDtoRequest dto) {
		return modelMapper.map(dto, Restaurante.class);
	}

	public RestauranteResumoDtoResponce converteResumo(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResumoDtoResponce.class);
	}
	
	public RestauranteDtoResponce converteEntity(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteDtoResponce.class);
	}

	/*
	 * Copia as propriadades do DTO para a Entidade, é mais indicado do que o
	 * BeanUtils.copyProperties porque no caso não precisamos ficar passando mais as
	 * propriedades que precisam ser ignoradas para não retornarem como null, o
	 * ModelMapper já tem a inteligência para validar isso.
	 */
	public void copiaPropiedades(RestaurantesDtoRequest dtoRequest, Restaurante restaurante) {
		// instancia uma nova cozinha para evitar um erro do jpa
		// Resolved [org.springframework.orm.jpa.JpaSystemException: identifier of an
		// instance of com.kaique.ifood.entities.Cozinha
		restaurante.setCozinha(new Cozinha());

		modelMapper.map(dtoRequest, restaurante);
	}

	public List<RestauranteResumoDtoResponce> listaDtoResumo(List<Restaurante> lista) {
		return lista.stream().map(restaunte -> converteResumo(restaunte)).collect(Collectors.toList());
	}
	
	public List<RestauranteDtoResponce> listaDtoEmtity(List<Restaurante> lista) {
		return lista.stream().map(restaunte -> converteEntity(restaunte)).collect(Collectors.toList());
	}
}
