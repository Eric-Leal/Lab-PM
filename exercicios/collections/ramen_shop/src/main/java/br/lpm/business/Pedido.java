package br.lpm.business;

import br.lpm.core.Prato;

public class Pedido {
    private final int ID;
    private static int contador = 0;
    private Prato prato;

    public Pedido(Prato prato) {
        this.ID = ++contador;
        setPrato(prato);
    }

    public int getId() {
        return ID;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        if (prato != null) {
            this.prato = prato;
        }
    }

    @Override 
    public String toString() {
        return String.format("Pedido " + 
                ID +
                ": " + prato.getDescricao()+
                ", precoTotal = " + "%.2f", prato.getPreco());
    }
}
