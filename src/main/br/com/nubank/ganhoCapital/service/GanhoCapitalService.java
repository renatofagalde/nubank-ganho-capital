package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.calculadora.Calculadora;
import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioCompra;
import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioVenda;
import br.com.nubank.ganhoCapital.service.model.OperationInput;
import br.com.nubank.ganhoCapital.service.model.OperationOutput;
import br.com.nubank.ganhoCapital.service.model.OperationsOutput;

import java.util.List;
import java.util.function.BiConsumer;

public class GanhoCapitalService extends GanhoCapital {

    List<Calculadora> calculadoras = List.of(new CalculadoraPrecoMedioCompra(), new CalculadoraPrecoMedioVenda());

    @Override
    public void processarLucroPrejuizo(List<List<OperationInput>> allListOperations) {

        allListOperations.forEach(listOperation -> {
            OperationsOutput operations = new OperationsOutput();
            listOperation.forEach(operationInput -> iterarOperacoes.accept(operationInput, operations));
            System.out.println(operations);
        });
    }

    BiConsumer<OperationInput, OperationsOutput> iterarOperacoes = new BiConsumer<OperationInput, OperationsOutput>() {
        @Override
        public void accept(OperationInput operationInput, OperationsOutput operations) {
            double imposto = 0;
            Calculadora calculadora = calculadoras
                    .stream()
                    .filter(c ->
                            c.getOperation().equals(operationInput.getOperation()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            imposto = calculadora.calcular(lucroPrejuizo, operationInput);
            operations.getOperations().add(new OperationOutput(imposto));
        }
    };
}

