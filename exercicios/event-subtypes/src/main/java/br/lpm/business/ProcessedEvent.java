package br.lpm.business;

import java.time.LocalDateTime;

public class ProcessedEvent {
    private final long eventId;
    private final String eventType;
    private final LocalDateTime eventTimestamp;
    private final LocalDateTime timestamp;

    public ProcessedEvent(Event event) {
        this.eventId = event.getId();
        this.eventType = event.getBody().getClass().getSimpleName();
        this.eventTimestamp = event.getTimestamp();
        this.timestamp = LocalDateTime.now();
    }

    public long getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}