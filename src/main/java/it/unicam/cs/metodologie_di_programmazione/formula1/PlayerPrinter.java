package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Interfaccia per la Stampa delle informazioni sui Players in gara.
 *
 * @author Davide Cabitza
 *
 */
public interface PlayerPrinter {


    /**
     * Metodo che annuncia i Players che partecipano alla partita.
     */
    public void stampaStart();



    /**
    * Metodo che aggiorna la visibilità dei Players nella Mappa di gioco.
    */
   public void stampaPlayers();



    /**
     * Metodo che annuncia l'esito della gara.
     */
    public void stampaEsito();




}
