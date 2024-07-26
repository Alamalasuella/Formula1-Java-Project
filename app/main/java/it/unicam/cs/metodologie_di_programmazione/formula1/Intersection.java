package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Interfaccia per gestire le intersezioni tra Coordinate della Mappa di gioco.
 *
 * @author Davide Cabitza
 *
 */
public interface Intersection {


    /**
     * Metodo che verifica se due Linee si intersecano o meno.
     */
    public boolean interseca(Linea altra) ;



    /**
     * Metodo che verifica se due Linee si intersecano o meno.
     */
    public boolean intersezione(Coordinata c1, Coordinata c2, Coordinata q1, Coordinata q2);



    /**
     * Metodo che calcola la direzione di un punto rispetto a un segmento definito da due coordinate.
     */
    public int direzione(Coordinata a, Coordinata b, Coordinata c);



    /**
     * Metodo che verifica se un punto è su una certa Linea.
     */
    public boolean sullaLinea(Coordinata a, Coordinata b, Coordinata c);




}
