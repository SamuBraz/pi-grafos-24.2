package pi.core;

public class Vertice {

    private String rotulo; //conteudo do vertice
    private int grau = 0;
    private boolean isArtistas;

    public boolean isArtistas() {
        return isArtistas;
    }

    public void setArtistas(boolean artistas) {
        isArtistas = artistas;
    }

    public Vertice(String rotulo, Boolean isArtistas){
        this.rotulo = rotulo;
        this.isArtistas = isArtistas;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public void adiocioGrau(){
        this.grau++;
    }
}