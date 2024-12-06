package pi.core;

import pi.core.Grafo;
import pi.common.LeitorArquivo;
import pi.core.MatrizAdjacencia;
import pi.search.BuscaPorLargura;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(10);


        System.out.println("Lendo Dataset;");
        LeitorArquivo leitorArquivo1 = new LeitorArquivo("dataset_100.csv");

        long startTime = System.nanoTime();
        List<String> linhas = leitorArquivo1.read();

        System.out.println("Conectando grafos;");
        grafo.conecta_vertice_artista(linhas);


        BuscaPorLargura buscaPorLargura = BuscaPorLargura.getInstance();


        buscaPorLargura.buscarGenerosEArtistas(grafo, "50 Cent");
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        System.out.println("Tempo de execução: " + totalTime + " milisegundos");

    }
}