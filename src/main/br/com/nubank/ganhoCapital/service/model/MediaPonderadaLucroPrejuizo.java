package br.com.nubank.ganhoCapital.service.model;


public class MediaPonderadaLucroPrejuizo {

    private int quantidadeAcoesAtual;
    private double mediaPonderadaAtual;
    private double prejuizoPassado;

    public MediaPonderadaLucroPrejuizo() {
    }

    public MediaPonderadaLucroPrejuizo(int quantidadeAcoesAtual, double mediaPonderadaAtual, double prejuizoPassado) {
        this.quantidadeAcoesAtual = quantidadeAcoesAtual;
        this.mediaPonderadaAtual = mediaPonderadaAtual;
        this.prejuizoPassado = prejuizoPassado;
    }

    public int getQuantidadeAcoesAtual() {
        return quantidadeAcoesAtual;
    }

    public double getMediaPonderadaAtual() {
        return mediaPonderadaAtual;
    }

    public double getPrejuizoPassado() {
        return prejuizoPassado;
    }

    public void atualizarQuantidadeAcoesAtual(int quantidadeAcoesAtual){
        this.quantidadeAcoesAtual=quantidadeAcoesAtual;
    }

    public void atualizarMediaPonderadaAtual(double mediaPonderadaAtual){
        this.mediaPonderadaAtual=mediaPonderadaAtual;
    }

    public void atualizarPrejuizoPassado(double prejuizoPassado){
        this.prejuizoPassado=prejuizoPassado;
    }

}
