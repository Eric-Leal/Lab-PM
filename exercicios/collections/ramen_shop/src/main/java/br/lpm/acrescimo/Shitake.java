package br.lpm.acrescimo;

import br.lpm.core.Acrescimo;
import br.lpm.core.Prato;

public class Shitake extends Acrescimo {

    public Shitake(Prato prato) {
        super(prato);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Shitake";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 6.90;
    }
    
}
