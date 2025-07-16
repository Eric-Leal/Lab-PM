package br.lpm.acrescimo;

import br.lpm.core.Acrescimo;
import br.lpm.core.Prato;

public class Crotouns extends Acrescimo {

    public Crotouns(Prato prato) {
        super(prato);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Crotouns";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 2.00;
    }
    
}
