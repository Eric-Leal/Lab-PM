package br.lpm.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.acrescimo.Tofu;
import br.lpm.prato.Tamanho;
import br.lpm.prato.Vegano;

public class AcrescimoTest {

    private Acrescimo acrescimo;
    private Prato prato;

    @BeforeEach
    void setUp() {
        prato = new Vegano("Ramen", Tamanho.MEDIO, null);
        acrescimo = new Tofu(prato);
    }

    @Test
    void testSetPrato() {
        assertEquals(prato, acrescimo.getPrato(), "Testando se o prato foi inicializado corretamente");
        
        acrescimo.setPrato(prato);
        assertEquals(prato, acrescimo.getPrato(), "Testando se o prato foi atualizado corretamente");

        acrescimo.setPrato(null);
        assertEquals(prato, acrescimo.getPrato(), "Prato não deve ser alterado para nulo");
    }
}
