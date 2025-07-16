package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.bebida.Kocha;
import br.lpm.bebida.Ocha;
import br.lpm.core.Prato;
import br.lpm.prato.Boi;
import br.lpm.prato.Tamanho;
import br.lpm.prato.Vegano;

public class ListaDeEsperaTest {
    private ListaDeEspera listaDeEspera;
    private Pedido pedido1;
    private Pedido pedido2;

    private Prato prato1;
    private Prato prato2;

    @BeforeEach
    void setUp(){
        listaDeEspera = ListaDeEspera.getInstance();
        prato1 = new Boi("Ramen de Boi", Tamanho.PEQUENO, new Ocha());
        prato2 = new Vegano("Ramen Vegano", Tamanho.GRANDE, new Kocha());

        pedido1 = new Pedido(prato1);
        pedido2 = new Pedido(prato2);
    }

    @Test
    void testAddPedido() {
        assertEquals(0, listaDeEspera.getPedidos().size(), "Lista de espera deve estar vazia antes de adicionar pedidos");

        listaDeEspera.addPedido(pedido1);
        assertEquals(1, listaDeEspera.getPedidos().size(), "Lista de espera deve conter 1 pedido após adicionar o primeiro");
        assertEquals(pedido1, listaDeEspera.getPedidos().get(0), "O primeiro pedido adicionado deve ser o pedido1");

        listaDeEspera.addPedido(pedido2);
        assertEquals(2, listaDeEspera.getPedidos().size(), "Lista de espera deve conter 2 pedidos após adicionar o segundo");

        listaDeEspera.addPedido(null);
        assertEquals(2, listaDeEspera.getPedidos().size(), "Lista de espera não deve mudar ao tentar adicionar um pedido nulo");
    }

    @Test
    void testRemovePedido() {
        listaDeEspera.addPedido(pedido1);
        listaDeEspera.addPedido(pedido2);


        assertEquals(2, listaDeEspera.getPedidos().size(), "Lista de espera deve conter 2 pedidos");

        listaDeEspera.removePedido(pedido1);
        assertEquals(1, listaDeEspera.getPedidos().size(), "Lista de espera deve conter 1 pedidos após remover o pedido1");
        assertEquals(pedido2, listaDeEspera.getPedidos().get(0), "O segundo pedido deve ser o primeiro pedido na lista após a remoção do pedido1");

        listaDeEspera.removePedido(pedido1);
        assertEquals(1, listaDeEspera.getPedidos().size(), "Não deve ser possível remover um pedido que não está na lista de espera");

        listaDeEspera.removePedido(null);
        assertEquals(1, listaDeEspera.getPedidos().size(), "Lista de espera não deve mudar ao tentar remover um pedido nulo");

        listaDeEspera.removePedido(pedido2);
        assertEquals(0, listaDeEspera.getPedidos().size(), "Lista de espera deve estar vazia após remover todos os pedidos");

    }
}
