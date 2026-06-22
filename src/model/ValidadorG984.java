package model;

public class ValidadorG984 {
    public void validarParametrosFisicos(RedePassiva rede) throws ValidacaoException {
        if (rede.getAtenuacaoFibra() != null) {
            if (rede.getAtenuacaoFibra() < 0.20 || rede.getAtenuacaoFibra() > 0.45) {
                throw new ValidacaoException("ALERTA: A atenuação da fibra está fora do padrão (0.20 a 0.45 dB/km).");
            }
        }
    }
}