package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe che permette la costruzione di una griglia di Celle tramite
 * lettura di File, rappresentante i valori della Mappa di gioco.
 *
 * @author Cabitza Davide
 *
 */

public class LeggiMappa {

    private Celle[][] griglia;

    /**
     * Costruisce una griglia
     *
     * @param  file                    il nome del File da leggere
     * @throws IOException             se scaturiscono errori ala lettura del File
     * @throws NullPointerException    se il File passato è <@code> null
     */
    public LeggiMappa(String file) throws IOException, NullPointerException {
        if (file == null) throw new NullPointerException("File Null non ammesso.");
        loadTrackFromFile(file);
    }


    /**
     * Compone una griglia basandosi sui valori del File passato.
     *
     * @param  file             il File da leggere
     * @throws IOException      se scaturiscono errori ala lettura del File
     */
    private void loadTrackFromFile(String file) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(file);
        if (is == null) {
            throw new IOException("File " + file + " non trovato.");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            int righe = 0;
            int colonne = 0;
            while ((linea = br.readLine()) != null) {
                righe++;
                String[] valori = linea.split(" ");
                colonne = valori.length;
            }
            griglia = new Celle[righe][colonne];
            br.close();
            InputStream is2 = getClass().getClassLoader().getResourceAsStream(file);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            int riga = 0;
            while ((linea = br2.readLine()) != null) {
                String[] valori = linea.split(" ");
                for (int colonna = 0; colonna < valori.length; colonna++) {
                    griglia[riga][colonna] = Celle.getCellaFromValue(Integer.parseInt(valori[colonna]));
                }
                riga++;
            }
            br2.close();
        }
    }



    /**
     * Metodo Getter per la classe LeggiMappa
     *
     */

    public Celle[][] getGriglia() {
        return griglia;
    }



}