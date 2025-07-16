package br.lpm.business;

public abstract class EventConsumer {
    public static final int MAX_EVENTS = 1000;
    private int eventsConsumed;        

    protected EventConsumer() {
        this.eventsConsumed = 0;

    } 

    public Event consumeEvent(Stream stream) {
        if (eventsConsumed == MAX_EVENTS) {
            throw new IllegalStateException("Limite máximo de eventos atingido");
        }
        Event event = stream.consume();
        
        updateStatistics(event);
        eventsConsumed++;
        
        return event;
    }

    public abstract void updateStatistics(Event event);
    public abstract float percentEvent(String identifier);
    public abstract String modeEvent();


    public int getEventsConsumed() {
        return eventsConsumed;
    }
    public abstract int getIdentifierSize();
    public abstract String getIdentifierKey(int index); 
    public abstract int getIdentifierCount(int index);

    public abstract String getEventTypeName();
    public abstract String getIdentifierLabel();

    @Override
    public abstract String toString();
}