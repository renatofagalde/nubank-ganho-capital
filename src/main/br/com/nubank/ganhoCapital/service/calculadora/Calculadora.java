package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

public abstract class Calculadora implements CalculadoraPrecoMedio {

    protected String operation;

    protected double getQtdAcoesXValor(OperationInput operation) {
        return operation.getUnitCost().doubleValue() * operation.getQuantity().doubleValue();
    }

    public String getOperation() {
        return operation;
    }
}
