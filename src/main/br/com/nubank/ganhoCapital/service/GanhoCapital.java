package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.model.MediaPonderadaLucroPrejuizo;

public abstract class GanhoCapital implements CalcularLucroPrejuizo{
    protected MediaPonderadaLucroPrejuizo lucroPrejuizo = new MediaPonderadaLucroPrejuizo();
}
