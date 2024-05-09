package com.kaique.ifood.services.factory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.enuns.StatusPedido;

public class PedidoMockFactory {

	/*TODO ; trocar o nulo por um obj real depois  */
	
	 public static Pedido createMockPedido() {
	        Pedido pedido = new Pedido();
	        pedido.setId(1L);
	        pedido.setCodigo("ABC123");
	        pedido.setSubTotal(BigDecimal.valueOf(50.00));
	        pedido.setTaxaFrete(BigDecimal.valueOf(5.00));
	        pedido.setValorTotal(BigDecimal.valueOf(55.00));
	        pedido.setStatusPedido(StatusPedido.CRIADO);
	        pedido.setDataCriacao(OffsetDateTime.now());
	        pedido.setEnderecoEntrega(null);
	        pedido.setFormaPagamento(null);
	        pedido.setUsuarioCliente(null);
	        pedido.setRestaurante(null);
	        pedido.setItens(null);

	        return pedido;
	    }
}
