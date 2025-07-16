package br.lpm.prato;

import br.lpm.core.Bebida;
import br.lpm.core.PratoBase;

public class Vegano extends PratoBase {

    public Vegano(String descricao, Tamanho tamanho, Bebida bebida) {
        super(descricao, tamanho, bebida);
    }

    @Override
    public double getPrecoProteina() {
        return 3.90;
    }
    
}
