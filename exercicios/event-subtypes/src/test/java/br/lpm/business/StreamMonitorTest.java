package br.lpm.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StreamMonitorTest {
    private StreamMonitor monitor;
    private Event saleEvent1, saleEvent2;
    private Event ticketEvent1;
    private Event subscriptionEvent1;
    private ProcessedEvent processedEvent;

    @BeforeEach
    void setUp() {
        monitor = new StreamMonitor();

        LocalDate data = LocalDate.now().plusYears(1); // Um ano no futuro

        Sale sale1 = new Sale("Produto 1", 100.0f, data, "John");
        Sale sale2 = new Sale("Produto 2", 200.0f, data.plusDays(1), "Mary");
        saleEvent1 = new Event(sale1);
        saleEvent2 = new Event(sale2);

        Ticket ticket1 = new Ticket("Show A", 150.0f, data.plusDays(2), "Teatro Municipal");
        ticketEvent1 = new Event(ticket1);

        Subscription subscription1 = new Subscription("Plano Premium", 99.90f, data.plusDays(3), "Carlos");
        subscriptionEvent1 = new Event(subscription1);

        processedEvent = new ProcessedEvent(saleEvent1);
    }

    @Test
    void testInitialState() {
        assertEquals(0, monitor.getEventCount(), "Contagem de eventos deve começar em zero");
        assertNull(monitor.getMostFrequentEventType(), "Tipo mais frequente deve começar como nulo");
        assertEquals(0, monitor.getEventTypeCount("Sale"), "Contagem de vendas deve começar em zero");
        assertEquals(0, monitor.getEventTypeCount("Ticket"), "Contagem de ingressos deve começar em zero");
        assertEquals(0, monitor.getEventTypeCount("Subscription"), "Contagem de assinaturas deve começar em zero");
        assertEquals(0.0, monitor.getEventProcessingRate(), "Taxa de processamento deve começar em zero");
    }

    @Test
    void testEventRecording() {
        monitor.startMonitoring();
        monitor.recordEvent(saleEvent1);
        monitor.recordEvent(saleEvent2);
        monitor.recordEvent(ticketEvent1);
        monitor.recordEvent(subscriptionEvent1);

        assertEquals(4, monitor.getEventCount(), "Deve ter registrado 4 eventos");
        assertEquals(2, monitor.getEventTypeCount("Sale"), "Deve ter 2 eventos de venda");
        assertEquals(1, monitor.getEventTypeCount("Ticket"), "Deve ter 1 evento de ingresso");
        assertEquals(1, monitor.getEventTypeCount("Subscription"), "Deve ter 1 evento de assinatura");
    }

    @Test
    void testProcessedEventRecording() {
        monitor.recordProcessedEvent(processedEvent);
        ProcessedEvent[] events = monitor.getProcessedEvents();
        assertNotNull(events, "Array de eventos processados não deve ser nulo");
        assertEquals(processedEvent, events[0], "Primeiro evento processado deve corresponder ao evento registrado");
    }

    @Test
    void testMonitoringPeriod() {
        assertNull(monitor.getStartTime(), "Horário inicial deve ser nulo antes do monitoramento começar");

        monitor.startMonitoring();
        LocalDateTime startTime = monitor.getStartTime();
        assertNotNull(startTime, "Horário inicial não deve ser nulo após início do monitoramento");

        assertTrue(startTime.isBefore(LocalDateTime.now()) ||
                startTime.isEqual(LocalDateTime.now()),
                "Horário inicial deve ser anterior ou igual ao horário atual");
    }

    @Test
    void testEventTypeFrequency() {
        monitor.startMonitoring();

        monitor.recordEvent(saleEvent1);
        monitor.recordEvent(saleEvent2);
        monitor.recordEvent(ticketEvent1);
        monitor.recordEvent(subscriptionEvent1);

        monitor.stopMonitoring();
        assertEquals("Sale", monitor.getMostFrequentEventType(),
                "Venda deve ser o tipo de evento mais frequente");
    }

    @Test
    void testProcessingRate() {
        monitor.startMonitoring();

        monitor.recordEvent(saleEvent1);
        monitor.recordEvent(saleEvent2);
        monitor.recordEvent(ticketEvent1);
        monitor.recordEvent(subscriptionEvent1);

        monitor.stopMonitoring();
        assertTrue(monitor.getEventProcessingRate() >= 0,
                "Taxa de processamento deve ser não-negativa");
    }
}