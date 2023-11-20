package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.OperationInput;

public class CalculadoraPrecoMedioVenda extends Calculadora {
    @Override
    public double calcular(OperationInput operationInput) {

        double qtdAcoesXValorVenda = getQtdAcoesXValor(operationInput);
        double imposto = 0;

//        mediaPonderadaAtual;
        double valorPonderado = operationInput.getQuantity().intValue() * lucroPrejuizo.getMediaPonderadaAtual();
        double lucroAjuste = 0;

        //1 calcular lucro x prejuizo
        double lucro = qtdAcoesXValorVenda - valorPonderado;
        if (lucro < 0) { //prejuizo, aumentando o prejuizo
//            prejuizoPassado += lucro;
            lucroPrejuizo.atualizarPrejuizoPassado(
                    lucroPrejuizo.getPrejuizoPassado()+lucro
            );

        } else { //lucro

            //se o valor que teve de lucro, consigo zerar o prejuizo passado
//            lucroAjuste = lucro + prejuizoPassado;
            lucroAjuste = lucro + lucroPrejuizo.getPrejuizoPassado();

            //2 prejuizo anterior
            if (lucroPrejuizo.getPrejuizoPassado() < 0) {
                if (lucroAjuste >= 0) { //posso fazer a conta direto, porque o meu lucro atual é maior ou igual ao prejuizo acumulado
//                    prejuizoPassado = 0; //como é maior, posso zerar o prejuizo passado
                    lucroPrejuizo.atualizarPrejuizoPassado(0);
                } else {
                    //quando tenho lucro, mas não é suficiente para zerar o prejuizoPassado
                    //so ajusto o prejuizoPassado
//                    prejuizoPassado -= lucroAjuste;
                    lucroPrejuizo.atualizarPrejuizoPassado(
                            lucroPrejuizo.getPrejuizoPassado()-lucroAjuste
                    );
                    lucroAjuste = 0;
                }
            }
        }

        if (qtdAcoesXValorVenda <= 20000) {
            return imposto;
        }

        //3 calculo do imposto da operacao
        imposto = lucroAjuste * 0.2;
        return imposto;


        return 0;
    }
}
