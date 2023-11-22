package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

public class CalculadoraPrecoMedioVenda extends Calculadora {

    public CalculadoraPrecoMedioVenda() {
        this.operation = "sell";
    }

    @Override
    public double calcular(MediaPonderadaLucroPrejuizo lucroPrejuizo, OperationInput operationInput) {

        double lucroAjuste = 0;
        double imposto = 0;

        double qtdAcoesXValorVenda = getQtdAcoesXValor(operationInput);
        double valorPonderado = operationInput.getQuantity().intValue() * lucroPrejuizo.getMediaPonderadaAtual();

        double lucro = qtdAcoesXValorVenda - valorPonderado;
        lucroAjuste = isLucroOuPrejuizo(lucroPrejuizo, lucro, lucroAjuste);

        lucroPrejuizo.atualizarQuantidadeAcoesAtual(
                lucroPrejuizo.getQuantidadeAcoesAtual() - operationInput.getQuantity());

        if (qtdAcoesXValorVenda <= 20000) {
            return imposto;
        }


        imposto = lucroAjuste * 0.2;
        return imposto;
    }

    private static double isLucroOuPrejuizo(MediaPonderadaLucroPrejuizo lucroPrejuizo, double lucro, double lucroAjuste) {
        if (lucro < 0) {
            lucroPrejuizo.atualizarPrejuizoPassado(
                    lucroPrejuizo.getPrejuizoPassado() + lucro
            );
        } else { //lucro
            lucroAjuste = lucro + lucroPrejuizo.getPrejuizoPassado();

            if (lucroPrejuizo.getPrejuizoPassado() < 0) {
                if (lucroAjuste >= 0) { //posso fazer a conta direto, porque o meu lucro atual é maior ou igual ao prejuizo acumulado
                    //prejuizoPassado = 0; //como é maior, posso zerar o prejuizo passado
                    lucroPrejuizo.atualizarPrejuizoPassado(0);
                } else {
                    //quando tenho lucro, mas não é suficiente para zerar o prejuizoPassado
                    //so ajusto o prejuizoPassado
                    lucroPrejuizo.atualizarPrejuizoPassado(
                            lucroPrejuizo.getPrejuizoPassado() - lucroAjuste
                    );
                    lucroAjuste = 0;
                }
            }
        }
        return lucroAjuste;
    }

    private static void ajustarPrejuizoLucro(MediaPonderadaLucroPrejuizo lucroPrejuizo, double lucroAjuste) {
        if (lucroPrejuizo.getPrejuizoPassado() < 0) {
            if (lucroAjuste >= 0) { //posso fazer a conta direto, porque o meu lucro atual é maior ou igual ao prejuizo acumulado
                //prejuizoPassado = 0; //como é maior, posso zerar o prejuizo passado
                lucroPrejuizo.atualizarPrejuizoPassado(0);
            } else {
                //quando tenho lucro, mas não é suficiente para zerar o prejuizoPassado
                //so ajusto o prejuizoPassado
                //prejuizoPassado -= lucroAjuste;
                lucroPrejuizo.atualizarPrejuizoPassado(
                        lucroPrejuizo.getPrejuizoPassado() - lucroAjuste
                );
                lucroAjuste = 0;
            }
        }
    }
}
