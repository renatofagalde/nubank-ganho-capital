package br.com.nubank.ganhoCapital.model;

import java.math.BigDecimal;

public class OperationInput {
    private String operation;
    private BigDecimal unitCost;
    private Integer quantity;

    public OperationInput(String operation, BigDecimal unitCost, Integer quantity) {
        this.operation = operation;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OperationInput{" +
                "operation='" + operation + '\'' +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }
}
