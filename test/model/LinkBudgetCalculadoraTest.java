package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LinkBudgetCalculadoraTest {
    private final LinkBudgetCalculadora calculadora = new LinkBudgetCalculadora();

    @Test
    void deveCalcularPrxComCenarioRealFtth() throws ValidacaoException {
        Equipamento equipamento = new Equipamento(3.0, null, 3.0);
        RedePassiva rede = new RedePassiva(3.2, 0.35, 17.5, 1.2, 0.2);

        String resultado = calculadora.calcularVariavelFaltante(equipamento, rede);

        assertEquals("Sensibilidade Prx: -20,02 dBm", resultado);
    }

    @Test
    void deveCalcularMargemComOrcamentoGponBPlus() throws ValidacaoException {
        Equipamento equipamento = new Equipamento(1.5, -28.0, null);
        RedePassiva rede = new RedePassiva(3.2, 0.35, 17.5, 1.2, 0.2);

        String resultado = calculadora.calcularVariavelFaltante(equipamento, rede);

        assertEquals("Margem de Segurança: 9,48 dB", resultado);
    }

    @Test
    void deveCalcularDistanciaComSplitterUmParaSessentaEQuatro() throws ValidacaoException {
        double perdaSplitterIdeal = 10.0 * Math.log10(64.0);
        Equipamento equipamento = new Equipamento(3.0, -28.0, 3.0);
        RedePassiva rede = new RedePassiva(null, 0.25, perdaSplitterIdeal, 1.0, 0.4);

        String resultado = calculadora.calcularVariavelFaltante(equipamento, rede);

        assertEquals("Distância Máxima: 34,15 km", resultado);
    }

    @Test
    void deveCalcularAtenuacaoDaFibra() throws ValidacaoException {
        Equipamento equipamento = new Equipamento(3.0, -20.02, 3.0);
        RedePassiva rede = new RedePassiva(3.2, null, 17.5, 1.2, 0.2);

        String resultado = calculadora.calcularVariavelFaltante(equipamento, rede);

        assertEquals("Atenuação da Fibra: 0,35 dB/km", resultado);
    }

    @Test
    void deveCalcularPerdaDeSplitters() throws ValidacaoException {
        Equipamento equipamento = new Equipamento(3.0, -17.0, 3.0);
        RedePassiva rede = new RedePassiva(20.0, 0.25, null, 1.0, 0.5);

        String resultado = calculadora.calcularVariavelFaltante(equipamento, rede);

        assertEquals("Perda Splitters: 10,50 dB", resultado);
    }

    @Test
    void deveExigirExatamenteUmCampoFaltante() {
        Equipamento equipamento = new Equipamento(3.0, null, 3.0);
        RedePassiva rede = new RedePassiva(null, 0.25, 10.5, 1.0, 0.5);

        ValidacaoException erro = assertThrows(
                ValidacaoException.class,
                () -> calculadora.calcularVariavelFaltante(equipamento, rede)
        );

        assertEquals("ERRO: Deixe apenas UM campo da fórmula em branco.", erro.getMessage());
    }

    @Test
    void deveRejeitarResultadoFisicamenteInvalido() {
        Equipamento equipamento = new Equipamento(3.0, -28.0, 3.0);
        RedePassiva rede = new RedePassiva(null, 0.25, 40.0, 1.0, 0.5);

        ValidacaoException erro = assertThrows(
                ValidacaoException.class,
                () -> calculadora.calcularVariavelFaltante(equipamento, rede)
        );

        assertEquals("ERRO: Os dados informados geram um resultado fisicamente inválido.", erro.getMessage());
    }
}
