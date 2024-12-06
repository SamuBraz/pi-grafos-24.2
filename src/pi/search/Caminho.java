package pi.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caminho {
    private Map<String, String> caminho;

    public Caminho () {
        this.caminho = new HashMap<>();
    }

    public void ligarVertices(String anterior, String proximo) {
        this.caminho.put(anterior, proximo);
    }

    public List<String> gerarCaminho(String origem, String destino) {
        List<String> resultado = new ArrayList<String>();
        String no = destino;

        while(no != origem && this.caminho.containsKey(no)) {
            resultado.add(no);
            no = this.caminho.get(no);
        }
        resultado.add(no);

        //inverter a ordem dos elementos da lista
        Collections.reverse(resultado);
        return resultado;



    }
}