package br.com.nubank.ganhoCapital.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class OperationInput {
    private String operation;
    private BigDecimal unit;
    private Integer quantity;
    public OperationInput(@JsonProperty("operation")String operation, @JsonProperty("unit-cost")BigDecimal unit,@JsonProperty("quantity") Integer quantity) {
        this.operation = operation;
        this.unit = unit;
        this.quantity = quantity;
    }
    public String getOperation() {
        return operation;
    }

    public BigDecimal getUnitCost() {
        return unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
