package br.lpm.business;

import java.util.ArrayList;
import java.util.List;

import br.lpm.core.Observable;

public class ListaDeEspera extends Observable{
    
    private static final ListaDeEspera INSTANCE = new ListaDeEspera();
    private List<Pedido> pedidos;

    private ListaDeEspera() {
        super();
        this.pedidos = new ArrayList<>();
    }

    public static ListaDeEspera getInstance() {
        return INSTANCE;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void addPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    public void removePedido(Pedido pedido) {
        if (pedido != null && pedidos.remove(pedido)) {
            pedidos.remove(pedido);
        }
    }


}
