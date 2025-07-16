package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class StreamTest {
    private Stream stream;
    private Event saleEvent1, saleEvent2;
    private Event ticketEvent1;
    private StreamMonitor monitor;

    @BeforeEach
    void setUp() {
        stream = new Stream();
        monitor = new StreamMonitor();
        
        Sale sale1 = new Sale("Produto A", 100.0f, LocalDate.of(2026, 02, 01), "João");
        Sale sale2 = new Sale("Produto B", 200.0f, LocalDate.of(2026, 02, 01), "Maria");
        saleEvent1 = new Event(sale1);
        saleEvent2 = new Event(sale2);

        Ticket ticket1 = new Ticket("Show A", 150.0f, LocalDate.of(2026, 02, 01), "Teatro");
        ticketEvent1 = new Event(ticket1);
    }

    @Test
    void testInitialState() {
        assertEquals(0, stream.size(), "Stream deve iniciar vazio");
        assertTrue(stream.isEmpty(), "Stream deve iniciar vazio");
        assertNull(stream.getMonitor(), "Monitor deve iniciar como null");
    }

    @Test
    void testPublishAndGet() {
        stream.publish(saleEvent1);
        assertEquals(1, stream.size(), "Tamanho deve ser 1 após publicar");
        assertFalse(stream.isEmpty(), "Stream não deve estar vazio após publicar");
        assertEquals(saleEvent1, stream.get(), "Deve retornar o evento publicado");
        assertEquals(1, stream.size(), "Tamanho não deve mudar após get()");
    }

    @Test
    void testMultiplePublish() {
        stream.publish(saleEvent1);
        stream.publish(saleEvent2);
        stream.publish(ticketEvent1);
        
        assertEquals(3, stream.size(), "Tamanho deve ser 3 após publicar 3 eventos");
        assertEquals(saleEvent1, stream.get(), "Primeiro evento deve ser mantido");
    }

    @Test
    void testStreamCapacity() {
        for (int i = 0; i < Stream.MAX_EVENTS; i++) {
            stream.publish(saleEvent1);
        }
        assertEquals(Stream.MAX_EVENTS, stream.size(), "Stream deve estar cheio");
        
        assertThrows(IllegalStateException.class, 
            () -> stream.publish(saleEvent2),
            "Deve lançar exceção ao tentar publicar em stream cheio"
        );
    }

    @Test
    void testConsume() {
        stream.publish(saleEvent1);
        stream.publish(saleEvent2);
        
        Event consumed = stream.consume();
        assertEquals(saleEvent1, consumed, "Deve consumir o primeiro evento");
        assertEquals(1, stream.size(), "Tamanho deve diminuir após consumir");
        assertEquals(saleEvent2, stream.get(), "Segundo evento deve ser o primeiro após consumir");
    }

    @Test
    void testConsumeEmpty() {
        assertTrue(stream.isEmpty(), "Stream deve iniciar vazio");
        assertThrows(IllegalStateException.class, 
            () -> stream.consume(),
            "Deve lançar exceção ao consumir de stream vazio"
        );
        assertThrows(IllegalStateException.class, 
            () -> stream.get(),
            "Deve lançar exceção ao get de stream vazio"
        );
    }

    @Test
    void testRemoveAll() {
        stream.publish(saleEvent1);
        stream.publish(saleEvent2);
        stream.publish(ticketEvent1);
        
        assertEquals(3, stream.size(), "Deve ter 3 eventos antes de remover");
        stream.removeAll();
        assertEquals(0, stream.size(), "Deve estar vazio após removeAll");
        assertTrue(stream.isEmpty(), "Deve estar vazio após removeAll");
    }

    @Test
    void testMonitoring() {
        stream.setMonitor(monitor);
        assertNotNull(stream.getMonitor(), "Monitor deve estar definido após setMonitor");
        
        stream.publish(saleEvent1);
        stream.publish(ticketEvent1);
        
        assertEquals(2, monitor.getEventCount(), "Monitor deve registrar 2 eventos");
        
        stream.removeAll();
        assertEquals(2, monitor.getEventCount(), "RemoveAll não deve afetar o monitor");
    }

    @Test
    void testNullMonitor() {
        stream.setMonitor(null);
        assertNull(stream.getMonitor(), "Monitor deve permanecer null");
        
        stream.publish(saleEvent1);
        assertEquals(1, stream.size(), "Stream deve funcionar sem monitor");
    }
}