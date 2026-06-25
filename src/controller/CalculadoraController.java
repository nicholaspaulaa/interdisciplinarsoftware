package controller;

import model.*;

public class CalculadoraController {
    private LinkBudgetCalculadora calc = new LinkBudgetCalculadora();
    private ValidadorG984 validador = new ValidadorG984();

    public String processarCalculo(Double ptx, Double prx, Double margem, Double dist,
                                   Double aten, Double splitters, Double conect, Double fusoes) {
        try {
            ResolucaoEntrada entrada = ResolucaoEntrada.resolver(
                    ptx, prx, margem, dist, aten, splitters, conect, fusoes
            );
            Equipamento eq = new Equipamento(entrada.ptx, entrada.prx, entrada.margem);
            RedePassiva rede = new RedePassiva(
                    entrada.dist, entrada.aten, entrada.splitters, entrada.conect, entrada.fusoes
            );

            validador.validarParametrosFisicos(eq, rede);
            return calc.calcularVariavelFaltante(eq, rede);
        } catch (ValidacaoException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "ERRO GRAVE: Falha de processamento.";
        }
    }
}
