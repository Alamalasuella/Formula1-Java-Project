package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che genera una lista di nomi per i Bot tramite la lettura di un File .txt.
 *
 * Il File deve contenere stringhe rappresentanti nomi per i Bot.
 *
 * @author Davide Cabitza
 *
 */

public class LeggiBot {

    private List<String> names;
    private String fileName;

    /**
     * Legge i nomi dei Bot da un File passato.
     *
     * @param  file             il File da leggere
     * @throws IOException      se scaturiscono errori ala lettura del File
     */
    public LeggiBot(String file) throws NullPointerException, IOException {
        if (file == null) throw new NullPointerException("File Null non ammesso.");
        this.fileName = file;
        loadNamesFromFile(file);
    }


    /**
     * Legge i nomi dei Bot da un File.
     *
     * @param  file             il File da leggere
     * @throws IOException      se scaturiscono errori ala lettura del File
     */
    private void loadNamesFromFile(String file) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(file);
        if (is == null) {
            throw new IOException("File " + file + " non trovato.");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            names = new ArrayList<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                names.add(linea.trim());
            }
        }
    }



    /**
     * Metodi Getter e Setter per la classe LeggiBot.
     *
     */

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getFileName(){
        return this.fileName;
    }


}