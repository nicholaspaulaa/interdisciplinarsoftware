package model;

public class Equipamento {
    private Double ptx;
    private Double prx;
    private Double margem;

    public Equipamento(Double ptx, Double prx, Double margem) {
        this.ptx = ptx;
        this.prx = prx;
        this.margem = margem;
    }

    public Double getPtx() { return ptx; }
    public Double getPrx() { return prx; }
    public Double getMargem() { return margem; }
}