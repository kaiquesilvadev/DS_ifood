package com.kaique.ifood.documentation;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kaique.ifood.dto.request.PedidoDtoRequest;
import com.kaique.ifood.dto.responce.PedidoDtoResponce;
import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public interface PedidoOpenAPI {

    @Operation(summary = "Lista os registros resumidos")
    Page<PedidoResumoDtoResponce> lista(Pageable pageable);

    @Operation(summary = "Busca um registro por código")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    PedidoDtoResponce buscaPorCodigo(
            @Parameter(description = "Código do pedido", example = "8e6588c2-b393-4e04-ba84-0d8a576977ae") String codigo
    );
    
    @Operation(summary = "cria um pedido")
    @ApiResponses({
        	@ApiResponse(responseCode = "400", description = "Parâmetro passado inválido" ,
        			   content = @Content(schema = @Schema(implementation = ApiErro.class))),
        	@ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            			
    })
    public PedidoDtoResponce criarPedido(
    		 @Parameter(description = "Representação de um novo pedido") PedidoDtoRequest dtoRequest);
    
    @Operation(summary = "Confirma um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o código", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void confirmaPedido(
            @Parameter(description = "Código do pedido", example = "8e6588c2-b393-4e04-ba84-0d8a576977ae") String codigo
    );

    @Operation(summary = "Confirma entrega")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o código", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void pedidoEntregue(
            @Parameter(description = "Código do pedido", example = "8e6588c2-b393-4e04-ba84-0d8a576977ae") String codigo
    );
    
    @Operation(summary = "Cancelar pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o código", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
    void pedidoCancelado(
            @Parameter(description = "Código do pedido", example = "8e6588c2-b393-4e04-ba84-0d8a576977ae") String codigo
    );
}