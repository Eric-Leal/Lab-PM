package br.lpm.core;

import br.lpm.prato.Tamanho;

public abstract class PratoBase implements Prato {
    private String descricao;
    private Tamanho tamanho;
    private Bebida bebida;

    public PratoBase(String descricao, Tamanho tamanho, Bebida bebida) {
        setDescricao(descricao);
        setTamanho(tamanho);
        setBebida(bebida);
    } 

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        if (bebida != null) {
            this.bebida = bebida;
        }
    }

    public Tamanho getTamanho() {
        return tamanho;
    }
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
    
    public void setDescricao(String descricao) {
        if (descricao != null && !descricao.trim().isEmpty()) {
            this.descricao = descricao;
        }
    }
    
    @Override
    public String getDescricao() {
        return descricao + " " + tamanho.getDescricao() + (bebida != null ? " + " + bebida.toString() : "");
    }
    
    @Override
    public double getPreco() {
        return tamanho.getPreco() 
                + getPrecoProteina() 
                + (bebida != null ? bebida.getPreco() : 0.0);
    }

    public abstract double getPrecoProteina();
}
