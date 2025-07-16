package br.lpm.business;

import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class DistributionPlot {
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    
    public DistributionPlot(List<String> labels, List<Integer> values) {
        if (labels == null || values == null) {
            throw new NullPointerException("Labels e values não podem ser null");
        }
        this.dataset = new DefaultCategoryDataset();
        setData(labels, values);
    }
    
    public void setData(List<String> labels, List<Integer> values) {
        // Validar parâmetros nulos
        if (labels == null || values == null) {
            throw new NullPointerException("A lista de labels e values não podem ser nulas");
        }
        
        dataset.clear();
        for (int i = 0; i < labels.size(); i++) {
            dataset.addValue(values.get(i), "Eventos", labels.get(i));
        }
        
        this.chart = ChartFactory.createBarChart(
            "Distribuição de Eventos",
            "Tipo de Evento",
            "Quantidade",
            dataset
        );
    }
    
    public void show() {
        if (chart == null) {
            throw new IllegalStateException("Dados não foram configurados. Chame setData primeiro.");
        }
        
        JFrame frame = new JFrame("Distribuição de Eventos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}