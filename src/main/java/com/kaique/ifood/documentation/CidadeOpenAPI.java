package com.kaique.ifood.documentation;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kaique.ifood.dto.request.CidadeDtoRequest;
import com.kaique.ifood.dto.responce.CidadeResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface CidadeOpenAPI {

    @Operation(summary = "Lista os registros")
    List<CidadeResponce> listar();

    @Operation(summary = "Busca por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "400", description = "Parâmetro passado inválido", content = @Content(schema = @Schema(implementation = ApiErro.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    CidadeResponce buscarPorId(@Parameter(description = "ID da cidade", example = "1") @PathVariable Long id);

    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado com sucesso")
    })
    CidadeResponce adiciona(@Parameter(description = "Representação de uma nova cidade") @RequestBody CidadeDtoRequest dto);

    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Não encontrado com o id", content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    CidadeResponce atualiza(
        @Parameter(description = "ID da cidade", example = "1") @PathVariable Long id,
        @Parameter(description = "Representação de um registro atualizado") @RequestBody CidadeDtoRequest dto
    );

    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Não encontrado com o id", content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void deletar(@Parameter(description = "ID da cidade", example = "1") @PathVariable Long id);
}
