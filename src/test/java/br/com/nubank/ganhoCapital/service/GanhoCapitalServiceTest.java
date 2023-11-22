package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.model.OperationInput;
import br.com.nubank.ganhoCapital.service.model.OperationOutput;
import br.com.nubank.ganhoCapital.service.model.OperationsOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GanhoCapitalServiceTest {

    private GanhoCapital underTest;

    private List<OperationInput> operationsInput;

    @BeforeEach
    void setUp() {
        this.underTest = new GanhoCapitalService();
    }

    @Test
    void deve_retornar_impostos_zerados() {

        this.operationsInput = this.vendas();

        List<OperationsOutput> operationsOutputs = this.underTest.processarLucroPrejuizo(List.of(this.operationsInput));
        operationsOutputs.forEach(operationsOutput -> {
            operationsOutput.getOperations().forEach(operationOutput ->

                    assertEquals(0,operationOutput.getTax()));
        });


    }

    @Test
    void deve_retornar_impostos_com_valores() {

        this.operationsInput = this.compras();

        List<OperationsOutput> operationsOutputs = this.underTest.processarLucroPrejuizo(List.of(this.operationsInput));
        List<OperationOutput> operations = operationsOutputs.get(0).getOperations();

        assertEquals(0,operations.get(0).getTax());
        assertEquals(10000,operations.get(1).getTax());
        assertEquals(0,operations.get(2).getTax());
    }


    private List<OperationInput> vendas(){
        return List.of(new OperationInput("sell", BigDecimal.valueOf(10), 10),
                new OperationInput("sell", BigDecimal.valueOf(15), 10),
                new OperationInput("sell", BigDecimal.valueOf(20), 10));

    }
    private List<OperationInput> compras(){
        return List.of(new OperationInput("buy", BigDecimal.valueOf(10), 10000),
                new OperationInput("sell", BigDecimal.valueOf(20), 5000),
                new OperationInput("sell", BigDecimal.valueOf(5), 5000));

    }



}