package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.CozinhaDtoRequest;
import com.kaique.ifood.dto.responce.CozinhaDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cozinhas", description = "Gerenciamento de cozinhas")
public interface CozinhaOpenAPI {

    @Operation(summary = "Lista os registros")
    List<CozinhaDtoResponce> listar();

    @Operation(summary = "Lista os registros por nome")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    List<CozinhaDtoResponce> buscarPorNome(String nome);

    @Operation(summary = "Busca por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    CozinhaDtoResponce buscaPorId(@Parameter(description = "ID", example = "1") Long id);

    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
        @ApiResponse(responseCode = "404", description = "Não encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    CozinhaDtoResponce adiciona(CozinhaDtoRequest dto);

    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    CozinhaDtoResponce atualiza(
            @Parameter(description = "ID", example = "1") Long id,
            @Parameter(description = "Representação de um registro atualizado") CozinhaDtoRequest dto
    );

    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void deletar(@Parameter(description = "ID", example = "1") Long id);
}
