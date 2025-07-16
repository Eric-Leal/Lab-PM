package br.lpm.core;

public abstract class Acrescimo implements Prato{

    private Prato prato;

    public Acrescimo(Prato prato) {
        setPrato(prato);
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        if(prato != null) {
            this.prato = prato;
        }
    }

    @Override 
    public String getDescricao() {
        return prato.getDescricao();
    }
    @Override
    public double getPreco() {
        return prato.getPreco();
    }
}
