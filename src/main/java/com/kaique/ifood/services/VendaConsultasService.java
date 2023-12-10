package com.kaique.ifood.services;

import java.util.List;

import com.kaique.ifood.dto.request.filtros.VendaDiariaFiltro;
import com.kaique.ifood.dto.responce.VendaDiaria;

/*
As implementações são feitas no VendaConsultaServiceImpl
*/
public interface VendaConsultasService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro vendaDiariaFiltro, String timeOffSet);

}