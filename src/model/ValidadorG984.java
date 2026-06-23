package model;

public class ValidadorG984 {
    public void validarParametrosFisicos(Equipamento eq, RedePassiva rede) throws ValidacaoException {
        validarEquipamento(eq);
        validarRede(rede);
    }

    private void validarEquipamento(Equipamento eq) throws ValidacaoException {
        if (eq.getPtx() != null && (eq.getPtx() < -10.0 || eq.getPtx() > 10.0)) {
            throw new ValidacaoException("ALERTA: Potência Ptx fora da faixa esperada (-10 a 10 dBm).");
        }

        if (eq.getPrx() != null && (eq.getPrx() < -35.0 || eq.getPrx() > -8.0)) {
            throw new ValidacaoException("ALERTA: Sensibilidade Prx fora da faixa esperada (-35 a -8 dBm).");
        }

        if (eq.getMargem() != null) {
            if (eq.getMargem() < 0.0) {
                throw new ValidacaoException("ERRO: Margem de segurança não pode ser negativa.");
            }
            if (eq.getMargem() > 10.0) {
                throw new ValidacaoException("ALERTA: Margem de segurança acima do esperado (0 a 10 dB).");
            }
        }
    }

    private void validarRede(RedePassiva rede) throws ValidacaoException {
        if (rede.getDistanciaKm() != null) {
            if (rede.getDistanciaKm() < 0.0) {
                throw new ValidacaoException("ERRO: Distância não pode ser negativa.");
            }
            if (rede.getDistanciaKm() > 60.0) {
                throw new ValidacaoException("ALERTA: Distância acima do alcance lógico esperado para GPON (até 60 km).");
            }
        }

        if (rede.getAtenuacaoFibra() != null) {
            if (rede.getAtenuacaoFibra() <= 0.0) {
                throw new ValidacaoException("ERRO: Atenuação da fibra deve ser maior que zero.");
            }
            if (rede.getAtenuacaoFibra() < 0.20 || rede.getAtenuacaoFibra() > 0.45) {
                throw new ValidacaoException("ALERTA: A atenuação da fibra está fora do padrão (0.20 a 0.45 dB/km).");
            }
        }

        validarPerdaNaoNegativa(rede.getPerdaSplitters(), "splitters");
        validarPerdaNaoNegativa(rede.getPerdaConectores(), "conectores");
        validarPerdaNaoNegativa(rede.getPerdaFusoes(), "fusões");

        if (rede.getPerdasPontuais() > 25.0) {
            throw new ValidacaoException("ALERTA: Perdas pontuais acima de 25 dB indicam excesso de splitters/conexões.");
        }
    }

    private void validarPerdaNaoNegativa(Double perda, String nome) throws ValidacaoException {
        if (perda != null && perda < 0.0) {
            throw new ValidacaoException("ERRO: Perda de " + nome + " não pode ser negativa.");
        }
    }
}