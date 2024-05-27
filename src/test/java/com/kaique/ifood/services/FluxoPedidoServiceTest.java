package com.kaique.ifood.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.enuns.StatusPedido;
import com.kaique.ifood.exception.PedidoNaoEncontradoException;
import com.kaique.ifood.exception.ViolacaoStatusPedidoException;
import com.kaique.ifood.services.factory.PedidoMockFactory;


@ExtendWith(SpringExtension.class)
public class FluxoPedidoServiceTest {

    @InjectMocks
    private FluxoPedidoService fluxoPedidoService;

    @Mock
    private EmissaoPedidoServices emissaoPedidoServices;

    private Pedido pedidoExistente;
    private String codigoExistente;
    private String codigoInexistente;

    @BeforeEach
    void setup() throws Exception {
        codigoExistente = "123456";
        codigoInexistente = "654321";

        pedidoExistente = PedidoMockFactory.createMockPedido();
        Mockito.when(emissaoPedidoServices.buscaPorCodigo(codigoExistente)).thenReturn(pedidoExistente);
        Mockito.doThrow(PedidoNaoEncontradoException.class).when(emissaoPedidoServices).buscaPorCodigo(codigoInexistente);
    }

    @Test
    public void confirmadoDeveConfirmarPedidoQueTenhaCodigoExistente() {
        fluxoPedidoService.confirmado(codigoExistente);

        assertTrue(pedidoExistente.getStatusPedido() == StatusPedido.CONFIRMADO);
    }

    @Test
    public void confirmadoDeveRetornaPedidoNaoEncontradoExceptionQuandoCodigoInexistente() {
        assertThrows(PedidoNaoEncontradoException.class, () -> {
            fluxoPedidoService.confirmado(codigoInexistente);
        });
    }
    
    @Test
    public void entregueDeveLancarExceptionQuandoTentarAtualizarPedidoDeCriadoParaEntregue() {
        
        assertThrows(ViolacaoStatusPedidoException.class, () -> {
        	fluxoPedidoService.entregue(codigoExistente);
        });
    }
    
    @Test
    public void entregueDeveLancarExceptionQuandoTentarAtualizarPedidoDeCanceladoParaEntregue() {
        
    	  pedidoExistente = PedidoMockFactory.createMockPedido();
          pedidoExistente.stausCancelado();
          Mockito.when(emissaoPedidoServices.buscaPorCodigo(codigoExistente)).thenReturn(pedidoExistente);
    	
        assertThrows(ViolacaoStatusPedidoException.class, () -> {
        	fluxoPedidoService.entregue(codigoExistente);
        });
    }
    
    @Test
    public void entregueDeveMudarStatusParaEntregueQuandoPedidoEstiverConfirmado() {
    	
        pedidoExistente = PedidoMockFactory.createMockPedido();
        pedidoExistente.statusConfirmado();
        Mockito.when(emissaoPedidoServices.buscaPorCodigo(codigoExistente)).thenReturn(pedidoExistente);
        fluxoPedidoService.entregue(codigoExistente);

        assertTrue(pedidoExistente.getStatusPedido() == StatusPedido.ENTREGUE);
    }
}
