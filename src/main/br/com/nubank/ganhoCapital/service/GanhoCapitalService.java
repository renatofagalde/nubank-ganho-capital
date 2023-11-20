package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioCompra;
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

                    imposto = new CalculadoraPrecoMedioVenda().calcular(lucroPrejuizo,operationInput);


                    //imposto = imposto(operationInput);
//                    quantidadeAcoesAtual -= operationInput.getQuantity();
                    lucroPrejuizo.atualizarQuantidadeAcoesAtual(
                            lucroPrejuizo.getQuantidadeAcoesAtual() - operationInput.getQuantity()
                    );


                } else { //comprar acoes

//                    mediaPonderadaAtual = mediaPonderada(operationInput);
                    double mediaPonderada = new CalculadoraPrecoMedioCompra().calcular(lucroPrejuizo, operationInput);
                    lucroPrejuizo.atualizarMediaPonderadaAtual(mediaPonderada);

//                    quantidadeAcoesAtual += operationInput.getQuantity();
                    lucroPrejuizo.atualizarQuantidadeAcoesAtual(
                            lucroPrejuizo.getQuantidadeAcoesAtual() + operationInput.getQuantity()
                    );

                }
                System.out.println("imposto = " + imposto);
            });
        });

    }
}
