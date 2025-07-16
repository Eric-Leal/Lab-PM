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

public class BalancoTest {
    private Balanco balanco;
    private Pedido pedido1;
    private Prato prato;
    private Pedido pedido2;
    private Prato prato2;

    @BeforeEach
    void setUp() {
        balanco = Balanco.getInstance();
        balanco.getPedidos().clear();
        prato = new Vegano("Ramen", Tamanho.MEDIO, new Ocha());
        prato2 = new Boi("Ramen de Boi", Tamanho.GRANDE, new Kocha());
        pedido1 = new Pedido(prato);
        pedido2 = new Pedido(prato2);
    }

    @Test
    void testAddPedido() {
        balanco.addPedido(pedido1);
        assertEquals(1, balanco.getQuantidadePedidos(), "Deve haver 1 pedido no Balanço após adicionar um pedido.");

        balanco.addPedido(pedido2);
        assertEquals(2, balanco.getQuantidadePedidos(), "Deve haver 2 pedidos no Balanço após adicionar outro pedido.");

        balanco.addPedido(null);
        assertEquals(2, balanco.getQuantidadePedidos(), "Não deve haver pedidos adicionais após tentar adicionar um pedido nulo.");
    }

    @Test
    void testExibir() {
        double receitaEsperada = 0.0;
        assertEquals(receitaEsperada, balanco.getReceitaTotal(), "A receita total inicial deve ser 0");

        balanco.addPedido(pedido1);
        receitaEsperada = pedido1.getPrato().getPreco();
        assertEquals(receitaEsperada, balanco.getReceitaTotal(), "A receita total deve ser igual ao preço do prato do pedido adicionado.");

        balanco.addPedido(pedido2);
        receitaEsperada += pedido2.getPrato().getPreco();
        assertEquals(receitaEsperada, balanco.getReceitaTotal(), "Testando a receita atualizada com o novo pedido.");


        balanco.addPedido(null);
        assertEquals(receitaEsperada, balanco.getReceitaTotal(), "A receita total não deve mudar quando adicionar pedido nulo.");

        balanco.removePedido(pedido1);
        receitaEsperada -= pedido1.getPrato().getPreco();
        assertEquals(receitaEsperada, balanco.getReceitaTotal(), "A receita total deve ser atualizada corretamente após remover um pedido.");
    }

    @Test
    void testCalcTicketMedio() {
        double ticketMedioEsperado = 0.0;
        assertEquals(ticketMedioEsperado, balanco.getTicketMedio(), "O ticket médio deve ser 0 com nenhum pedido adicionado.");

        balanco.addPedido(pedido1);
        ticketMedioEsperado = pedido1.getPrato().getPreco();
        assertEquals(ticketMedioEsperado, balanco.getTicketMedio(), "Testando media com 1 pedido, deve retornar o preço do prato do pedido.");


        balanco.addPedido(pedido2);
        ticketMedioEsperado = (pedido1.getPrato().getPreco() + pedido2.getPrato().getPreco()) / 2;
        assertEquals(ticketMedioEsperado, balanco.getTicketMedio(), "Testando media com dois pedidos.");

        balanco.addPedido(null);
        assertEquals(ticketMedioEsperado, balanco.getTicketMedio(), "O Ticket Medio não deve alterar com valor nulo.");

        balanco.removePedido(pedido1);
        ticketMedioEsperado = pedido2.getPrato().getPreco();
        assertEquals(ticketMedioEsperado, balanco.getTicketMedio(), "Após remover o pedido1, o ticket médio deve ser igual ao preço do prato do pedido2.");

        balanco.removePedido(pedido2);
        assertEquals(0.0, balanco.getTicketMedio(), "O ticket médio deve ser 0 após remover todos os pedidos.");
    }

    @Test
    void testRemovePedido() {
        balanco.addPedido(pedido1);
        assertEquals(1, balanco.getQuantidadePedidos(), "Deve haver 1 pedido no Balanco após adicionar um pedido.");
        
        balanco.removePedido(null);
        assertEquals(1, balanco.getQuantidadePedidos(), "Não deve haver alteração na quantidade de pedidos ao tentar remover um pedido nulo.");

        balanco.removePedido(pedido1);
        assertEquals(0, balanco.getQuantidadePedidos(), "Deve haver 0 pedidos no Balanco após remover o pedido adicionado.");

    }
}
