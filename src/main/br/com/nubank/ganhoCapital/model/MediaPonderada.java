package br.com.nubank.ganhoCapital.model;


//nova-media-ponderada = ((quantidade-de-acoes-atual * media-ponderadaatual) + (quantidade-de-acoes * valor-de-compra)) / (quantidade-de-acoes-atual + quantidadede-acoes-compradas) .
public class MediaPonderada {

    private int quantidadeAcoesAtual;
    private double mediaPonderadaAtual;

    private int quantidadeAcoesCompradas;

    public MediaPonderada(int quantidadeAcoesAtual, double mediaPonderadaAtual, int quantidadeAcoesCompradas) {
        this.quantidadeAcoesAtual = quantidadeAcoesAtual;
        this.mediaPonderadaAtual = mediaPonderadaAtual;
        this.quantidadeAcoesCompradas = quantidadeAcoesCompradas;
    }

    public int getQuantidadeAcoesAtual() {
        return quantidadeAcoesAtual;
    }

    public void setQuantidadeAcoesAtual(int quantidadeAcoesAtual) {
        this.quantidadeAcoesAtual = quantidadeAcoesAtual;
    }

    public double getMediaPonderadaAtual() {
        return mediaPonderadaAtual;
    }

    public void setMediaPonderadaAtual(double mediaPonderadaAtual) {
        this.mediaPonderadaAtual = mediaPonderadaAtual;
    }

    public int getQuantidadeAcoesCompradas() {
        return quantidadeAcoesCompradas;
    }

    public void setQuantidadeAcoesCompradas(int quantidadeAcoesCompradas) {
        this.quantidadeAcoesCompradas = quantidadeAcoesCompradas;
    }
}
