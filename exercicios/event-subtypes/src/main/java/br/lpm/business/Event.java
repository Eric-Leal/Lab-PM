package br.lpm.business;

import java.time.LocalDateTime;

public class Event {
    private static int counter = 1;
    private final int id;
    private LocalDateTime timestamp;
    private EventBody body;

    public Event(EventBody eventBody) {
        this.id = counter++;
        this.timestamp = LocalDateTime.now();
        setEventBody(eventBody);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }   

    public EventBody getBody() {
        return body;
    }

    public void setEventBody(EventBody body) {
        this.body = body;
    }
}