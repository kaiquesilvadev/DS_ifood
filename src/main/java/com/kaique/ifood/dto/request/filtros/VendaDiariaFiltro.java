package com.kaique.ifood.dto.request.filtros;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFiltro {

	private Long restauranteId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate DataInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate dataFim;
}
