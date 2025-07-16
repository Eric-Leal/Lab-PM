package br.lpm.acrescimo;

import br.lpm.core.Acrescimo;
import br.lpm.core.Prato;

public class Chilli extends Acrescimo {

    public Chilli(Prato prato) {
        super(prato);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Chilli";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 2.50;
    }   
    
}
