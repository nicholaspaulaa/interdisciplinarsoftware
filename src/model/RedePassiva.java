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
        this.perdaSplitters = splitters != null ? splitters : 0.0;
        this.perdaConectores = conect != null ? conect : 0.0;
        this.perdaFusoes = fusoes != null ? fusoes : 0.0;
    }
}