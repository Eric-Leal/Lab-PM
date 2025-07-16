package br.lpm.prato;

public enum Tamanho {
    GRANDE( 9.90),
    MEDIO(12.90),
    PEQUENO(15.90);

    private final double preco;

    Tamanho(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return this.name().toLowerCase();
    }
    
}
