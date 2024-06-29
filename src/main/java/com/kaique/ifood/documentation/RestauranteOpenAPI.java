package com.kaique.ifood.documentation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
import com.kaique.ifood.dto.responce.RestauranteDtoResponce;
import com.kaique.ifood.dto.responce.RestauranteResumoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurante", description = "Gerenciamento de restaurante")
public interface RestauranteOpenAPI {
	
    @Operation(summary = "Lista os registros paginado")
	public Page<RestauranteResumoDtoResponce> listar(Pageable pageable);

    @Operation(summary = "Busca por ID")
    @ApiResponses({
    		@ApiResponse(responseCode = "200", description = "busca com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    public RestauranteDtoResponce buscaPorId( Long id) ;

    @Operation(summary = "Busca por taxa de frete")
    @ApiResponses({
    		@ApiResponse(responseCode = "200", description = "busca com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public List<RestauranteDtoResponce> filtraPorTaxas(BigDecimal taxaInicial,  BigDecimal taxaFinal);

    @Operation(summary = "Busca por nome e taxa de frete")
    @ApiResponses({
    		@ApiResponse(responseCode = "200", description = "busca com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public List<RestauranteDtoResponce> buscaRTTPorNomeFrete(String nome,  BigDecimal taxaFreteInicia, BigDecimal taxaFreteFinal) ;

    @Operation(summary = "Busca por frete gratis")
    @ApiResponses({
    		@ApiResponse(responseCode = "200", description = "busca com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public List<RestauranteDtoResponce> restaurantesComFreteGratis(String nome);

    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
        	@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
        	@ApiResponse(responseCode = "400", description = "Parametro passado inválido", 
                     content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public RestauranteDtoResponce adiciona(RestaurantesDtoRequest restauranteDto);

    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id",
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public RestauranteDtoResponce atualiza(Long restauranteId, RestaurantesDtoRequest restaurante);

    @Operation(summary = "Ativar um registro por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id",
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void ativa(Long restauranteId) ;

    @Operation(summary = "Desativa um registro por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id",
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void desativa(Long restauranteId) ;

	@Operation(summary = "Exclui um registro por ID")
	@ApiResponses({
	        @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
	                        content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public void deletar(Long id) ;
	
}
