package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Interfaccia per gestire le mosse di un Player generico durante la partita.
 *
 * @author Davide Cabitza
 *
 */
public interface movimentoPlayer {


    /**
     * Metodo che sposta la Macchina del Player
     */
    public void move();



    /**
     * Metodo che esegue un turno di partita del del Player
     */
    public void turno();


}
