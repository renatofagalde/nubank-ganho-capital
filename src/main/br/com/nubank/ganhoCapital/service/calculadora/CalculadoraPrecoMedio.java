package br.com.nubank.ganhoCapital.service.calculadora;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;
import br.com.nubank.ganhoCapital.service.model.OperationInput;

public interface CalculadoraPrecoMedio {
    double calcular(MediaPonderadaLucroPrejuizo lucroPrejuizo, OperationInput operationInput);
}
