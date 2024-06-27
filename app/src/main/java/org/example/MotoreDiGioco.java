package org.example;

/**
 * Interfaccia per gestire il Motore di Gioco.
 *
 * @author Cabitza Davide
 *
 */
public interface MotoreDiGioco {


    /**
     * Metodo che crea i Players che partecipano alla partita.
     */
    public void creaPlayers();



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
     *
     * @return
     */
    public boolean playerState();



    /**
     * Metodo che verifica lo stato della partita.
     */
    public boolean matchState(Bot giocatore);


    /**
     * Metodo che aggiorna la Mappa di gioco con le Coordinate dei Players.
     */
    public void aggiornaMappa();



    /**
     * Metodo che reimposta il valore delle Celle della Mappa di gioco.
     */
    public void resetCelle(Bot giocatore);



    /**
     * Metodo che inizia una partita.
     */
    public void startPartita();



}
