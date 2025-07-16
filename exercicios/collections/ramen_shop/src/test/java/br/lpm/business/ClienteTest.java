package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.bebida.Refrigerante;
import br.lpm.core.PratoBase;
import br.lpm.prato.Boi;
import br.lpm.prato.Porco;
import br.lpm.prato.Tamanho;

public class ClienteTest {

    private Cliente cliente;
    private Pedido pedido;
    private PratoBase prato;

    @BeforeEach
    void setUp() {
        prato = new Porco("Porco", Tamanho.MEDIO, new Refrigerante());
        pedido = new Pedido(prato);
        cliente = new Cliente("Claudio", pedido);
    }

    @Test
    void testSetNomeValido() {
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome válido foi setado corretamente");

        cliente.setNome("Jorge");
        assertEquals("Jorge", cliente.getNome(), "Testando se o nome foi atualizado corretamente");
    }

    @Test
    void testSetNomeInvalido() {
        assertEquals("Claudio", cliente.getNome(), "Testando o nome inicial foi inicializado corretamente");

        cliente.setNome("");
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome não pode ser vazio");

        cliente.setNome(null);
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome não pode ser nulo");

        cliente.setNome(" ");
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome não pode ser apenas espaços em branco");

        cliente.setNome("Claudio192874");
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome não pode conter números");
        
        cliente.setNome("@#$%");
        assertEquals("Claudio", cliente.getNome(), "Testando se o nome não pode conter caracteres especiais");
    }

    @Test
    void testSetPedido() {
        assertEquals(pedido, cliente.getPedido(), "Testando se o pedido foi setado corretamente");
        
        Pedido novoPedido = new Pedido(new Boi("Boi", Tamanho.GRANDE, new Refrigerante()));
        cliente.setPedido(novoPedido);
        assertEquals(novoPedido, cliente.getPedido(), "Testando se o pedido foi atualizado corretamente");

        cliente.setPedido(null);
        assertEquals(novoPedido, cliente.getPedido(), "Testando se o pedido não pode ser nulo");
    }
}
