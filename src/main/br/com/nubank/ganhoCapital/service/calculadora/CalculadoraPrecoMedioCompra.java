package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

public class CalculadoraPrecoMedioCompra extends Calculadora {
    @Override
    public double calcular(MediaPonderadaLucroPrejuizo lucroPrejuizo, OperationInput operationInput) {

        double qtdAcoesAtualXAvgPond = lucroPrejuizo.getQuantidadeAcoesAtual() * lucroPrejuizo.getMediaPonderadaAtual();
        double qtdAcoesXValorCompra = getQtdAcoesXValor(operationInput);

        int somaQuantidadeAcoesCompradas = lucroPrejuizo.getQuantidadeAcoesAtual() + operationInput.getQuantity();
        return (qtdAcoesAtualXAvgPond + qtdAcoesXValorCompra) / somaQuantidadeAcoesCompradas;
    }
}
