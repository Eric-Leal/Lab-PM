package br.lpm.business;

import java.time.LocalDate;

public class Subscription implements EventBody {
    private String subscriptionName;
    private float monthlyValue;
    private LocalDate startDate;
    private String subscriberName;

    public Subscription(String subscriptionName, float monthlyValue, LocalDate startDate, String subscriberName) {
        setSubscriptionName(subscriptionName);
        setMonthlyValue(monthlyValue);
        setStartDate(startDate);
        setSubscriberName(subscriberName);
    }

    public Subscription() {
        subscriptionName = "Plano Básico";
        monthlyValue = 29.90F;
        startDate = LocalDate.now();
        subscriberName = "Assinante Padrão";
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        if (subscriptionName != null && !subscriptionName.trim().isEmpty()) {
            this.subscriptionName = subscriptionName;
        } else {
            throw new IllegalArgumentException("O nome da assinatura não pode ser vazio");
        }
    }

    public float getMonthlyValue() {
        return monthlyValue;
    }

    public void setMonthlyValue(float monthlyValue) {
        if (monthlyValue > 0) {
            this.monthlyValue = monthlyValue;
        } else {
            throw new IllegalArgumentException("O valor mensal deve ser maior que zero");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate != null && !startDate.isBefore(LocalDate.now())) {
            this.startDate = startDate;
        } else {
            throw new IllegalArgumentException("A data de início não pode ser nula nem anterior à data atual");
        }
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        if (subscriberName != null && !subscriberName.trim().isEmpty() && subscriberName.matches("^[\\p{L} ]*$")) {
            this.subscriberName = subscriberName;
        } else {
            throw new IllegalArgumentException("O nome do assinante não pode ser vazio e deve conter apenas letras");
        }
    }

    @Override
    public String toString() {
        return "Tipo: assinatura\n" +
               "Nome da assinatura: " + subscriptionName + "\n" +
               "Assinante: " + subscriberName + "\n" +
               "Valor mensal: R$ " + String.format("%.2f", monthlyValue) + "\n" +
               "Data de início: " + startDate;
    }
}