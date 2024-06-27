package org.example;

/**
 * Classe che gestisce le intersezioni tra oggetti.
 *
 * @author Davide Cabitza
 *
 */
public class Traguardo implements Intersection {

    Mappa map;

    public Traguardo(Mappa map) {
        this.map = map;
    }


    /**
     * Metodo che controlla se due oggetti si intersecano
     * applicando l'algoritmo di Bresenham.
     *
     * @param  start    prima Coordinata con cui effettuare il confronto
     * @param  end      seconda Coordinata con cui effettuare il confronto
     * @return true     se le due Coordinate si intersecano, false altrimenti
     */
    @Override
    public boolean checkIntersection(Coordinata start, Coordinata end) {
        BresenhamAlgorithm bresenham = new BresenhamAlgorithm();
        return bresenham.intersezione(start, end, map);
    }



}
