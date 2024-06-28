package com.kaique.ifood.documentation;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageGenericoOperAPI<T> {

    @Schema(description = "Conteúdo da página", example = "lista de objetos")
    private List<T> content;

    @Schema(description = "Quantidade de registros por página", example = "10")
    private Long size;

    @Schema(description = "Total de registros", example = "50")
    private Long totalElements;

    @Schema(description = "Total de páginas", example = "5")
    private Long totalPages;

    @Schema(description = "Número da página (Começa em 0)", example = "0")
    private Long number;
}