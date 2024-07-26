package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Interfaccia per lo spostamento di una Macchina generica all'interno della Mappa di gioco.
 *
 * @author Davide Cabitza
 *
 */

public interface Spostamento {


    /**
     * Metodo che calcola e aggiorna la velocità della Macchina.
     */
    public void velocita();



    /**
     * Metodo che crea una lista di tutte e sole
     * le prime mosse che la Macchina può eseguire come primo spostamento.
     */
    public void mosseIniziali();



    /**
     * Metodo che restituisce la mossa standard che la Macchina può eseguire.
     *
     */
    public Coordinata mossaStandard();



    /**
     * Metodo che crea una lista di tutte e sole
     * le mosse che la Macchina può eseguire.
     */
    public void mosse();



    /**
     * Metodo che sposta una macchina su una Coordinata passata.
     *
     * @param coordinata
     */
    public void moveTo(Coordinata coordinata);




}
