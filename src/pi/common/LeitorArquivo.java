package pi.common;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class LeitorArquivo {
    String inputFilePath;
    ArrayList<String> linhas_csv =  new ArrayList<>();

    public LeitorArquivo(String inputFilePath){
        this.inputFilePath = inputFilePath;
    }

    public ArrayList<String> read() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(this.inputFilePath));
            String line;

            int i = 0;

            while((line = reader.readLine()) != null) {
                if(i == 0){
                    i++;
                    continue;
                }
                linhas_csv.add(line);
            }

        }catch (IOException e) {
            System.err.println(e);
        }

        return linhas_csv;
    }
}