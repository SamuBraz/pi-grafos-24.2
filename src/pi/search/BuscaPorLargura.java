package pi.search;

import pi.core.Grafo;
import pi.core.Vertice;

import java.util.*;

public class BuscaPorLargura {
    private static BuscaPorLargura instance;

    private BuscaPorLargura() {}

    public static BuscaPorLargura getInstance() {
        if (instance == null) {
            instance = new BuscaPorLargura();
        }
        return instance;
    }


    public void buscarGenerosEArtistas(Grafo grafo, String artistaInicial) {
        // Fila para gerenciar os nós a serem explorados
        Queue<String> fila = new LinkedList<>();
        // Conjunto para registrar os nós visitados
        LinkedHashSet<String> verticesVisitados = new LinkedHashSet<>();


        Set<String> generosArtistaOriginal = new HashSet<>();


        //Hash mapa para artistas encontrados, nele fica gravado o nome do artista e a quantidade de generos em comum com o artista original.
        Map<String, Integer> artistas_encontrados = new HashMap<>();

        Caminho caminho = new Caminho();

        // Enfileira o artista inicial
        fila.add(artistaInicial);
        verticesVisitados.add(artistaInicial);

        System.out.println("Explorando a partir do artista: " + artistaInicial);

        while (!fila.isEmpty()) {
            String noAtual = fila.poll(); // Remove o próximo nó da fila

            // Recupera os nós adjacentes (gêneros ou artistas relacionados)
            List<Vertice> adjacencias = grafo.getGrafoAdjacencias(noAtual);

            for (Vertice adjacente : adjacencias) {
                String rotulo = adjacente.getRotulo();

                // Se ainda não foi visitado
                if (!verticesVisitados.contains(rotulo)) {
                    verticesVisitados.add(rotulo);
                    fila.add(rotulo);
                    caminho.ligarVertices(rotulo, noAtual);// Enfileira para explorar depois

                    // Verifica se o nó atual é um gênero ou artista
                    if (adjacente.isArtistas()) {
                        if(!rotulo.equals(artistaInicial)) {
                            Set<String> artistaAtualGeneros = new HashSet<>();
                            List<Vertice> adjacenciasArtista = grafo.getGrafoAdjacencias(rotulo);
                            for(Vertice generoAtual : adjacenciasArtista){
                                 String generoNome = generoAtual.getRotulo();
                                 artistaAtualGeneros.add(generoNome);
                            }
                            artistaAtualGeneros.retainAll(generosArtistaOriginal);
                            artistas_encontrados.put(rotulo, artistaAtualGeneros.size());
                        }
                    } else {
                        if(artistas_encontrados.isEmpty()){
                            generosArtistaOriginal.add(rotulo);
                        } else{
                            fila.clear();//criterio de parada, já explorou todos os generos e o artistas relacionados ao genero
                        }
                    }
                }
            }
        }
        for(String genero : generosArtistaOriginal){
            System.out.println("Genero relacionado ao artista " + artistaInicial + ": " + genero);
        }


        int maiorAparicao = 0;
        String aritstaMaisAparicao = "";
        for (Map.Entry<String, Integer> entry : artistas_encontrados.entrySet()) {
            System.out.println("Artista " + entry.getKey() + " possui " + entry.getValue() + " generos em comum");
            if (entry.getValue() > maiorAparicao){
                maiorAparicao = entry.getValue();
                aritstaMaisAparicao = entry.getKey();
            }
        }


        System.out.println(">> Artista que tem mais similaridade com '" + artistaInicial + "' é  '" + aritstaMaisAparicao + "' << ");

        List<String> caminho_artista  = caminho.gerarCaminho(artistaInicial,aritstaMaisAparicao );

        for (String passo : caminho_artista) {
            System.out.println("[BFS]: >> " + passo + " ");
        }

    }
}