package br.lpm.bebida;

import br.lpm.core.Bebida;

public class Kocha extends Bebida {

    @Override 
    public double getPreco() {
        return 0.0;
    }
 
    @Override
    public String toString() {
        return "Ko-cha";
    }
}
