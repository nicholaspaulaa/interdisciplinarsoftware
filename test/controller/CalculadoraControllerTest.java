package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculadoraControllerTest {
    private final CalculadoraController controller = new CalculadoraController();

    @Test
    void deveProcessarCalculoValido() {
        String resultado = controller.processarCalculo(
                3.0, null, 3.0,
                3.2, 0.35, 17.5, 1.2, 0.2
        );

        assertEquals("Sensibilidade Prx: -20,02 dBm", resultado);
    }

    @Test
    void deveRetornarAlertaParaAtenuacaoForaDoPadrao() {
        String resultado = controller.processarCalculo(
                3.0, null, 3.0,
                10.0, 0.50, 10.5, 1.0, 0.4
        );

        assertEquals("ALERTA: A atenuação da fibra está fora do padrão (0.20 a 0.45 dB/km).", resultado);
    }

    @Test
    void deveRetornarErroParaDistanciaNegativa() {
        String resultado = controller.processarCalculo(
                3.0, -28.0, 3.0,
                -5.0, 0.25, 10.5, 1.0, 0.4
        );

        assertEquals("ERRO: Distância não pode ser negativa.", resultado);
    }

    @Test
    void deveCalcularSplittersComPerdasOpcionaisVazias() {
        String resultado = controller.processarCalculo(
                3.0, -17.0, 3.0,
                20.0, 0.25, null, 1.0, 0.5
        );

        assertEquals("Perda Splitters: 10,50 dB", resultado);
    }

    @Test
    void deveCalcularDistanciaComPerdasOpcionaisVazias() {
        String resultado = controller.processarCalculo(
                3.0, -28.0, 3.0,
                null, 0.25, null, null, null
        );

        assertEquals("Distância Máxima: 112,00 km", resultado);
    }
}
