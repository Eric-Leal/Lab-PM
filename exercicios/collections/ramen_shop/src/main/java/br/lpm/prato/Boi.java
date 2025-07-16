package br.lpm.prato;

import br.lpm.core.Bebida;
import br.lpm.core.PratoBase;

public class Boi extends PratoBase {

    public Boi(String descricao, Tamanho tamanho, Bebida bebida) {
        super(descricao, tamanho, bebida);
    }

    @Override
    public double getPrecoProteina() {
        return 7.90;
    }
    
}
