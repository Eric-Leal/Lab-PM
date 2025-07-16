package br.lpm.main;

import org.jfree.data.category.DefaultCategoryDataset;
import br.lpm.business.EventConsumer;

public class BarChart {
    private final EventConsumer eventConsumer;
    private static final String DEFAULT_COLUMN_KEY = "Quantidade";

    public BarChart(EventConsumer eventConsumer) {
        if (eventConsumer == null) {
            throw new IllegalArgumentException("EventConsumer não pode ser nulo");
        }
        this.eventConsumer = eventConsumer;
    }

    public DefaultCategoryDataset createDataset() {
        validateEventConsumer();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Usa os dados já contabilizados no EventConsumer
        for (int i = 0; i < eventConsumer.getIdentifierSize(); i++) {
            String identifier = eventConsumer.getIdentifierKey(i);
            int count = eventConsumer.getIdentifierCount(i);
            dataset.addValue(count, DEFAULT_COLUMN_KEY, identifier);
        }

        return dataset;
    }

    private void validateEventConsumer() {
        if (eventConsumer.getEventsConsumed() == 0) {
            throw new IllegalStateException("Nenhum evento foi consumido ainda");
        }
    }

    public String getChartTitle() {
        return "Distribuição de " + eventConsumer.getEventTypeName();
    }

    public String getXAxisLabel() {
        return eventConsumer.getIdentifierLabel();
    }

    public String getYAxisLabel() {
        return "Quantidade";
    }
}