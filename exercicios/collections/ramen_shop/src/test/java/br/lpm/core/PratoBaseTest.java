package br.lpm.core;

import br.lpm.prato.Boi;
import br.lpm.prato.Tamanho;
import br.lpm.bebida.Refrigerante;
import br.lpm.bebida.Kocha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PratoBaseTest {

    private PratoBase prato;
    private Bebida bebida1;
    private Bebida bebida2;

    @BeforeEach
    void setUp() {
        bebida1 = new Refrigerante();
        bebida2 = new Kocha();

        prato = new Boi("Ramen de Boi", Tamanho.PEQUENO, null);
    }

    @Test
    void testGetPreco() {

        double precoEsperado1 = Tamanho.PEQUENO.getPreco() + 7.90; 
        assertEquals(precoEsperado1, prato.getPreco(), "Testando o preço do Ramen de Boi Pequeno sem Bebida");

        prato.setBebida(bebida1);
        double precoEsperado2 = Tamanho.PEQUENO.getPreco() + 7.90 + 5.90; 
        assertEquals(precoEsperado2, prato.getPreco(), "Testando o preço do Ramen de Boi Grande com Refrigerante");

        prato.setBebida(bebida2);
        double precoComBebida2 = Tamanho.PEQUENO.getPreco() + 7.90 + 0.0;
        assertEquals(precoComBebida2, prato.getPreco(), "Testando o preço do Ramen de Boi Pequeno com Kocha");

        double precoComTamanhoPequeno = Tamanho.PEQUENO.getPreco() + prato.getPrecoProteina();
        assertEquals(precoComTamanhoPequeno, prato.getPreco(),"Testando o preço do Ramen de Boi Pequeno");

        prato.setTamanho(Tamanho.MEDIO);
        double precoComTamanhoMedio = Tamanho.MEDIO.getPreco() + prato.getPrecoProteina();
        assertEquals(precoComTamanhoMedio, prato.getPreco(), "Testando o preço do Ramen de Boi Médio");

        prato.setTamanho(Tamanho.GRANDE);
        double precoComTamanhoGrande = Tamanho.GRANDE.getPreco() + prato.getPrecoProteina();
        assertEquals(precoComTamanhoGrande, prato.getPreco(), "Testando o preço do Ramen de Boi Grande");
    }

    @Test
    void testSetBebida(){
        assertEquals(null, prato.getBebida(), "Verificando se bebida começa como nula");

        prato.setBebida(bebida1);
        assertEquals(bebida1, prato.getBebida(), "Testando se bebida foi alterada corretamente");

        prato.setBebida(bebida2);
        assertEquals(bebida2, prato.getBebida(), "Testando se bebida foi alterada corretamente");
    }
    
    @Test
    void testSetDescricaoValido() {
        assertEquals("Ramen de Boi pequeno", prato.getDescricao(), "Verificando a descrição inicial do prato");

        prato.setDescricao("Ramen de Boi dois");
        assertEquals("Ramen de Boi dois pequeno", prato.getDescricao(), "Verificando se a descrição foi atualizada corretamente");
        
    }

    @Test
    void testSetDescricaoInvalido(){
        assertEquals("Ramen de Boi pequeno", prato.getDescricao(), "Verificando a descrição inicial do prato");

        prato.setDescricao(null);
        assertEquals("Ramen de Boi pequeno", prato.getDescricao(), "Descrição não deve ser alterada para nula");

        prato.setDescricao("  ");
        assertEquals("Ramen de Boi pequeno", prato.getDescricao(), "Descrição não deve ser alterada para espaço vazio");

        prato.setDescricao("");
        assertEquals("Ramen de Boi pequeno", prato.getDescricao(), "Descrição não deve ser alterada para vazia");
    }
}