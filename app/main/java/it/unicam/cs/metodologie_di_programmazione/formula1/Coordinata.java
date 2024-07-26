package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Classe che crea una Coordinata.
 *
 * Un oggetto coordinata rappresenta un punto con ascissa x e ordinata y in uno spazio cartesiano.
 *
 * @author Cabitza Davide
 *
 */

public class Coordinata implements PuntiCoordinata{

    private int x;
    private int y;


    /**
     * Costruisce una Coordinata.
     *
     * @param y,x due valori Int rappresentanti ascissa e ordianta di una Coordinata
     */
    public Coordinata(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Metodo che restituisce gli otto punti vicini alla coordinata data, omettendo coordinate negative.
     *
     * @return vicini    una List<Coordinata> contenente le coordinate dei punti vicini
     *                   alla una coordinata che chiama il metodo, ad esclusione delle coordinate negative
     */
    @Override
    public List<Coordinata> ottoVicini() {
        List<Coordinata> vicini = new ArrayList<>();
        for (int x : new int[]{-1, 0, 1}) {
            for (int y : new int[]{-1, 0, 1}) {
                int newX = this.getX() + x;
                int newY = this.getY() + y;
                if (newX >= 0 && newY >= 0 && (x != 0 || y != 0)) {
                    vicini.add(new Coordinata(newX, newY));
                }
            }
        }
        return vicini;
    }


    /**
     * Metodo che verifica la positività di ascissa e ordinata della Coordinata.
     *
     * @return true  se la coordinata ha ascissa e ordinata positive, false altrimenti
     */
    @Override
    public boolean isPositive() {
        if (!(this.x < 0 || this.y < 0)) return true;
        return false;
    }


    /**
     * Ridefinizione del metodo "equals" per la classe Coordinata.
     *
     * Due Moordinate sono uguali se e solo se hanno la stessa ascissa e la stessa ordinata.
     *
     * @param  obj    l'oggetto da confrontare con questa Coordinata
     * @return true   se l'oggetto specificato è una Coordinata con gli stessi valori di x e y, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinata that = (Coordinata) obj;
        return x == that.x && y == that.y;
    }


    /**
     * Ridefinizione del metodo "hashCode" per la classe Coordinata.
     * Genera un valore di hash basato sui valori di x e y.
     *
     * @return un valore di hash per questa Coordinata
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    /**
     * Ridefinizione del metodo "toString" per la classe Coordinata.
     * Restituisce una rappresentazione stringa di questa Coordinata.
     *
     * @return una stringa che rappresenta questa Coordinata nel formato "Coordinata{x=valoreX, y=valoreY}"
     */
    @Override
    public String toString() {
        return "Coordinata{" +
                "x=" + (this.x + 1) +
                ", y=" + (this.y + 1) +
                '}';
    }



    /**
     * Serie di Metodi Getter per la Classe Coordinata
     *
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
