package br.lpm.acrescimo;

import br.lpm.core.Acrescimo;
import br.lpm.core.Prato;

public class CremeAlho extends Acrescimo {

    public CremeAlho(Prato prato) {
        super(prato);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Creme de Alho";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 1.50;
    }
    
}
