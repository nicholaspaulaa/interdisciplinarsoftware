package model;

public class TesteMatematico {
    public static void main(String[] args) throws Exception {
        LinkBudgetCalculadora calc = new LinkBudgetCalculadora();

        imprimir(calc, "Distância", new Equipamento(3.0, -28.0, 3.0),
                new RedePassiva(null, 0.25, 10.5, 1.0, 0.5));
        imprimir(calc, "Prx", new Equipamento(3.0, null, 3.0),
                new RedePassiva(20.0, 0.25, 10.5, 1.0, 0.5));
        imprimir(calc, "Ptx", new Equipamento(null, -28.0, 3.0),
                new RedePassiva(20.0, 0.25, 10.5, 1.0, 0.5));
        imprimir(calc, "Atenuação", new Equipamento(3.0, -17.0, 3.0),
                new RedePassiva(20.0, null, 10.5, 1.0, 0.5));
        imprimir(calc, "Margem", new Equipamento(3.0, -17.0, null),
                new RedePassiva(20.0, 0.25, 10.5, 1.0, 0.5));
        imprimir(calc, "Splitters", new Equipamento(3.0, -17.0, 3.0),
                new RedePassiva(20.0, 0.25, null, 1.0, 0.5));
    }

    private static void imprimir(LinkBudgetCalculadora calc, String caso, Equipamento eq, RedePassiva rede)
            throws ValidacaoException {
        System.out.println(caso + ": " + calc.calcularVariavelFaltante(eq, rede));
    }
}