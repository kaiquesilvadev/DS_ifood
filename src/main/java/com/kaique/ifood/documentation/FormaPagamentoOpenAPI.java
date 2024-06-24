package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.FormaPagamentoDtoRequest;
import com.kaique.ifood.dto.responce.FormaPagamentoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FormasPagamento", description = "Gerenciamento das formas de pagamento")
public interface FormaPagamentoOpenAPI {

    @Operation(summary = "Lista os registros")
    List<FormaPagamentoDtoResponce> lista();

    @Operation(summary = "Busca por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Parâmetro passado inválido", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    FormaPagamentoDtoResponce buscaPorId(
            @Parameter(description = "ID", example = "1") Long id
    );

    @Operation(summary = "Cadastra um novo registro")
    @ApiResponse(responseCode = "201", description = "Criado com sucesso")
    FormaPagamentoDtoResponce adiciona(FormaPagamentoDtoRequest dto);

    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
            			content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    FormaPagamentoDtoResponce atualiza(
            @Parameter(description = "Representação de um registro atualizado") FormaPagamentoDtoRequest dto ,
            @Parameter(description = "ID", example = "1") Long id
    );

    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void deletar(@Parameter(description = "ID", example = "1") Long id);
}
