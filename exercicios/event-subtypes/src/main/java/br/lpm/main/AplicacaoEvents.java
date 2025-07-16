package br.lpm.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import br.lpm.business.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;

public class AplicacaoEvents {
    private static EventConsumer consumer;
    private static int currentEventType;

    private static void eventsHistogram() {
        if (consumer.getEventsConsumed() == 0) {
            System.out.println("Não há eventos consumidos para gerar histograma!");
            return;
        }
    
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < consumer.getIdentifierSize(); i++) {
            dataset.addValue(
                consumer.getIdentifierCount(i),
                consumer.getEventTypeName(),
                consumer.getIdentifierKey(i));
        }
    
        JFreeChart chart = ChartFactory.createBarChart(
                "Histograma de Eventos",
                "Identificador",
                "Quantidade",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    
        JFrame frame = new JFrame("Histograma de Eventos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int escolherTipoEvento(Scanner scanner) {
        System.out.println("\nEscolha o tipo de evento:");
        System.out.println("1 - Vendas");
        System.out.println("2 - Tickets");
        System.out.println("3 - Assinaturas");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        if (tipo < 1 || tipo > 3) {
            throw new IllegalArgumentException("Tipo de evento inválido");
        }
        return tipo;
    }

    private static void updateConsumer(Stream stream, int tipo) {
        consumer = switch (tipo) {
            case 1 -> new SaleConsumer(stream);
            case 2 -> new TicketConsumer(stream);
            case 3 -> new SubscriptionConsumer(stream);
            default -> throw new IllegalStateException("Tipo de evento inválido");
        };
    }

    private static String getCurrentEventTypeName() {
        return switch (currentEventType) {
            case 1 -> "venda";
            case 2 -> "ticket";
            case 3 -> "assinatura";
            default -> throw new IllegalStateException("Tipo de evento inválido");
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StreamMonitor monitor = new StreamMonitor();
        Stream stream = new Stream();
        stream.setMonitor(monitor);
        monitor.startMonitoring();

        System.out.println("Bem-vindo ao sistema de eventos!");
        currentEventType = escolherTipoEvento(scanner);
        updateConsumer(stream, currentEventType);
        String tipoNome = getCurrentEventTypeName();

        int opcao;
        do {
            System.out.println("\nMenu de Eventos");
            System.out.println("1 - Cadastrar " + tipoNome);
            System.out.println("2 - Consumir eventos");
            System.out.println("3 - Consultar percentual de evento");
            System.out.println("4 - Limpar fluxo");
            System.out.println("5 - Relatório de monitoramento");
            System.out.println("6 - Visualizar primeiro evento");
            System.out.println("7 - Gerar histograma");
            System.out.println("8 - Gráfico de distribuição");
            System.out.println("9 - Trocar tipo de evento (atual: " + tipoNome + ")");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        EventBody content;
                        switch (currentEventType) {
                            case 1: // Venda
                                System.out.print("Descrição: ");
                                String descricao = scanner.nextLine();
                                System.out.print("Responsável: ");
                                String responsavel = scanner.nextLine();
                                System.out.print("Valor: ");
                                float valor = scanner.nextFloat();
                                scanner.nextLine();
                                System.out.print("Data (dd/MM/yyyy): ");
                                LocalDate data = LocalDate.parse(scanner.nextLine(), dataFormatada);
                                content = new Sale(descricao, valor, data, responsavel);
                                break;

                            case 2: // Ticket
                                System.out.print("Evento: ");
                                String evento = scanner.nextLine();
                                System.out.print("Local: ");
                                String local = scanner.nextLine();
                                System.out.print("Valor: ");
                                valor = scanner.nextFloat();
                                scanner.nextLine();
                                System.out.print("Data (dd/MM/yyyy): ");
                                data = LocalDate.parse(scanner.nextLine(), dataFormatada);
                                content = new Ticket(evento, valor, data, local);
                                break;

                            case 3: // Assinatura
                                System.out.print("Nome da assinatura: ");
                                String nomeAssinatura = scanner.nextLine();
                                System.out.print("Nome do assinante: ");
                                String nomeAssinante = scanner.nextLine();
                                System.out.print("Valor mensal: ");
                                valor = scanner.nextFloat();
                                scanner.nextLine();
                                System.out.print("Data de início (dd/MM/yyyy): ");
                                data = LocalDate.parse(scanner.nextLine(), dataFormatada);
                                content = new Subscription(nomeAssinatura, valor, data, nomeAssinante);
                                break;

                            default:
                                throw new IllegalStateException("Tipo de evento inválido");
                        }

                        stream.publish(new Event(content));
                        System.out.println(tipoNome + " cadastrado com sucesso!");
                        break;

                    case 2:
                        if (stream.isEmpty()) {
                            System.out.println("Não há eventos para consumir!");
                            break;
                        }

                        System.out.print("Quantidade a consumir: ");
                        int qtd = scanner.nextInt();
                        
                        for (int i = 0; i < qtd && !stream.isEmpty(); i++) {
                            consumer.consumeEvent(stream);
                            System.out.println("Evento consumido com sucesso!");
                        }

                        if (consumer.getEventsConsumed() > 0) {
                            System.out.println("\nEstatísticas:");
                            consumer.toString();
                        }
                        break;

                    case 3:
                        if (consumer.getEventsConsumed() == 0) {
                            System.out.println("Nenhum evento foi consumido ainda!");
                            break;
                        }
                        System.out.print("Digite o identificador do evento: ");
                        String identifier = scanner.nextLine();
                        try {
                            float percent = consumer.percentEvent(identifier);
                            System.out.printf("Percentual do evento '%s': %.2f%%\n", 
                                identifier, percent);
                        } catch (IllegalStateException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 4:
                        stream.removeAll();
                        System.out.println("Fluxo limpo com sucesso!");
                        break;

                    case 5:
                        System.out.println(monitor.toString());
                        break;

                    case 6:
                        if (stream.isEmpty()) {
                            System.out.println("Não há eventos no fluxo!");
                            break;
                        }
                        Event firstEvent = stream.get();
                        System.out.println("Primeiro evento no fluxo:");
                        System.out.println("ID: " + firstEvent.getId());
                        System.out.println("Timestamp: " + firstEvent.getTimestamp());
                        System.out.println(firstEvent.getBody().toString());
                        break;

                    case 7:
                        eventsHistogram();
                        break;

                    case 8:
                        monitor.stopMonitoring();
                        if(monitor.getEventCount() > 0){
                            monitor.plotEventDistribution();
                        }else{
                            System.out.println("Não há eventos para gerar o gráfico de distribuição!");
                        }
                        monitor.startMonitoring();
                        break;

                    case 9:
                        currentEventType = escolherTipoEvento(scanner);
                        updateConsumer(stream, currentEventType);
                        tipoNome = getCurrentEventTypeName();
                        System.out.println("Tipo de evento alterado para: " + tipoNome);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        } while(opcao != 0);

        monitor.stopMonitoring();
        scanner.close();
    }
}