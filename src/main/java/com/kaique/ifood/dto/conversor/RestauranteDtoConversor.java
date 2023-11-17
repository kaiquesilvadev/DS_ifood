package com.kaique.ifood.dto.conversor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
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
}
