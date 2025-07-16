package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleTest {

    private Sale venda;
    private LocalDate data;
    private String responsavel;
    private String descricao;
    private float valor;

    @BeforeEach
    void setUp() {
        venda = new Sale();
    }

    @Test
    void testSetValorValido(){
        float valor = 2.0F;
        venda.setValue(valor);
        assertEquals(valor, venda.getValue(), "Testando valor acima de 0.");
    }

    @Test
    void testSetValorInvalido(){
        valor = 0.0F;
        assertThrows(IllegalArgumentException.class, () -> venda.setValue(valor), "Testando valor igual a 0.");
        valor = -2.0F;
        assertThrows(IllegalArgumentException.class, () -> venda.setValue(valor), "Testando valor menor que 0;");
    }    

    @Test
    void testSetDataValida() {
        data = LocalDate.of(2026, 3, 1);
        venda.setDate(data);
        assertEquals(data, venda.getDate(), "Testando data acima da atual.");
    }
    
    @Test
    void testSetDataInvalida() {
        data = LocalDate.of(2023, 3, 1);
        assertThrows(IllegalArgumentException.class, () -> venda.setDate(data), "Testando data abaixo da atual.");
        data = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> venda.setDate(data), "Testando data atual.");
    }

    @Test
    void testSetResponsavelValido() {
        String responsavel = "João Silva";
        venda.setOrigin(responsavel);
        assertEquals(responsavel, venda.getOrigin(), "Teste de nome com acento e espaço.");
        
    }

    @Test
    void testSetResponsavelInvalido() {
        responsavel = "João Silva2";
        assertThrows(IllegalArgumentException.class, () -> venda.setOrigin(responsavel), "Testando nome de responsavel com numero.");
        responsavel = "";
        assertThrows(IllegalArgumentException.class, () -> venda.setOrigin(responsavel), "Testando nome de responsavel vazio.");
        responsavel = "    ";
        assertThrows(IllegalArgumentException.class, () -> venda.setOrigin(responsavel), "Testando nome de responsavel vazio com espaço.");
        responsavel = null;
        assertThrows(IllegalArgumentException.class, () -> venda.setOrigin(responsavel), "Testando nome de responsavel nulo.");
    }

    
    @Test
    void testSetDescricaoValida () { 
        String descricao = "Produto";
        venda.setDescription(descricao);
        assertEquals(descricao, venda.getDescription());
    }
    
    @Test
    void testSetDescricaoInvalida () { 
        descricao = null;
        assertThrows(IllegalArgumentException.class, () -> venda.setDescription(descricao), "Testando nome de descrição nula.");
        descricao = "";
        assertThrows(IllegalArgumentException.class, () -> venda.setDescription(descricao),"Testando nome de descrição vazio.");
        descricao = "  ";
        assertThrows(IllegalArgumentException.class, () -> venda.setDescription(descricao),"Testando nome da descricao usando somente espaco.");
    }
}
