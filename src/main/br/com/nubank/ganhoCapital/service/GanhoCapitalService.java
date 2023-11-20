package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.calculadora.Calculadora;
import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioCompra;
import br.com.nubank.ganhoCapital.service.calculadora.CalculadoraPrecoMedioVenda;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

import java.util.List;

public class GanhoCapitalService extends GanhoCapital {

    List<Calculadora> calculadoras = List.of(new CalculadoraPrecoMedioCompra(), new CalculadoraPrecoMedioVenda());

    @Override
    public void processarLucroPrejuizo(List<List<OperationInput>> allListOperations) {
        allListOperations.stream().forEach(listOperation -> {
            listOperation.stream().forEach(operationInput -> {
                double imposto = 0;
                Calculadora calculadora = calculadoras.stream().filter(c ->
                                c.getOperation().equals(operationInput.getOperation()))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);
                imposto = calculadora.calcular(lucroPrejuizo, operationInput);
                System.out.println("imposto = " + imposto);
            });
        });
    }
}
