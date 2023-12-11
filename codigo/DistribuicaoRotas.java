package codigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistribuicaoRotas {

    // Método principal para distribuir rotas entre caminhões
    public static List<Caminhao> distribuirRotas(List<Integer> rotas, int numCaminhoes) {

        rotas.sort(Collections.reverseOrder());

        // Inicializar lista de caminhões
        List<Caminhao> caminhoes = new ArrayList<>();
        for (int i = 1; i <= numCaminhoes; i++) {
            caminhoes.add(new Caminhao(i));
        }

        // Distribuir as rotas entre os caminhões
        distribuirRecursivo(rotas, caminhoes, 0);

        return caminhoes;
    }

    // Função recursiva para distribuir as rotas entre os caminhões
    private static void distribuirRecursivo(List<Integer> rotas, List<Caminhao> caminhoes, int caminhaoAtual) {

        if (rotas.isEmpty()) {
            // Todas as rotas foram distribuídas
            return;
        }

        // Adicionar rota ao caminhão atual
        caminhoes.get(caminhaoAtual).addRota(rotas.remove(0));

        // Atualizar o índice do próximo caminhão
        caminhaoAtual = (caminhaoAtual + 1) % caminhoes.size();

        // Chamada recursiva para o restante das rotas
        distribuirRecursivo(rotas, caminhoes, caminhaoAtual);
    }

    // Main
    public static void main(String[] args) {

        // Parâmetros para gerar as rotas
        int qntRotas = 6;
        int qntConjuntos = 1;
        double dispersao = 0.5;
        int numCaminhoes = 3;

        //
        List<List<Integer>> conjuntosDeTeste = new ArrayList<>();

        // Converte List<int[]> para List<List<Integer>>
        for (int[] conjunto : GeradorDeProblemas.geracaoDeRotas(qntRotas, qntConjuntos, dispersao)) {
            List<Integer> rota = new ArrayList<>();
            for (int i : conjunto) {
                rota.add(i);
            }
            conjuntosDeTeste.add(rota);
        }

        // Distribuir as rotas
        for (int i = 0; i < conjuntosDeTeste.size(); i++) {

            // Iniciar contagem do tempo
            long inicio = System.nanoTime();

            List<Caminhao> distribuicao = distribuirRotas(conjuntosDeTeste.get(i), numCaminhoes);
            // Imprimir resultado

            // Finalizar contagem do tempo
            long fim = System.nanoTime();

            // Calcular tempo total
            long tempoTotal = (fim - inicio);

            // Imprimir resultado
            imprimirResultado(distribuicao, tempoTotal);
        }

    }

    // Método para imprimir o resultado da distribuição e o tempo de execução
    private static void imprimirResultado(List<Caminhao> caminhoes, long tempoTotal) {
        for (Caminhao c : caminhoes) {
            System.out.println("Soma das rotas do caminhão " + c.getId() + ": " + c.somarKMcaminhao() + "km");
            System.out.print("Rotas do caminhão " + c.getId() + " : ");
            c.imprimirRotas();
            System.out.println(" ");

        }
        System.out.println("Tempo de execução: " + tempoTotal + "ms");
        System.out.println("-------------------------------------------");
    }
}
