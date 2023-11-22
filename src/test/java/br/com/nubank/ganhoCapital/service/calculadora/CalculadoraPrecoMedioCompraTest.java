package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraPrecoMedioCompraTest {

    private Calculadora underTest;

    private MediaPonderadaLucroPrejuizo lucroPrejuizo;

    private OperationInput operationInput;

    private static final DecimalFormat formater = new DecimalFormat("0.00");

    @BeforeEach
    void setUp() {

        this.underTest = new CalculadoraPrecoMedioCompra();

        this.lucroPrejuizo = new MediaPonderadaLucroPrejuizo();        
        this.lucroPrejuizo.atualizarQuantidadeAcoesAtual(10);
        this.lucroPrejuizo.atualizarMediaPonderadaAtual(10);
        this.lucroPrejuizo.atualizarPrejuizoPassado(10);

        this.operationInput = new OperationInput("buy", BigDecimal.ONE, 1);
    }

    @Test
    void deve_calcular_lucro_prejuizo_na_venda_imposto_deve_ser_zero() {
        double calculo = this.underTest.calcular(this.lucroPrejuizo, this.operationInput);
        formater.format(this.lucroPrejuizo.getMediaPonderadaAtual());
        assertEquals(0, calculo);
        assertEquals(11,this.lucroPrejuizo.getQuantidadeAcoesAtual());
        assertEquals(9.18, this.lucroPrejuizo.getMediaPonderadaAtual(),0.01);
        assertEquals(10,this.lucroPrejuizo.getPrejuizoPassado());
    }
}