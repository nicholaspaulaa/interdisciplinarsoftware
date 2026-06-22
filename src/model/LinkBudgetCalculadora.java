package model;

public class LinkBudgetCalculadora {
    private double calcularPerdaTotal(RedePassiva rede) {
        return (rede.getAtenuacaoFibra() * rede.getDistanciaKm()) + rede.getPerdasPontuais();
    }
}