package br.lpm.prato;

import br.lpm.core.Bebida;
import br.lpm.core.PratoBase;

public class Porco extends PratoBase {

    public Porco(String descricao, Tamanho tamanho, Bebida bebida) {
        super(descricao, tamanho, bebida);
    }

    @Override
    public double getPrecoProteina() {
        return 5.90;
    }
    
}
