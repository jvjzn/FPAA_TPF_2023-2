package codigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistribuicaoRotas {

    public static void main(String[] args) {

        int qntRotas = 6;
        int qntConjuntos = 3;
        double dispersao = 0.5;
        int numCaminhoes = 3;

        List<int[]> conjuntosDeTeste  = GeradorDeProblemas.geracaoDeRotas(qntRotas, qntConjuntos, dispersao);

        for (int i = 0; i < conjuntosDeTeste.size(); i++) {
           
            long inicio = System.nanoTime();

            List<Caminhao> distribuicao = distribuirRotas(conjuntosDeTeste.get(i), numCaminhoes);
                       
            long fim = System.nanoTime();
           
            long tempoTotal = (fim - inicio);
           
            imprimirResultado(distribuicao, tempoTotal);
        }
    }


    public static List<Caminhao> distribuirRotas(int[] conjuntoRotas, int caminhoes) {
               
        Arrays.sort(conjuntoRotas);
              
        inverterArray(conjuntoRotas);

        List<Caminhao> caminhoesList = new ArrayList<>();
        for (int i = 0; i < caminhoes; i++) {
            caminhoesList.add(new Caminhao(i + 1));
        }

        int meio = conjuntoRotas.length / 2;
        int[] subconjunto1 = Arrays.copyOfRange(conjuntoRotas, 0, meio);
        int[] subconjunto2 = Arrays.copyOfRange(conjuntoRotas, meio, conjuntoRotas.length);

        for (int i = 0; i < subconjunto1.length; i++) {
            int indiceCaminhao = i % caminhoes;
            caminhoesList.get(indiceCaminhao).addRota(subconjunto1[i]);
        }

        for (int i = 0; i < subconjunto2.length; i++) {
            int indiceCaminhao = i % caminhoes;
            caminhoesList.get(indiceCaminhao).addRota(subconjunto2[i]);
        }

        return caminhoesList;
    }

    // Método para inverter a ordem do array
    private static void inverterArray(int[] array) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio < fim) {
            int temp = array[inicio];
            array[inicio] = array[fim];
            array[fim] = temp;

            inicio++;
            fim--;
        }
    }

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