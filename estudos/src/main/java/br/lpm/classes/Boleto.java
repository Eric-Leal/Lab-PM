package br.lpm.classes;

import java.time.LocalDate;

public class Boleto {
    private float valor;
    private LocalDate vencimento;
    private LocalDate pagamento;

    public Boleto(float valor, LocalDate vencimento, LocalDate pagamento) {
        setValor(valor);
        setPagamento(pagamento);
        setVencimento(vencimento);
    }    

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        if(valor > 0){
        this.valor = valor;
        }
    }
    public LocalDate getVencimento() {
        return vencimento;
    }
    public void setVencimento(LocalDate vencimento) {
        if(vencimento.isAfter(LocalDate.now())){
            this.vencimento = vencimento;
        }
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        if (pagamento != null && vencimento != null && pagamento.isBefore(vencimento)) {
            this.pagamento = pagamento;
        }
    }


}
