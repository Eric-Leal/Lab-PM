package br.lpm.business;

import java.util.ArrayList;
import java.util.List;

public class Balanco {
    private static final Balanco INSTANCE = new Balanco();
    private List<Pedido> pedidos;
    
    public static Balanco getInstance() {
        return INSTANCE;
    }

    private Balanco() {
        this.pedidos = new ArrayList<>();
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public int getQuantidadePedidos() {
        return pedidos.size();
    }

    public double getReceitaTotal() {
        return pedidos.isEmpty() ? 0.0 : pedidos.stream()
                .mapToDouble(pedido -> pedido.getPrato().getPreco())
                .sum();
    }

    public double getTicketMedio() {
        return pedidos.isEmpty() ? 0.0 : getReceitaTotal() / getQuantidadePedidos();
    }

    public String exibirPedidos() {
        return pedidos.isEmpty() ? "Nenhum pedido no Balanço.\n"
                : "Pedidos:\n" +
                        pedidos.stream()
                                .map(Pedido::toString)
                                .reduce("", (a, b) -> a + b + "\n");
    }


}
