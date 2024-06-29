package com.kaique.ifood.documentation;

import java.util.List;

import com.kaique.ifood.dto.responce.GrupoDtoResconse;
import com.kaique.ifood.dto.responce.UsuarioDtoResponce;
import com.kaique.ifood.exceptionHandler.ApiErro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UsuarioGrupo", description = "Gerenciamento de grupo de usuario")
public interface UsuarioGrupoOpenAPI {

    @Operation(summary = "Lista os registros")
	public List<GrupoDtoResconse> ListaGrupo(Long usuarioId) ;
    
    @Operation(summary = "adiciona um novo grupo em usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado com sucesso") ,
        @ApiResponse(responseCode = "404", description = "Não encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public UsuarioDtoResponce adicionaGrupo(Long usuarioId, Long grupoId) ;
    
    @Operation(summary = "adiciona um novo grupo em usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "apagado com sucesso") ,
        @ApiResponse(responseCode = "404", description = "Não encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiErro.class)))
    })
	public UsuarioDtoResponce demoverGrupo(Long usuarioId, Long grupoId) ;
}
