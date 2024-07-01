package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.filtros.VendaDiariaFiltro;
import com.kaique.ifood.dto.responce.VendaDiaria;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "EstatisticasVenda", description = "Gerenciamento das vendas diaria")
public interface EstatisticasVendaOpenAPI {

	@Operation(summary = "lista de vendas diaria")
	    @ApiResponses({
	            @ApiResponse(responseCode = "400", description = "Parâmetro passado inválido", 
	                         content = @Content(schema = @Schema(implementation = ApiErro.class))),
	            @ApiResponse(responseCode = "404", description = "Não encontrado", 
	                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
	    })
	public List<VendaDiaria> consultarVendasDiarias(
			@Parameter(description = "Coloque uma data inicial e uma data final. Se não for passado, será considerado null e ele buscará todas." )VendaDiariaFiltro vendaDiariaFiltro,
			@Parameter(description = "Parâmetro não obrigatório: se não for passado um horário, por padrão ele busca todos.", example = "+01:00")String timeOffSet) ;
}
