package com.kaique.ifood.documentation;

import com.kaique.ifood.dto.responce.PedidoResumoDtoResponce;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PagePedidosOpenAPI", description = "Página de pedidos resumidos")
public class PagePedidosOpenAPI extends PageGenericoOperAPI<PedidoResumoDtoResponce> {
}
