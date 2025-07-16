package br.lpm.business;

import br.lpm.core.Observer;

public class Cliente implements Observer{
    
    String nome;
    Pedido pedido;

    public Cliente(String nome, Pedido pedido) {
        setNome(nome);
        setPedido(pedido);  
    }

    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty() && nome.matches("[\\p{L} ]+")) {
        this.nome = nome;
        }
    }

    public Pedido getPedido() {
        return pedido;
    }   
    
    public void setPedido(Pedido pedido) {
        if(pedido != null) {
            this.pedido = pedido;
        }
    }

    @Override
    public String update() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNome()).append(", seu pedido está pronto.");
        return sb.toString();
    }
}
