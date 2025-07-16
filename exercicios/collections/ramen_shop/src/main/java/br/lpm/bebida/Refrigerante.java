package br.lpm.bebida;

import br.lpm.core.Bebida;

public class Refrigerante extends Bebida {

    @Override 
    public double getPreco() {
        return 5.90;
    }

    @Override
    public String toString() {
        return "Refrigerante";
    }
}
