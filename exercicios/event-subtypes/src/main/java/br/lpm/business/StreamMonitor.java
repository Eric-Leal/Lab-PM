package br.lpm.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.Duration;

public class StreamMonitor {
    private int eventCount;
    private String[] eventTypes;
    private int[] eventTypeCount;
    private int typeCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ProcessedEvent[] processedEvents;
    private int processedCount;
    public static final int MAX_PROCESSED = 1000;
    public static final int MAX_TYPES = 10;

    private double processingRate;
    private String mostFrequentType;

    public StreamMonitor() {
        this.eventCount = 0;
        this.eventTypes = new String[MAX_TYPES];
        this.eventTypeCount = new int[MAX_TYPES];
        this.typeCount = 0;
        this.processedEvents = new ProcessedEvent[MAX_PROCESSED];
        this.processedCount = 0;
        this.processingRate = 0;
        this.mostFrequentType = null;
    }

    public void startMonitoring() {
        this.startTime = LocalDateTime.now();
    }

    public void stopMonitoring() {
        this.endTime = LocalDateTime.now();
        calculateMetrics();
    }

    private void calculateMetrics() {
        Duration duration = Duration.between(startTime, endTime);
        double minutes = duration.toMinutes();
        if (minutes > 0) {
            this.processingRate = eventCount / minutes;
        }

        if (typeCount == 0) {
            this.mostFrequentType = null;
        } else {
            String mostFrequent = eventTypes[0];
            int maxCount = eventTypeCount[0];

            for (int i = 1; i < typeCount; i++) {
                if (eventTypeCount[i] > maxCount) {
                    maxCount = eventTypeCount[i];
                    mostFrequent = eventTypes[i];
                }
            }
            this.mostFrequentType = mostFrequent;
        }
    }

    private void updateEventTypeCount(Event event) {
        String eventType = event.getBody().getClass().getSimpleName();
        boolean found = false;
        int i = 0;
        
        while (i < typeCount && !found) {
            if (eventTypes[i].equals(eventType)) {
                eventTypeCount[i]++;
                found = true;
            }
            i++;
        }
        
        if (!found && typeCount < MAX_TYPES) {
            eventTypes[typeCount] = eventType;
            eventTypeCount[typeCount] = 1;
            typeCount++;
        }
    }

    public void recordEvent(Event event) {
        eventCount++;
        updateEventTypeCount(event);
    }

    public void recordProcessedEvent(ProcessedEvent processedEvent) {
        if (processedCount < MAX_PROCESSED) {
            processedEvents[processedCount] = processedEvent;
            processedCount++;
        }
    }
    public int getEventCount() {
        return eventCount;
    }

    public String getMostFrequentEventType() {
        return mostFrequentType;
    }

    public int getEventTypeCount(String eventType) {
        for (int i = 0; i < typeCount; i++) {
            if (eventTypes[i].equals(eventType)) {
                return eventTypeCount[i];
            }
        }
        return 0;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public ProcessedEvent[] getProcessedEvents() {
        return processedEvents;
    }

    public double getEventProcessingRate() {
        return processingRate;
    }

    
    
    public void plotEventDistribution() {
        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        
        for (int i = 0; i < typeCount; i++) {
            labels.add(eventTypes[i]);
            values.add(eventTypeCount[i]);
        }
        
        DistributionPlot plot = new DistributionPlot(labels, values);
        plot.show();
    }
    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append("Relatório de Monitoramento\n");
        report.append("-------------------------\n");
        report.append(String.format("Total de eventos: %d\n", eventCount));
        report.append(String.format("Taxa de processamento: %.2f eventos/minuto\n", processingRate));
        report.append(String.format("Tipo mais frequente: %s\n", mostFrequentType));
        report.append("\nContagem por tipo:\n");
        
        for (int i = 0; i < typeCount; i++) {
            report.append(String.format("%s: %d eventos\n", eventTypes[i], eventTypeCount[i]));
        }
        
        return report.toString();
    }
}