package br.lpm.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoletoTest {
    
    private Boleto boleto;
    private LocalDate vencimento;
    private LocalDate pagamento ;

    @BeforeEach
    void setUp() {
        vencimento = LocalDate.now().plusDays(5); 
        pagamento = LocalDate.now();
        boleto = new Boleto(100, vencimento, pagamento);    
    }

    @Test
    void testSetPagamento() {

    }

    @Test
    void testSetValor() {

    }

    @Test
    void testSetVencimentoValido() {
        assertEquals(LocalDate.now().plusDays(5), boleto.getVencimento(), "O vencimento do boleto deve ser igual ao valor definido");
    }
}
