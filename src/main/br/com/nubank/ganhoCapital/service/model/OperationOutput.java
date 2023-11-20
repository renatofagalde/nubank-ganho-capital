package br.com.nubank.ganhoCapital.service.model;

public class OperationOutput {

    private double tax;

    public OperationOutput(double tax) {
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return String.format("{\"tax\":%.2f}", this.tax);
    }
}
