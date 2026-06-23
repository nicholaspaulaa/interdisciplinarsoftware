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

    private double calcularDistancia(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double potDisponivel = eq.getPtx() - eq.getPrx() - eq.getMargem() - rede.getPerdasPontuais();
        return validarNaoNegativo(potDisponivel / rede.getAtenuacaoFibra());
    }

    private double calcularAtenuacao(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double perdaPorDistancia = eq.getPtx() - eq.getPrx() - eq.getMargem() - rede.getPerdasPontuais();
        return validarNaoNegativo(perdaPorDistancia / rede.getDistanciaKm());
    }

    private double calcularMargem(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double margem = eq.getPtx() - eq.getPrx() - calcularPerdaTotal(rede);
        return validarNaoNegativo(margem);
    }

    private double calcularPerdaSplitters(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double perda = calcularPerdaPontualFaltante(eq, rede) - valorOuZero(rede.getPerdaConectores()) - valorOuZero(rede.getPerdaFusoes());
        return validarNaoNegativo(perda);
    }

    private double calcularPerdaConectores(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double perda = calcularPerdaPontualFaltante(eq, rede) - valorOuZero(rede.getPerdaSplitters()) - valorOuZero(rede.getPerdaFusoes());
        return validarNaoNegativo(perda);
    }

    private double calcularPerdaFusoes(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        double perda = calcularPerdaPontualFaltante(eq, rede) - valorOuZero(rede.getPerdaSplitters()) - valorOuZero(rede.getPerdaConectores());
        return validarNaoNegativo(perda);
    }

    private double calcularPerdaPontualFaltante(Equipamento eq, RedePassiva rede) {
        return eq.getPtx() - eq.getPrx() - eq.getMargem() - (rede.getAtenuacaoFibra() * rede.getDistanciaKm());
    }

    private double valorOuZero(Double valor) {
        return valor != null ? valor : 0.0;
    }

    private double validarNaoNegativo(double valor) throws ValidacaoException {
        if (Double.isNaN(valor) || Double.isInfinite(valor) || valor < 0) {
            throw new ValidacaoException("ERRO: Os dados informados geram um resultado fisicamente inválido.");
        }
        return valor;
    }

    public String calcularVariavelFaltante(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        int nullCount = 0;
        if (eq.getPtx() == null) nullCount++;
        if (eq.getPrx() == null) nullCount++;
        if (eq.getMargem() == null) nullCount++;
        if (rede.getDistanciaKm() == null) nullCount++;
        if (rede.getAtenuacaoFibra() == null) nullCount++;
        if (rede.getPerdaSplitters() == null) nullCount++;
        if (rede.getPerdaConectores() == null) nullCount++;
        if (rede.getPerdaFusoes() == null) nullCount++;

        if (nullCount > 1) throw new ValidacaoException("ERRO: Deixe apenas UM campo da fórmula em branco.");
        if (nullCount == 0) throw new ValidacaoException("ERRO: Apague um campo para calcular.");

        if (eq.getPtx() == null) return String.format("Potência Ptx: %.2f dBm", calcularPtx(eq, rede));
        if (eq.getPrx() == null) return String.format("Sensibilidade Prx: %.2f dBm", calcularPrx(eq, rede));
        if (eq.getMargem() == null) return String.format("Margem de Segurança: %.2f dB", calcularMargem(eq, rede));
        if (rede.getDistanciaKm() == null) return String.format("Distância Máxima: %.2f km", calcularDistancia(eq, rede));
        if (rede.getAtenuacaoFibra() == null) return String.format("Atenuação da Fibra: %.2f dB/km", calcularAtenuacao(eq, rede));
        if (rede.getPerdaSplitters() == null) return String.format("Perda Splitters: %.2f dB", calcularPerdaSplitters(eq, rede));
        if (rede.getPerdaConectores() == null) return String.format("Perda Conectores: %.2f dB", calcularPerdaConectores(eq, rede));
        return String.format("Perda Fusões: %.2f dB", calcularPerdaFusoes(eq, rede));
    }
}