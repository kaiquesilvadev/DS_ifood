package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.request.AtualizaSenhaDtoRequest;
import com.kaique.ifood.dto.request.AtualizaUsuarioDtoRequest;
import com.kaique.ifood.dto.request.UsuarioDtoRequest;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario", description = "Gerenciamento de Usuario")
public interface UsuarioOpenAPI {
	
    @Operation(summary = "Lista os registros")
	public List<UsuarioDtoResponce> list() ;

	@Operation(summary = "Busca por ID")
	@ApiResponses({
            @ApiResponse(responseCode = "404", description = "Não encontrado", 
	                    content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public UsuarioDtoResponce buscaPorId(Long id) ;
	
	@Operation(summary = "Cadastra um novo registro")
    @ApiResponses({
        	@ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
        	@ApiResponse(responseCode = "404", description = "Não encontrado", 
        				content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public UsuarioDtoResponce adiciona(UsuarioDtoRequest dtoRequest) ;
	
	@Operation(summary = "Atualiza um registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
	                    content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public UsuarioDtoResponce atualizaUsuario(  AtualizaUsuarioDtoRequest dtoRequest, Long id) ;
	
	@Operation(summary = "Atualiza senha de um registro por ID")
	@ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
	                    content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public void atualizaSenha(AtualizaSenhaDtoRequest dtoRequest,Long id) ;
	
	@Operation(summary = "Exclui um registro por ID")
	@ApiResponses({
	        @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Não encontrado com o id", 
	                         content = @Content(schema = @Schema(implementation = ApiErro.class)))
	})
	public void deletar(Long id) ;
}
