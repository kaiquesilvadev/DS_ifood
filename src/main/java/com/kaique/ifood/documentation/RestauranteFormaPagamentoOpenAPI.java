package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.responce.FormaPagamentoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RestauranteFormaPagamento", description = "Gerenciamento de forma de pagamento de restaurante específico")
public interface RestauranteFormaPagamentoOpenAPI {

    @Operation(summary = "Lista forma de pagamentos")
	public List<FormaPagamentoDtoResponce> listaFormaPagamento(Long restauranteId) ;


    @Operation(summary = "Exclui  forma de pagamento de restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void deletaFormaPagamentoDeRestaurante(Long restauranteId , Long formaPagamentoId) ;
	
    @Operation(summary = "adicionar forma de pagamento em restaurante")
    @ApiResponses({
    		@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
    		@ApiResponse(responseCode = "404", description = "Não encontrado", 
    					content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public List<FormaPagamentoDtoResponce> addFormaPagamentoEmRestaurante(Long restauranteId , Long formaPagamentoId) ;
}
