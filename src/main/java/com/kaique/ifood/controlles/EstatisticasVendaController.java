package com.kaique.ifood.controlles;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.request.filtros.VendaDiariaFiltro;
import com.kaique.ifood.dto.responce.VendaDiaria;
import com.kaique.ifood.services.VendaConsultasService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasVendaController {

	@Autowired
	private VendaConsultasService vendaConsultasService;

	// Não precisa marcar com @PathVariable, o spring entende quando é passado um
	// parâmetro
	@GetMapping(value = "/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro vendaDiariaFiltro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
		return vendaConsultasService.consultarVendasDiarias(vendaDiariaFiltro, timeOffSet);
	}
}