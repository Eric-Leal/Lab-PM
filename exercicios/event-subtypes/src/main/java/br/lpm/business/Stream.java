package br.lpm.business;

public class Stream {
    public static final int MAX_EVENTS = 100;
    private Event[] events;
    private int size;
    private StreamMonitor monitor;

    public Stream() {
        events = new Event[MAX_EVENTS];
        size = 0;
    }

    public void setMonitor(StreamMonitor monitor) {
        if (monitor != null) {
            this.monitor = monitor;
        }
    }

    public StreamMonitor getMonitor() {
        return monitor;
    }

    public void publish(Event event) {
        if (size >= MAX_EVENTS) {
            throw new IllegalStateException("Stream está cheio");
        }
        events[size++] = event;

        if (monitor != null) {
            monitor.recordEvent(event);
        }
    }

    public Event consume() {
        if (size == 0) {
            throw new IllegalStateException("Stream vazio");
        }

        Event event = events[0];
        for (int i = 0; i < size - 1; i++) {
            events[i] = events[i + 1];
        }
        events[size - 1] = null;
        size--;
        
        if (monitor != null) {
            monitor.recordProcessedEvent(new ProcessedEvent(event));
        }
        
        return event;
    }

    public Event get() {
        if (size == 0) {
            throw new IllegalStateException("Stream vazio");
        }
        
        Event event = events[0];
        if (monitor != null) {
            monitor.recordProcessedEvent(new ProcessedEvent(event));
        }
        
        return event;
    }

    public void removeAll() {
        for (int i = 0; i < size; i++) {
            events[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return(size <= 0);
    }
}