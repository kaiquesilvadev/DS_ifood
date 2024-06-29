package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.ProdutoDtoRequest;
import com.kaique.ifood.dto.responce.ProdutoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RestauranteProduto", description = "Gerenciamento de produto de um restaurante específico")
public interface RestauranteProdutoOpenAPI {
	
    @Operation(summary = "Lista os registros")
	public List<ProdutoDtoResponce> listaProdutos(Long restauranteId);
	
    @Operation(summary = "Busca por ID , produto em restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                        content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public ProdutoDtoResponce buscaIdDeProdutoEmRestaurante(Long restauranteId, Long produtoId) ;
	
    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
    		@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
    		@ApiResponse(responseCode = "404", description = "Não encontrado", 
    					content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public ProdutoDtoResponce adiciona(Long restauranteId, ProdutoDtoRequest dtoRequest) ;
	
    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public ProdutoDtoResponce atualizar(ProdutoDtoRequest dtoRequest , Long restauranteId, Long produtoId) ;
	
    @Operation(summary = "Ativa produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void ativaProduto(Long restauranteId, Long produtoId) ;
	
    @Operation(summary = "Desativa produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void desativaProduto(Long restauranteId, Long produtoId) ;
	
    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void deletaProduto(Long restauranteId, Long produtoId) ;
}

