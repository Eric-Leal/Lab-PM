package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class SaleConsumerTest {
    private SaleConsumer consumer;
    private Stream stream;
    private Event event1, event2, event3;

    @BeforeEach
    void setUp() {
        stream = new Stream();
        consumer = new SaleConsumer(stream);

        Sale sale1 = new Sale("Produto A", 100.0f, LocalDate.of(2026,02,01), "João");
        Sale sale2 = new Sale("Produto B", 200.0f, LocalDate.of(2026,02,01), "Maria");
        Sale sale3 = new Sale("Produto A", 150.0f, LocalDate.of(2026,02,01), "João");
        
        event1 = new Event(sale1);
        event2 = new Event(sale2);
        event3 = new Event(sale3);
    }

    @Test
    void testAvgValue() {
        assertThrows(IllegalStateException.class, () -> consumer.avgValue(), 
            "Deve lançar exceção ao calcular média sem eventos");

        publishAndConsume(event1);
        assertEquals(100.0f, consumer.avgValue(), 
            "Média com um único evento de valor 100.0 deve ser 100.0");

        publishAndConsume(event2);
        assertEquals(150.0f, consumer.avgValue(), 
            "Média com eventos de valores 100.0 e 200.0 deve ser 150.0");
    }

    @Test
    void testMaxValue() {
        publishAndConsume(event1);
        assertEquals(100.0f, consumer.maxValue(), 
            "Valor máximo com um único evento de valor 100.0 deve ser 100.0");

        publishAndConsume(event2);
        assertEquals(200.0f, consumer.maxValue(), 
            "Valor máximo após adicionar evento de valor 200.0 deve ser 200.0");
    }

    @Test
    void testMinValue() {
        publishAndConsume(event1);
        assertEquals(100.0f, consumer.minValue(), 
            "Valor mínimo com um único evento de valor 100.0 deve ser 100.0");

        publishAndConsume(event2);
        assertEquals(100.0f, consumer.minValue(), 
            "Valor mínimo após adicionar evento de valor 200.0 deve permanecer 100.0");
    }

    @Test
    void testModeEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.modeEvent(), 
            "Deve lançar exceção ao calcular moda sem eventos");

        publishAndConsume(event1);
        assertEquals("Produto A", consumer.modeEvent(), 
            "Moda com um único produto A deve ser Produto A");

        publishAndConsume(event2);
        publishAndConsume(event3);
        assertEquals("Produto A", consumer.modeEvent(), 
            "Moda com dois produtos A e um produto B deve ser Produto A");
    }

    @Test
    void testPercentEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.percentEvent("João"), 
            "Deve lançar exceção ao calcular porcentagem sem eventos");

        publishAndConsume(event1);
        assertEquals(100.0f, consumer.percentEvent("João"), 
            "Porcentagem para João com um único evento deve ser 100%");

        publishAndConsume(event2);
        assertEquals(50.0f, consumer.percentEvent("João"), 
            "Porcentagem para João com um de dois eventos deve ser 50%");
    }

    @Test
    void testUpdateStatistics() {
        assertEquals(0, consumer.getEventsConsumed(), 
            "Contagem inicial de eventos deve ser zero");
        
        publishAndConsume(event1);
        assertEquals(1, consumer.getEventsConsumed(), 
            "Contagem após consumir um evento deve ser 1");
        assertEquals(100.0f, consumer.maxValue(), 
            "Valor máximo após primeiro evento deve ser 100.0");
        assertEquals(100.0f, consumer.minValue(), 
            "Valor mínimo após primeiro evento deve ser 100.0");
        
        publishAndConsume(event2);
        assertEquals(2, consumer.getEventsConsumed(), 
            "Contagem após consumir dois eventos deve ser 2");
        assertEquals(200.0f, consumer.maxValue(), 
            "Valor máximo após segundo evento deve ser 200.0");
        assertEquals(100.0f, consumer.minValue(), 
            "Valor mínimo após segundo evento deve permanecer 100.0");
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