package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.EstadoDtoRequest;
import com.kaique.ifood.dto.responce.EstadoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estado", description = "Gerenciamento de Estado")
public interface EstadoOpenAPI {

    @Operation(summary = "Lista os registros")
	public List<EstadoDtoResponce> listar() ;
    
    @Operation(summary = "Busca por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public EstadoDtoResponce buscaPorId(Long id) ;
    
    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
        	@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
        	@ApiResponse(responseCode = "404", description = "Não encontrado", 
                     	content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public EstadoDtoResponce adiciona(EstadoDtoRequest estado) ;
    
    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public EstadoDtoResponce atualiza(Long estadiId,  EstadoDtoRequest estado) ;
    
    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void deletar(Long id) ;
}
