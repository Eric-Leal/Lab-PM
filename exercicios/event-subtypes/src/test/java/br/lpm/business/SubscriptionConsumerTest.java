package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class SubscriptionConsumerTest {
    private SubscriptionConsumer consumer;
    private Stream stream;
    private Event event1, event2, event3;

    @BeforeEach
    void setUp() {
        stream = new Stream();
        consumer = new SubscriptionConsumer(stream);

        Subscription sub1 = new Subscription("Netflix", 50.0f, LocalDate.of(2026,02,01), "Carlos");
        Subscription sub2 = new Subscription("Disney+", 30.0f, LocalDate.of(2026,02,01), "Ana");
        Subscription sub3 = new Subscription("Netflix", 50.0f, LocalDate.of(2026,02,01), "Carlos");
        
        event1 = new Event(sub1);
        event2 = new Event(sub2);
        event3 = new Event(sub3);
    }

    @Test
    void testAvgValue() {
        assertThrows(IllegalStateException.class, () -> consumer.avgValue(), 
            "Should throw exception when calculating average with no events");

        publishAndConsume(event1);
        assertEquals(50.0f, consumer.avgValue(), 
            "Average with single event of value 50.0 should be 50.0");

        publishAndConsume(event2);
        assertEquals(40.0f, consumer.avgValue(), 
            "Average with events of values 50.0 and 30.0 should be 40.0");
    }

    @Test
    void testMaxValue() {
        publishAndConsume(event1);
        assertEquals(50.0f, consumer.maxValue(), 
            "Maximum value with single event of value 50.0 should be 50.0");

        publishAndConsume(event2);
        assertEquals(50.0f, consumer.maxValue(), 
            "Maximum value after adding event of value 30.0 should remain 50.0");
    }

    @Test
    void testMinValue() {
        publishAndConsume(event1);
        assertEquals(50.0f, consumer.minValue(), 
            "Minimum value with single event of value 50.0 should be 50.0");

        publishAndConsume(event2);
        assertEquals(30.0f, consumer.minValue(), 
            "Minimum value after adding event of value 30.0 should be 30.0");
    }

    @Test
    void testModeEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.modeEvent(), 
            "Should throw exception when calculating mode with no events");

        publishAndConsume(event1);
        assertEquals("Netflix", consumer.modeEvent(), 
            "Mode with single Netflix subscription should be Netflix");

        publishAndConsume(event2);
        publishAndConsume(event3);
        assertEquals("Netflix", consumer.modeEvent(), 
            "Mode with two Netflix and one Disney+ subscription should be Netflix");
    }

    @Test
    void testPercentEvent() {
        assertThrows(IllegalStateException.class, () -> consumer.percentEvent("Carlos"), 
            "Should throw exception when calculating percentage with no events");

        publishAndConsume(event1);
        assertEquals(100.0f, consumer.percentEvent("Carlos"), 
            "Percentage for Carlos with single event should be 100%");

        publishAndConsume(event2);
        assertEquals(50.0f, consumer.percentEvent("Carlos"), 
            "Percentage for Carlos with one out of two events should be 50%");
    }

    @Test
    void testUpdateStatistics() {
        assertEquals(0, consumer.getEventsConsumed(), 
            "Initial event count should be zero");
        
        publishAndConsume(event1);
        assertEquals(1, consumer.getEventsConsumed(), 
            "Event count after consuming one event should be 1");
        assertEquals(50.0f, consumer.maxValue(), 
            "Maximum value after first event should be 50.0");
        assertEquals(50.0f, consumer.minValue(), 
            "Minimum value after first event should be 50.0");
        
        publishAndConsume(event2);
        assertEquals(2, consumer.getEventsConsumed(), 
            "Event count after consuming two events should be 2");
        assertEquals(50.0f, consumer.maxValue(), 
            "Maximum value after second event should remain 50.0");
        assertEquals(30.0f, consumer.minValue(), 
            "Minimum value after second event should be 30.0");
    }

    @Test
    void testValidateEventsConsumed() {
        assertThrows(IllegalStateException.class, () -> consumer.validateEventsConsumed(), 
            "Should throw exception when validating with no events consumed");

        publishAndConsume(event1);
        assertDoesNotThrow(() -> consumer.validateEventsConsumed(), 
            "Should not throw exception after consuming an event");
    }

    private void publishAndConsume(Event event) {
        stream.publish(event);
        consumer.consumeEvent(stream);
    }
}