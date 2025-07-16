package br.lpm.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class EventTest {
    @Test 
    void testSetEventBody() {
        // Test with Sale event body
        Sale sale = new Sale("Test Product", 100.0f, LocalDate.now().plusDays(1), "Store");
        Event event = new Event(sale);
        assertEquals(sale, event.getBody(), "O corpo do evento deve ser igual ao objeto Sale criado");
        assertTrue(event.getBody() instanceof Sale, "Body deve ser uma instância de Sale");
        
        // Test with Subscription event body
        Subscription subscription = new Subscription("Basic Plan", 50.0f, LocalDate.now().plusDays(1), "John Doe");
        event.setEventBody(subscription);
        assertEquals(subscription, event.getBody(), "O corpo do evento deve ser igual ao objeto Subscription criado");
        assertTrue(event.getBody() instanceof Subscription, "Body deve ser uma instância de Subscription");
        
        // Test with Ticket event body
        Ticket ticket = new Ticket("Concert", 150.0f, LocalDate.now().plusDays(1), "Stadium");
        event.setEventBody(ticket);
        assertEquals(ticket, event.getBody(), "O corpo do evento deve ser igual ao objeto Ticket criado");
        assertTrue(event.getBody() instanceof Ticket, "Body deve ser uma instância de Ticket");

        // Test with null event body
        event.setEventBody(null);
        assertNull(event.getBody(), "O corpo do evento deve ser null");
    }
}