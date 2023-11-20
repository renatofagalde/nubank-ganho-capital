package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioVenda;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

import java.util.List;

public class GanhoCapitalService extends GanhoCapital {
    @Override
    public void processarLucroPrejuizo(List<List<OperationInput>> allListOperations) {
        allListOperations.stream().forEach(listOperation -> {
            listOperation.stream().forEach(operationInput -> {
                double imposto = 0;
                //todo escrever o pq não tem tratamento de erro e o pq não preciso checar o outro
                if (operationInput.getOperation().equals("sell")) {

                    imposto = new CalculadoraPrecoMedioVenda().calcular(operationInput);


                    imposto = imposto(operationInput);
                    quantidadeAcoesAtual -= operationInput.getQuantity();


                } else { //comprar acoes
                    mediaPonderadaAtual = mediaPonderada(operationInput);
                    quantidadeAcoesAtual += operationInput.getQuantity();

                }
                System.out.println("imposto = " + imposto);
            });
        });

    }
}
