package br.lpm.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class DistributionPlotTest {
    @Test 
    void testSetData() {
        // Preparar dados de teste iniciais
        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        
        labels.add("Vendas");
        labels.add("Tickets");
        labels.add("Assinaturas");
        values.add(10);
        values.add(20);
        values.add(30);
        
        // Criar objeto DistributionPlot com dados válidos
        DistributionPlot plot = new DistributionPlot(labels, values);
        
        // Testar atualização com novos dados válidos
        List<String> newLabels = new ArrayList<>();
        List<Integer> newValues = new ArrayList<>();
        
        newLabels.add("Janeiro");
        newLabels.add("Fevereiro");
        newValues.add(50);
        newValues.add(75);
        
        // Não deve lançar exceção com dados válidos
        assertDoesNotThrow(() -> {
            plot.setData(newLabels, newValues);
        }, "Não deve lançar exceção com dados válidos");
        
        // Testar validações de null
        assertThrows(NullPointerException.class, () -> {
            plot.setData(null, values);
        }, "Deve lançar NullPointerException quando labels for null");
        
        assertThrows(NullPointerException.class, () -> {
            plot.setData(labels, null);
        }, "Deve lançar NullPointerException quando values for null");
        
        // Testar com listas vazias
        List<String> emptyLabels = new ArrayList<>();
        List<Integer> emptyValues = new ArrayList<>();
        
        assertDoesNotThrow(() -> {
            plot.setData(emptyLabels, emptyValues);
        }, "Não deve lançar exceção com listas vazias");
    }
}