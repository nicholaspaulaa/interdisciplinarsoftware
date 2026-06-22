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

        if (nullCount > 1) throw new ValidacaoException("ERRO: Deixe apenas UM campo principal em branco.");
        if (nullCount == 0) throw new ValidacaoException("ERRO: Apague um campo para calcular.");
        if (rede.getAtenuacaoFibra() == null) throw new ValidacaoException("ERRO: Atenuação é obrigatória.");

        if (eq.getPrx() == null) return String.format("Sensibilidade Prx: %.2f dBm", calcularPrx(eq, rede));
        else if (eq.getPtx() == null) return String.format("Potência Ptx: %.2f dBm", calcularPtx(eq, rede));
        else return String.format("Distância Máxima: %.2f km", calcularDistancia(eq, rede));
    }
}