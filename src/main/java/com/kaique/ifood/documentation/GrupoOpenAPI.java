package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.GrupoDtoRequest;
import com.kaique.ifood.dto.responce.GrupoDtoResconse;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Grupos", description = "Gerenciamento de grupos")
public interface GrupoOpenAPI {

    @Operation(summary = "Lista os grupos")
    List<GrupoDtoResconse> listar();

    @Operation(summary = "Busca um grupo por id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Parâmetro passado inválido", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    GrupoDtoResconse buscaPorId(
            @Parameter(description = "ID de um grupo", example = "1") Long id
    );

    @Operation(summary = "Cadastra um novo grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso")
    })
    GrupoDtoResconse adiciona(
            @Parameter(description = "Representação de um Grupo") GrupoDtoRequest dto
    );

    @Operation(summary = "Atualiza um grupo por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    GrupoDtoResconse atualiza(
            @Parameter(description = "ID", example = "1") Long id, 
            @Parameter(description = "Representação de um Grupo atualizado") GrupoDtoRequest dto
    );

    @Operation(summary = "Exclui um grupo por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void deleta(
            @Parameter(description = "ID", example = "1") Long id
    );
    
    @Operation(summary = "remove grupo por id de grupo e permissão")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    public GrupoDtoResconse removerPermissao( 
    		@Parameter(description = "ID de grupo", example = "2") Long grupoId ,
    		@Parameter(description = "ID de permissão", example = "1") Long permissaoId
    );
    
    @Operation(summary = "adiciona permissão em grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public GrupoDtoResconse adicionaPermissao(
			@Parameter(description = "ID de grupo", example = "2") Long grupoId ,
    		@Parameter(description = "ID de permissão", example = "1") Long permissaoId
	);
}
