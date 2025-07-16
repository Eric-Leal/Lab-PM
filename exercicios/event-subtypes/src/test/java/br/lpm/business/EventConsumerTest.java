package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EventConsumerTest {
    private EventConsumer consumer;
    private Stream stream;
    private Event event;

    @BeforeEach
    void setUp() {
        stream = new Stream();
        consumer = new SaleConsumer(stream);
        
        Sale sale = new Sale("Produto A", 100.0f, LocalDate.of(2026,02,01), "João");
        event = new Event(sale);
    }

    @Test
    void testConsumeEvent() {
        stream.publish(event);
        Event consumedEvent = consumer.consumeEvent(stream);
        
        assertEquals(event, consumedEvent, 
            "O evento consumido deve ser igual ao evento publicado");
        assertEquals(1, consumer.getEventsConsumed(), 
            "Número de eventos consumidos deve ser 1 após consumir um evento");
    }

    @Test
    void testConsumeEventStreamVazio() {
        assertThrows(IllegalStateException.class, () -> consumer.consumeEvent(stream), 
            "Deve lançar exceção ao tentar consumir de um stream vazio");
    }

    @Test
    void testConsumeEventLimiteMaximo() {
        for (int i = 0; i < EventConsumer.MAX_EVENTS; i++) {
            stream.publish(event);
            consumer.consumeEvent(stream);
        }
        
        stream.publish(event);
        assertThrows(IllegalStateException.class, () -> consumer.consumeEvent(stream), 
            "Deve lançar exceção ao tentar consumir mais que o limite máximo de eventos");
    }
}