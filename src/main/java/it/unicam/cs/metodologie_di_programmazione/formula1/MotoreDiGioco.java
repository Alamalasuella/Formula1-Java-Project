package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.io.IOException;

/**
 * Interfaccia per gestire il Motore di Gioco.
 *
 * @author Davide Cabitza
 *
 */
public interface MotoreDiGioco {


    /**
     * Metodo che crea i Players che partecipano alla partita.
     */
    public void creaPlayers() throws IOException;



    /**
     * Metodo che calcola le mosse eseguibili dal Player dato.
     */
    public void mossePossibili(Bot giocatore);



    /**
     * Metodo che verifica se il Player dato è squalificati o meno.
     */
    public void checkPlayer(Bot giocatore);



    /**
     * Metodo che verifica lo stato dei Players.

     */
    public void playerState();



    /**
     * Metodo che verifica lo stato della partita.
     */
    public boolean matchState(Bot giocatore);



    /**
     * Metodo che aggiorna la Mappa di gioco con le Coordinate dei Players.
     */
    public void aggiornaMappa();



    /**
     * Metodo che ripristina il valore originario delle Celle della Mappa di gioco.
     */
    public void resetCelle() throws IOException;



    /**
     * Metodo che inizia una partita.
     */
    public void startPartita() throws IOException;




}
