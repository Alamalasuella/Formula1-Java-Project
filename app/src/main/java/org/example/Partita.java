/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;


import java.io.IOException;

/**
 * Classe che rappresenta una partita del gioco Formula1.
 *
 * Per assistere ad una partita tra Giocatori Bot è
 * sufficente avviare la classe Partita.
 *
 * @author Cabitza Davide
 *
 */


public class Partita {

    public static void main(String[] args) throws IOException {

        LeggiMappa leggiMappa = new LeggiMappa("mappa.txt");
        Mappa mappaDiGioco = new Mappa(leggiMappa);
        Engine motoreDiGioco = new Engine(mappaDiGioco);

        motoreDiGioco.startPartita();


    }


}
