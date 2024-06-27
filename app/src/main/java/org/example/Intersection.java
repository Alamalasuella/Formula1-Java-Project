package org.example;

/**
 * Interfaccia per gestire le intersezioni tra Punti della Mappa di gioco.
 *
 * @author Davide Cabitza
 *
 */
public interface Intersection {


    /**
     * Metodo che controlla se due Coordinate si intersecano.
     */
    public boolean checkIntersection(Coordinata c1, Coordinata c2);


}
