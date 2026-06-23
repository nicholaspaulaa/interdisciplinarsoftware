package controller;

import model.*;

public class CalculadoraController {
    private LinkBudgetCalculadora calculadora;
    private ValidadorG984 validador;

    public CalculadoraController() {
        this.calculadora = new LinkBudgetCalculadora();
        this.validador = new ValidadorG984();
    }

    public String processarCalculo(Double ptx, Double prx, Double margem, Double dist,
                                   Double atenuacao, Double splitters, Double conect, Double fusoes) {
        return "Recebido";
    }
}
