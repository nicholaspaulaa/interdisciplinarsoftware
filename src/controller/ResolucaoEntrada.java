package controller;

final class ResolucaoEntrada {
    final Double ptx;
    final Double prx;
    final Double margem;
    final Double dist;
    final Double aten;
    final Double splitters;
    final Double conect;
    final Double fusoes;

    private ResolucaoEntrada(Double[] valores) {
        ptx = valores[0];
        prx = valores[1];
        margem = valores[2];
        dist = valores[3];
        aten = valores[4];
        splitters = valores[5];
        conect = valores[6];
        fusoes = valores[7];
    }

    static ResolucaoEntrada resolver(Double ptx, Double prx, Double margem, Double dist,
                                     Double aten, Double splitters, Double conect, Double fusoes) {
        Double[] valores = { ptx, prx, margem, dist, aten, splitters, conect, fusoes };

        int primeiroVazio = -1;
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] == null) {
                primeiroVazio = i;
                break;
            }
        }

        if (primeiroVazio >= 0) {
            for (int i = 5; i <= 7; i++) {
                if (valores[i] == null && i != primeiroVazio) {
                    valores[i] = 0.0;
                }
            }
        }

        return new ResolucaoEntrada(valores);
    }
}
