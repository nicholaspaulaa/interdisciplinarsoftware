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
    private double calcularDistancia(Equipamento eq, RedePassiva rede) {
        double potDisponivel = eq.getPtx() - eq.getPrx() - eq.getMargem() - rede.getPerdasPontuais();
        return potDisponivel / rede.getAtenuacaoFibra();
    }

    public String calcularVariavelFaltante(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        int nullCount = 0;
        if (eq.getPrx() == null) nullCount++;
        if (eq.getPtx() == null) nullCount++;
        if (rede.getDistanciaKm() == null) nullCount++;
        return "Método em construção";
    }
}