package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;

public abstract class GanhoCapital implements CalcularLucroPrejuizo{

    protected int quantidadeAcoesAtual;
    protected double mediaPonderadaAtual;
    protected double prejuizoPassado;

}
