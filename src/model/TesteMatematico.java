package model;

public class TesteMatematico {
    public static void main(String[] args) throws Exception {
        LinkBudgetCalculadora calc = new LinkBudgetCalculadora();
        // Testa cálculo de distância (passando null na distância)
        Equipamento eq = new Equipamento(3.0, -28.0, 3.0);
        RedePassiva rede = new RedePassiva(null, 0.25, 10.5, 1.0, 0.5);

        String res = calc.calcularVariavelFaltante(eq, rede);
        System.out.println("TESTE UNITÁRIO: " + res);
    }
}