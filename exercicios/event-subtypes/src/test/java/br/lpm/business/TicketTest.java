package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    private Ticket ticket;
    private LocalDate data;
    private String endereco;
    private String evento;
    private float valor;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
    }

    @Test
    void testSetValorValido() {
        valor = 2.0F;
        ticket.setValue(valor);
        assertEquals(valor, ticket.getValue(), "Testando valor acima de 0.");
    }

    @Test
    void testSetValorInvalido() {
        valor = 0.0F;
        assertThrows(IllegalArgumentException.class, () -> ticket.setValue(valor), 
            "Testando valor igual a 0.");
        valor = -2.0F;
        assertThrows(IllegalArgumentException.class, () -> ticket.setValue(valor), 
            "Testando valor menor que 0.");
    }    

    @Test
    void testSetDataValida() {
        data = LocalDate.now().plusDays(1);
        ticket.setDate(data);
        assertEquals(data, ticket.getDate(), "Testando data futura.");
    }
    
    @Test
    void testSetDataInvalida() {
        data = LocalDate.of(2023, 3, 1);
        assertThrows(IllegalArgumentException.class, () -> ticket.setDate(data), 
            "Testando data passada.");
        data = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> ticket.setDate(data), 
            "Testando data atual.");
    }

    @Test
    void testSetEnderecoValido() {
        endereco = "Arena";
        ticket.setAddress(endereco);
        assertEquals(endereco, ticket.getAddress(), 
            "Testando endereço válido com espaço.");

        endereco = "Arena, 123";
        ticket.setAddress(endereco);
        assertEquals(endereco, ticket.getAddress(), 
            "Testando endereço válido com números.");
    }

    @Test
    void testSetEnderecoInvalido() {
        endereco = "";
        assertThrows(IllegalArgumentException.class, () -> ticket.setAddress(endereco), 
            "Testando endereço vazio.");
        endereco = "    ";
        assertThrows(IllegalArgumentException.class, () -> ticket.setAddress(endereco), 
            "Testando endereço apenas com espaços.");
        endereco = null;
        assertThrows(IllegalArgumentException.class, () -> ticket.setAddress(endereco), 
            "Testando endereço nulo.");
    }

    @Test
    void testSetEventoValido() {
        evento = "Show";
        ticket.setEvent(evento);
        assertEquals(evento, ticket.getEvent(), 
            "Testando nome de evento válido com espaços.");
    }
    
    @Test
    void testSetEventoInvalido() {
        evento = "Show 123";
        assertThrows(IllegalArgumentException.class, () -> ticket.setEvent(evento), 
            "Testando evento com números.");
        evento = null;
        assertThrows(IllegalArgumentException.class, () -> ticket.setEvent(evento), 
            "Testando evento nulo.");
        evento = "";
        assertThrows(IllegalArgumentException.class, () -> ticket.setEvent(evento), 
            "Testando evento vazio.");
        evento = "  ";
        assertThrows(IllegalArgumentException.class, () -> ticket.setEvent(evento), 
            "Testando evento apenas com espaços.");
    }

}