package model;

public class RedePassiva {
    private Double distanciaKm;
    private Double atenuacaoFibra;
    private Double perdaSplitters;
    private Double perdaConectores;
    private Double perdaFusoes;

    public RedePassiva(Double dist, Double aten, Double splitters, Double conect, Double fusoes) {
        this.distanciaKm = dist;
        this.atenuacaoFibra = aten;
        this.perdaSplitters = splitters;
        this.perdaConectores = conect;
        this.perdaFusoes = fusoes;
    }

    public Double getPerdasPontuais() {
        return valorOuZero(this.perdaSplitters) + valorOuZero(this.perdaConectores) + valorOuZero(this.perdaFusoes);
    }

    private Double valorOuZero(Double valor) {
        return valor != null ? valor : 0.0;
    }

    public Double getDistanciaKm() { return distanciaKm; }
    public Double getAtenuacaoFibra() { return atenuacaoFibra; }
    public Double getPerdaSplitters() { return perdaSplitters; }
    public Double getPerdaConectores() { return perdaConectores; }
    public Double getPerdaFusoes() { return perdaFusoes; }
}