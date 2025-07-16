package br.lpm.business;

import java.time.LocalDate;

public class Ticket implements EventBody {

    private String event;
    private float value;
    private LocalDate date;
    private String address;

    public Ticket(String event, float value, LocalDate date, String address) {
        setEvent(event);
        setValue(value);
        setDate(date);
        setAddress(address);
    }

    public Ticket() {
        event = "Evento padrão";
        value = 0.1F;
        date = LocalDate.now().plusDays(1); // Data padrão: amanhã
        address = "Local padrão";
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        if (event != null && !event.trim().isEmpty() && event.matches("^[\\p{L} ]*$")) {
            this.event = event;
        } else {
            throw new IllegalArgumentException("O nome do evento não pode ser vazio e não deve conter números.");
        }
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if (value > 0) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("A data deve ser maior do que a atual.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && !address.trim().isEmpty()) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("O endereço não pode ser vazio.");
        }
    }

    @Override
    public String toString() {
        return "Tipo: ticket\n" +
               "Evento: " + event + "\n" +
               "Local: " + address + "\n" +
               "Valor: R$ " + String.format("%.2f", value) + "\n" +
               "Data: " + date;
    }
}