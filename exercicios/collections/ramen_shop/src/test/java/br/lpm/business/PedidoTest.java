package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.bebida.Ocha;
import br.lpm.bebida.Refrigerante;
import br.lpm.core.PratoBase;
import br.lpm.prato.Boi;
import br.lpm.prato.Porco;
import br.lpm.prato.Tamanho;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoTest {
    private Pedido pedido;
    private PratoBase prato;

    @BeforeEach
    void setUp() {
        prato = new Boi("Ramen de Boi", Tamanho.PEQUENO, new Ocha());
        pedido = new Pedido(prato);
    }

    @Test
    void testSetPrato() {
        assertEquals(prato, pedido.getPrato(), "Testando se o prato foi iniciado corretamente");

        PratoBase novoPrato = new Porco("Ramen de Porco", Tamanho.MEDIO, new Refrigerante());
        pedido.setPrato(novoPrato);
        assertEquals(novoPrato, pedido.getPrato(), "Testando se o prato foi atualizado corretamente");

        pedido.setPrato(null);
        assertEquals(novoPrato, pedido.getPrato(), "Prato não deve ser alterado para nulo");
    }
}
