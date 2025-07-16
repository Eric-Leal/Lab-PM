package br.lpm.business;

import java.time.LocalDate;

public class Sale implements EventBody {

    private String description;
    private float value;
    private LocalDate date;
    private String origin;

    public Sale(String description, float value, LocalDate date, String origin) {
        setDescription(description);
        setValue(value);
        setDate(date);
        setOrigin(origin);
    }

    public Sale() {
        description = "Produto basico";
        value = 0.1F;
        date = LocalDate.now();
        origin = "Responsavel";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("O valor deve ser preenchido");
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        if (origin != null && !origin.trim().isEmpty() && origin.matches("^[\\p{L} ]*$")) {
            this.origin = origin;
        } else {
            throw new IllegalArgumentException("O nome não pode ser vazio e não pode conter números.");
        }
    }

    @Override
    public String toString() {
        return "Tipo: venda\n" +
               "Descrição: " + description + "\n" +
               "Responsável: " + origin + "\n" +
               "Valor: R$ " + String.format("%.2f", value) + "\n" +
               "Data: " + date;
    }

}