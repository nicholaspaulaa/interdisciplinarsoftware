package controller;

import model.*;

public class CalculadoraController {
    private LinkBudgetCalculadora calc = new LinkBudgetCalculadora();
    private ValidadorG984 validador = new ValidadorG984();

    public String processarCalculo(Double ptx, Double prx, Double margem, Double dist,
                                   Double aten, Double splitters, Double conect, Double fusoes) {
        try {
            Equipamento eq = new Equipamento(ptx, prx, margem);
            RedePassiva rede = new RedePassiva(dist, aten, splitters, conect, fusoes);

            validador.validarParametrosFisicos(rede);
            return calc.calcularVariavelFaltante(eq, rede);
        } catch (ValidacaoException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "ERRO GRAVE: Falha de processamento.";
        }
    }
}
