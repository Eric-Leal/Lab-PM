package br.lpm.business;

public class SubscriptionConsumer extends EventConsumer {
    
    private float maxValue;
    private float minValue;
    private float totalValue;

    private final String[] identifierKeys;  
    private final Integer[] identifierCount;
    private int identifierSize;             
    
    private final String[] attributeKeys;   
    private final Integer[] attributeCount;  
    private int attributeSize;

    public SubscriptionConsumer(Stream stream) {
        super();
        this.maxValue = Float.MIN_VALUE;
        this.minValue = Float.MAX_VALUE;
        this.totalValue = 0;
        
        this.identifierKeys = new String[MAX_EVENTS];
        this.identifierCount = new Integer[MAX_EVENTS];
        this.identifierSize = 0;
        
        this.attributeKeys = new String[MAX_EVENTS];
        this.attributeCount = new Integer[MAX_EVENTS];
        this.attributeSize = 0;
    }
   
    public void validateEventsConsumed() {
        if (getEventsConsumed() == 0) {
            throw new IllegalStateException("Nenhum evento foi consumido");
        }
    }

    @Override
    public void updateStatistics(Event event) {
        Subscription subscription = (Subscription) event.getBody();
        updateValueStatistics(subscription.getMonthlyValue());
        updateIdentifierCount(subscription.getSubscriberName());
        updateAttributeCount(subscription.getSubscriptionName());
    }

    private void updateValueStatistics(float value) {
        totalValue += value;
        maxValue = Math.max(maxValue, value);
        minValue = Math.min(minValue, value);
    }

    private void updateIdentifierCount(String identifier) {
        boolean found = false;
        for (int i = 0; i < identifierSize; i++) {
            if (identifierKeys[i].equals(identifier)) {
                identifierCount[i]++;
                found = true;
                break;
            }
        }
        if (!found && identifierSize < MAX_EVENTS) {
            identifierKeys[identifierSize] = identifier;
            identifierCount[identifierSize] = 1;
            identifierSize++;
        }
    }

    private void updateAttributeCount(String attribute) {
        boolean found = false;
        for (int i = 0; i < attributeSize; i++) {
            if (attributeKeys[i].equals(attribute)) {
                attributeCount[i]++;
                found = true;
                break;
            }
        }
        if (!found && attributeSize < MAX_EVENTS) {
            attributeKeys[attributeSize] = attribute;
            attributeCount[attributeSize] = 1;
            attributeSize++;
        }
    }
    public float maxValue() {
        validateEventsConsumed();
        return maxValue;
    }

    public float minValue() {
        validateEventsConsumed();
        return minValue;
    }

    public float avgValue() {
        validateEventsConsumed();
        return totalValue / getEventsConsumed();
    } 

    @Override
    public int getIdentifierSize() {
        return identifierSize;
    }

    @Override
    public String getIdentifierKey(int index) {
        if (index < 0 || index >= identifierSize) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        return identifierKeys[index];
    }

    @Override
    public int getIdentifierCount(int index) {
        if (index < 0 || index >= identifierSize) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        return identifierCount[index];
    }

    @Override
    public float percentEvent(String identifier) {
        validateEventsConsumed();
        int count = 0;
        for (int i = 0; i < identifierSize; i++) {
            if (identifierKeys[i].equals(identifier)) {
                count = identifierCount[i];
                break;
            }
        }
        return (float) count / getEventsConsumed() * 100;
    }

    @Override
    public String modeEvent() {
        validateEventsConsumed();
        if (attributeSize == 0) {
            return null;
        }
        
        String mostFrequent = attributeKeys[0];
        int maxFrequency = attributeCount[0];

        for (int i = 1; i < attributeSize; i++) {
            if (attributeCount[i] > maxFrequency) {
                maxFrequency = attributeCount[i];
                mostFrequent = attributeKeys[i];
            }
        }
        return mostFrequent;
    }

    @Override
    public String getEventTypeName() {
        return "Assinaturas";
    }

    @Override
    public String getIdentifierLabel() {
        return "Assinante";
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Consumidor de Eventos de Tickets:\n");
        sb.append("Número total de eventos consumidos: ").append(getEventsConsumed()).append("\n");
        sb.append("Valor máximo: ").append(maxValue).append("\n");
        sb.append("Valor mínimo: ").append(minValue).append("\n");
        sb.append("Valor médio: ").append(avgValue()).append("\n");
        sb.append("Identificadores:\n");
        return sb.toString();
    }  
}