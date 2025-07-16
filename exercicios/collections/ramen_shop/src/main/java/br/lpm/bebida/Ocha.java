package br.lpm.bebida;

import br.lpm.core.Bebida;

public class Ocha extends Bebida {

    @Override 
    public double getPreco() {
        return 3.90;
    }

    @Override
    public String toString() {
        return "O-cha";
    }
    
}
