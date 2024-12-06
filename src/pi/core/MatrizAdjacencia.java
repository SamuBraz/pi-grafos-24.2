package pi.core;

import java.util.ArrayList;
import java.util.List;

public class MatrizAdjacencia {

    private int[][] matriz;
    private List<Vertice> vertices;
    private int qtdVertices;


    public MatrizAdjacencia(List<Vertice> vertices){
        this.vertices = vertices;

        this.qtdVertices = this.vertices.size();
        this.matriz = new int[this.qtdVertices][this.qtdVertices];

        this.geraMatriz(0);

    }

    public void geraMatriz(int valorInicial){
        for(int i = 0;i <  this.qtdVertices; i++){
            for(int j = 0; j < this.qtdVertices;j++){
                this.matriz[i][j] = valorInicial;
            }
        }
    }



    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void criarArestas(int indece1, int indece2){
        Vertice verticeInicial = this.vertices.get(indece1);
        Vertice verticeFinal   = this.vertices.get(indece2);

        if(verticeInicial == verticeFinal){
            this.matriz[indece1][indece2] = 1;
        } else{
            this.matriz[indece1][indece2] = 1;
            this.matriz[indece2][indece1] = 1;
        }

    }

    public List<Vertice> getAdjacencias(int indiceVertice){

        int linha = indiceVertice;
        List<Vertice> adjacencias = new ArrayList<Vertice>();

        for (int j = 0; j < this.vertices.size(); j++) {
            if(this.matriz[linha][j] == 1) {
                Vertice vertice = this.vertices.get(j);
                adjacencias.add(vertice);
            }
        }

        return adjacencias;
    }
}