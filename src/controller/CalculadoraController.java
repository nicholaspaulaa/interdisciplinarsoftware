package controller;

import model.Equipamento;
import model.LinkBudgetCalculadora;
import model.RedePassiva;
import model.ValidadorG984;
import model.ValidacaoException;

public class CalculadoraController {
    private LinkBudgetCalculadora calculadora;
    private ValidadorG984 validador;

    public CalculadoraController() {
        this.calculadora = new LinkBudgetCalculadora();
        this.validador = new ValidadorG984();
    }
}
