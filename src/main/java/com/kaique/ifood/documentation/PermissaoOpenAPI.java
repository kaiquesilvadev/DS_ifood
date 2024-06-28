package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.PermissaoDtoRequest;
import com.kaique.ifood.dto.responce.PermissaoDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permissoes", description = "Gerenciamento de Permissoes")
public interface PermissaoOpenAPI {
	
    @Operation(summary = "Lista os registros")
	public List<PermissaoDtoResponce> lista() ;

    @Operation(summary = "Busca por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
                        content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public PermissaoDtoResponce buscaPorId(Long id);

    @Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
    		@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
    		@ApiResponse(responseCode = "404", description = "Não encontrado", 
    					content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public PermissaoDtoResponce adiciona(PermissaoDtoRequest dtoRequest) ;
	
    @Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public PermissaoDtoResponce atualiza(PermissaoDtoRequest dtoRequest, Long id);
	
    @Operation(summary = "Exclui um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public void deleta(Long id) ;
	
}
