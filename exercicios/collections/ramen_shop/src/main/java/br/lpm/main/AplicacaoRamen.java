package br.lpm.main;

import br.lpm.prato.Boi;
import br.lpm.prato.Porco;
import br.lpm.prato.Vegano;
import br.lpm.prato.Tamanho;
import br.lpm.bebida.Refrigerante;
import br.lpm.bebida.Ocha;
import br.lpm.bebida.Kocha;
import br.lpm.acrescimo.Tofu;
import br.lpm.acrescimo.Shitake;
import br.lpm.acrescimo.Chilli;
import br.lpm.acrescimo.ProteinaExtra;
import br.lpm.business.Pedido;
import br.lpm.business.Cliente;
import br.lpm.business.ListaDeEspera;
import br.lpm.business.Balanco;
import br.lpm.core.Prato;

public class AplicacaoRamen {
    public static void main(String[] args) {
        // Criação dos pratos base
        Prato boi = new Boi("Ramen de Boi", Tamanho.GRANDE, new Refrigerante());
        boi = new Chilli(boi);
        boi = new Tofu(boi);
        boi = new ProteinaExtra(boi);

        Prato porco = new Porco("Ramen de Porco", Tamanho.MEDIO, new Ocha());
        porco = new Tofu(porco);
        porco = new Chilli(porco);

        Prato vegano = new Vegano("Ramen Vegano", Tamanho.PEQUENO, new Kocha());
        vegano = new Shitake(vegano);



        Pedido pedido1 = new Pedido(boi);
        Pedido pedido2 = new Pedido(porco);
        Pedido pedido3 = new Pedido(vegano);

        Cliente cliente1 = new Cliente("Jorge", pedido1);
        Cliente cliente2 = new Cliente("Barbara", pedido2);
        Cliente cliente3 = new Cliente("Ana", pedido3);

        ListaDeEspera listaDeEspera = ListaDeEspera.getInstance();

        listaDeEspera.addObserver(cliente1);
        listaDeEspera.addObserver(cliente2);
        listaDeEspera.addObserver(cliente3);

        listaDeEspera.addPedido(pedido1);
        listaDeEspera.addPedido(pedido2);
        listaDeEspera.addPedido(pedido3);

        System.out.println("Estado inicial da lista de espera: " + listaDeEspera.getState());

    
        listaDeEspera.setState(1); 
        System.out.println("Estado da lista de espera durante o processamento: " + listaDeEspera.getState());

        System.out.println(listaDeEspera.notifyAllObservers()); 
        listaDeEspera.setState(2); 
        System.out.println("Estado final da lista de espera: " + listaDeEspera.getState());

        Balanco balanco = Balanco.getInstance();
        balanco.addPedido(pedido1);
        balanco.addPedido(pedido2);
        balanco.addPedido(pedido3);

        System.out.println("\n--- Balanço ---");
        System.out.println(balanco.exibirPedidos());
        System.out.println("Quantidade de pedidos: " + balanco.getQuantidadePedidos());
        System.out.printf("Receita total: R$ %.2f\n", balanco.getReceitaTotal());
        System.out.printf("Ticket médio: R$ %.2f\n", balanco.getTicketMedio());
    }
}