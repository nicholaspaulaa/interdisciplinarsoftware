package model;

public class LinkBudgetCalculadora {
    private double calcularPerdaTotal(RedePassiva rede) {
        return (rede.getAtenuacaoFibra() * rede.getDistanciaKm()) + rede.getPerdasPontuais();
    }
    private double calcularPrx(Equipamento eq, RedePassiva rede) {
        return eq.getPtx() - calcularPerdaTotal(rede) - eq.getMargem();
    }
    private double calcularPtx(Equipamento eq, RedePassiva rede) {
        return eq.getPrx() + calcularPerdaTotal(rede) + eq.getMargem();
    }
}