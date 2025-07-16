package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TicketConsumerTest {
    private TicketConsumer consumer;
    private Stream stream;
    private Event event1, event2, event3;

    @BeforeEach
    void setUp() {
        stream = new Stream();
        consumer = new TicketConsumer(stream);

        Ticket ticket1 = new Ticket("Show A", 150.0f, LocalDate.of(2026,02,01), "Teatro");
        Ticket ticket2 = new Ticket("Show B", 180.0f, LocalDate.of(2026,02,01), "Arena");
        Ticket ticket3 = new Ticket("Show A", 150.0f, LocalDate.of(2026,02,01), "Teatro");
        
        event1 = new Event(ticket1);
        event2 = new Event(ticket2);
        event3 = new Event(ticket3);
    }

    @Test
    void testAvgValue() {
        assertThrows(IllegalStateException.class, () -> consumer.avgValue(), 
            "Deve lançar exceção ao calcular média sem eventos");

        publishAndConsume(event1);
        assertEquals(150.0f, consumer.avgValue(), 
            "Média com um único evento de valor 150.0 deve ser 150.0");

        publishAndConsume(event2);
        assertEquals(165.0f, consumer.avgValue(), 
            "Média com eventos de valores 150.0 e 180.0 deve ser 165.0");
    }

    @Test
    void testMaxValue() {
        publishAndConsume(event1);
        assertEquals(150.0f, consumer.maxValue(), 
            "Valor máximo com um único evento de valor 150.0 deve ser 150.0");

        publishAndConsume(event2);
        assertEquals(180.0f, consumer.maxValue(), 
            "Valor máximo após adicionar evento de valor 180.0 deve ser 180.0");
    }

    @Test
    void testMinValue() {
        publishAndConsume(event1);
        assertEquals(150.0f, consumer.minValue(), 
            "Valor mínimo com um único evento de valor 150.0 deve ser 150.0");

        publishAndConsume(event2);
        assertEquals(150.0f, consumer.minValue(), 
            "Valor mínimo após adicionar evento de valor 180.0 deve permanecer 150.0");
    }

    @Test
    void testModeEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.modeEvent(), 
            "Deve lançar exceção ao calcular moda sem eventos");

        publishAndConsume(event1);
        assertEquals("Teatro", consumer.modeEvent(), 
            "Moda com um único local Teatro deve ser Teatro");

        publishAndConsume(event2);
        publishAndConsume(event3);
        assertEquals("Teatro", consumer.modeEvent(), 
            "Moda com dois eventos no Teatro e um na Arena deve ser Teatro");
    }

    @Test
    void testPercentEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.percentEvent("Show A"), 
            "Deve lançar exceção ao calcular porcentagem sem eventos");

        publishAndConsume(event1);
        assertEquals(100.0f, consumer.percentEvent("Show A"), 
            "Porcentagem para Show A com um único evento deve ser 100%");

        publishAndConsume(event2);
        assertEquals(50.0f, consumer.percentEvent("Show A"), 
            "Porcentagem para Show A com um de dois eventos deve ser 50%");
    }

    @Test
    void testUpdateStatistics() {
        assertEquals(0, consumer.getEventsConsumed(), 
            "Contagem inicial de eventos deve ser zero");
        
        publishAndConsume(event1);
        assertEquals(1, consumer.getEventsConsumed(), 
            "Contagem após consumir um evento deve ser 1");
        assertEquals(150.0f, consumer.maxValue(), 
            "Valor máximo após primeiro evento deve ser 150.0");
        assertEquals(150.0f, consumer.minValue(), 
            "Valor mínimo após primeiro evento deve ser 150.0");
        
        publishAndConsume(event2);
        assertEquals(2, consumer.getEventsConsumed(), 
            "Contagem após consumir dois eventos deve ser 2");
        assertEquals(180.0f, consumer.maxValue(), 
            "Valor máximo após segundo evento deve ser 180.0");
        assertEquals(150.0f, consumer.minValue(), 
            "Valor mínimo após segundo evento deve permanecer 150.0");
    }

    @Test
    void testValidateEventsConsumed() {
        assertThrows(IllegalStateException.class, () -> consumer.validateEventsConsumed(), 
            "Deve lançar exceção ao validar sem eventos consumidos");

        publishAndConsume(event1);
        assertDoesNotThrow(() -> consumer.validateEventsConsumed(), 
            "Não deve lançar exceção após consumir um evento");
    }

    private void publishAndConsume(Event event) {
        stream.publish(event);
        consumer.consumeEvent(stream);
    }
}