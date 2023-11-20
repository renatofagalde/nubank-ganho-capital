package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

public class CalculadoraPrecoMedioCompra extends Calculadora {

    public CalculadoraPrecoMedioCompra() {
        this.operation="buy";
    }

    @Override
    public double calcular(MediaPonderadaLucroPrejuizo lucroPrejuizo, OperationInput operationInput) {

        double qtdAcoesAtualXAvgPond = lucroPrejuizo.getQuantidadeAcoesAtual() * lucroPrejuizo.getMediaPonderadaAtual();
        double qtdAcoesXValorCompra = getQtdAcoesXValor(operationInput);

        int somaQuantidadeAcoesCompradas = lucroPrejuizo.getQuantidadeAcoesAtual() + operationInput.getQuantity();

        double mediaPonderada = (qtdAcoesAtualXAvgPond + qtdAcoesXValorCompra) / somaQuantidadeAcoesCompradas;
        lucroPrejuizo.atualizarMediaPonderadaAtual(mediaPonderada);
        lucroPrejuizo.atualizarQuantidadeAcoesAtual(
                lucroPrejuizo.getQuantidadeAcoesAtual() + operationInput.getQuantity()
        );

        return 0;
    }
}
