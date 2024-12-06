package pi.core;


import java.util.*;

public class Grafo {

    private int numeroVerticesAtual = 0;
    private int numeroMaximoVertices;

    private List<Vertice> vertices = new ArrayList<Vertice>();
    private Map<String, Integer> rotulosEmIndices = new HashMap<String, Integer>();

    private MatrizAdjacencia matrizAdjacencia;

    public Grafo(int numeroMaximoVertices){
        this.numeroMaximoVertices = numeroMaximoVertices;
    }

    public void adicionaVerice(String rotulo, Boolean isArtista ){
        Vertice vertice = new Vertice(rotulo, isArtista);
        this.vertices.add(vertice);
        this.rotulosEmIndices.put(rotulo, this.numeroVerticesAtual);
        this.numeroVerticesAtual++;
    }

    private void criarMatrizAdjacencia() {
        if(this.matrizAdjacencia == null) {
            this.matrizAdjacencia =
                    new MatrizAdjacencia(new ArrayList<Vertice>(this.vertices));
        }
    }

    public void gerarArestas(String rotoluInicial, String rotuloFinal){
        this.criarMatrizAdjacencia();

        this.matrizAdjacencia.criarArestas(this.rotulosEmIndices.get(rotoluInicial), this.rotulosEmIndices.get(rotuloFinal));

    }


    public void conecta_vertice_artista(List<String> linhas){
        for(String linha : linhas){
            String[] dividido = linha.split(";");
            if(Objects.equals(dividido[2], "[]") || Objects.equals(dividido[1], "")){
                continue;
            }


            Integer rotulo_artista = this.rotulosEmIndices.get(dividido[2]);
            if(rotulo_artista == null){
                this.adicionaVerice(dividido[2], true);
            } else{
                System.out.println("ARTISTA JÁ PRESENTE: " + dividido[2]);
            }


            String generos = dividido[1].replace("[", "").replace("]", "").replace("'", "");
            String[] generos_array = generos.split(", ");
            for(String genero : generos_array){
                Integer rotulo = this.rotulosEmIndices.get(genero);
                if(rotulo == null) {
                    this.adicionaVerice(genero, false);
                }
            }
        }

        for(String linha : linhas){
            String[] dividido = linha.split(";");
            if(Objects.equals(dividido[2], "[]") || Objects.equals(dividido[1], "")){
                continue;
            }

            String generos = dividido[1].replace("[", "").replace("]", "").replace("'", "");
            String[] generos_array = generos.split(", ");

            for(String genero : generos_array){
                gerarArestas(genero, dividido[2]);
                gerarArestas(dividido[2], genero);
            }


        }


        System.out.println(this.matrizAdjacencia);

    }

    public List<Vertice> getGrafoAdjacencias(String vertice){
        // preciso fazer a verificacao se o vertice existe
        int indiceVertice = this.rotulosEmIndices.get(vertice);

        return this.matrizAdjacencia.getAdjacencias(indiceVertice);
    }


}