package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraPrecoMedioVendaTest {
    private Calculadora underTest;

    private MediaPonderadaLucroPrejuizo lucroPrejuizo;

    private OperationInput operationInput;

    private static final DecimalFormat formater = new DecimalFormat("0.00");

    @BeforeEach
    void setUp() {
        this.underTest = new CalculadoraPrecoMedioVenda();
    }

    @Test
    void deve_calcular_retornando_imposto_zero() {

        imposto0();

        double calculo = this.underTest.calcular(this.lucroPrejuizo, this.operationInput);
        formater.format(this.lucroPrejuizo.getMediaPonderadaAtual());
        assertEquals(0, calculo);
        assertEquals(10,this.lucroPrejuizo.getQuantidadeAcoesAtual());
        assertEquals(10, this.lucroPrejuizo.getMediaPonderadaAtual(),0.01);
        assertEquals(1,this.lucroPrejuizo.getPrejuizoPassado());
    }

    @Test
    void deve_calcular_retornando_imposto() {

        pagando_imposto();

        double calculo = this.underTest.calcular(this.lucroPrejuizo, this.operationInput);
        formater.format(this.lucroPrejuizo.getMediaPonderadaAtual());
        assertEquals(3822.0, calculo);
        assertEquals(-90,this.lucroPrejuizo.getQuantidadeAcoesAtual());
        assertEquals(10, this.lucroPrejuizo.getMediaPonderadaAtual(),0.01);
        assertEquals(10,this.lucroPrejuizo.getPrejuizoPassado());
    }

    @Test
    void deve_calcular_tendo_lucro_zerando_prejuizo_passado() {

        tendo_lucro_zerando_prejuizo_total();

        double calculo = this.underTest.calcular(this.lucroPrejuizo, this.operationInput);
        formater.format(this.lucroPrejuizo.getMediaPonderadaAtual());
        assertEquals(3816.0, calculo);
        assertEquals(-90,this.lucroPrejuizo.getQuantidadeAcoesAtual());
        assertEquals(10, this.lucroPrejuizo.getMediaPonderadaAtual(),0.01);
        assertEquals(0,this.lucroPrejuizo.getPrejuizoPassado());
    }

    @Test
    void deve_calcular_tendo_lucro_zerando_prejuizo_parcialmente() {

        tendo_lucro_diminuindo_prejuizo();

        double calculo = this.underTest.calcular(this.lucroPrejuizo, this.operationInput);
        formater.format(this.lucroPrejuizo.getMediaPonderadaAtual());
        assertEquals(0, calculo);
        assertEquals(100,this.lucroPrejuizo.getQuantidadeAcoesAtual());
        assertEquals(10, this.lucroPrejuizo.getMediaPonderadaAtual(),0.01);
        assertEquals(0,this.lucroPrejuizo.getPrejuizoPassado());
    }

    private void imposto0(){
        this.operationInput = new OperationInput("sell", BigDecimal.ONE, 1);

        this.lucroPrejuizo = new MediaPonderadaLucroPrejuizo();
        this.lucroPrejuizo.atualizarQuantidadeAcoesAtual(10);
        this.lucroPrejuizo.atualizarMediaPonderadaAtual(10);
        this.lucroPrejuizo.atualizarPrejuizoPassado(10);

    }
    private void pagando_imposto(){
        this.operationInput = new OperationInput("sell", BigDecimal.valueOf(201), 100);

        this.lucroPrejuizo = new MediaPonderadaLucroPrejuizo();
        this.lucroPrejuizo.atualizarQuantidadeAcoesAtual(10);
        this.lucroPrejuizo.atualizarMediaPonderadaAtual(10);
        this.lucroPrejuizo.atualizarPrejuizoPassado(10);

    }

    private void tendo_lucro_zerando_prejuizo_total(){
        this.operationInput = new OperationInput("sell", BigDecimal.valueOf(201), 100);

        this.lucroPrejuizo = new MediaPonderadaLucroPrejuizo();
        this.lucroPrejuizo.atualizarQuantidadeAcoesAtual(10);
        this.lucroPrejuizo.atualizarMediaPonderadaAtual(10);
        this.lucroPrejuizo.atualizarPrejuizoPassado(-20);

    }

    private void tendo_lucro_diminuindo_prejuizo(){
        this.operationInput = new OperationInput("sell", BigDecimal.valueOf(10), 10);

        this.lucroPrejuizo = new MediaPonderadaLucroPrejuizo();
        this.lucroPrejuizo.atualizarQuantidadeAcoesAtual(100);
        this.lucroPrejuizo.atualizarMediaPonderadaAtual(10);
        this.lucroPrejuizo.atualizarPrejuizoPassado(-20);

    }

}