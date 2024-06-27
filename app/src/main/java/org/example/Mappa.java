package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che crea la Mappa di gioco.
 * Un oggetto Mappa possiede un'istanza della Classe LeggiMappa dalla quale
 * ricava la "griglia" rappresentante la Mappa di gioco.
 *
 * @author Cabitza Davide
 *
 */
public class Mappa implements PuntiMappa {

    private LeggiMappa lm;
    private Celle[][] griglia;
    private int height;
    private int width;


    /**
     * Costruisce una Mappa di gioco
     *
     * @param  lm                      un oggetto LeggiMappa
     * @throws NullPointerException    se l'oggetto passato è <@code> null
     */
    public Mappa(LeggiMappa lm) throws NullPointerException {
        if (lm == null) throw new NullPointerException("Oggetto Null non ammesso.");
        this.lm = lm;
        this.griglia = lm.getGriglia();
        this.height = griglia.length;
        this.width = griglia[0].length;
    }


    /**
     * Metodo che converte l'asse delle ordinate allo scopo di rendere
     * la griglia della Mappa di gioco simile ad un piano cartesiano.
     *
     * @param  y                           valore intero rappresentante l'asse delle ordinate
     * @return height                      valore di this.height aggiornato correttamente
     * @throws IllegalArgumentException    se il valore intero passato è negativo
     */
    private int conversioneOridinata(int y) throws IllegalArgumentException {
        if (y < 0) throw new IllegalArgumentException("Valore negativo non ammesso.");
        if (y >= this.height) throw new IllegalArgumentException("Valore non ammesso.");
        return this.height - 1 - y; //problema: se y > height vai in errore OUT OF BOUNDS
    }


    /**
     * Metodo che restituisce il tipo di cella corrispondente al valore intero specificato.
     *
     * @param  coordinata             la Coordinata da cui risalire alla Cella
     * @return oggetto Celle          la Cella alla posizione della Coordinata data
     * @throws NullPointerException   se la Coordinata passata è <@code> null
     */
    @Override
    public Celle getCellaFromCoordinata(Coordinata coordinata) throws NullPointerException {
        if (coordinata == null) throw new NullPointerException("org.example.Coordinata Null non ammessa.");
        if (!isNotValid(coordinata)) {
            return this.griglia[conversioneOridinata(coordinata.getY())][coordinata.getX()];
        } else {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" +
                    coordinata.getX() + ", " + coordinata.getY() + ")");
        }
    }


    /**
     * Metodo che permette la modifica del valore
     * di una Cella della griglia della Mappa che si trova alla Coordinata data.
     *
     * I valori sono rivisitati allo scopo di semplificare l'osservazione della griglia visualizzabile.
     *
     * @param  c                           un Oggetto Coordinata
     * @param  cella                       il tipo di Cella da inserire alla Coordinata data
     * @throws NullPointerException        se almeno uno degli elementi passati è <@code> null
     * @throws IllegalArgumentException    se la Coordinata passata non è valida
     */
    @Override
    public void setCella(Coordinata c, Celle cella) throws NullPointerException, IllegalArgumentException {
        if (c == null || cella == null) throw new NullPointerException("Valori Null non ammessi.");
        if (isNotValid(c)) throw new IllegalArgumentException("Coordinata non valida.");
        this.griglia[conversioneOridinata(c.getY())][c.getX()] = cella;
    }


    /**
     * Metodo che verifica la validità di una Coordinata.
     * Una Coordinata non è valida quando si trova al di fuori della Mappa.
     *
     * @param  c                        un Oggetto Coordinata
     * @return true                     se la Coordinata passata non è valida, false altrimenti
     * @throws NullPointerException     se la Coordinata passata è <@code> null
     */
    @Override
    public boolean isNotValid(Coordinata c) throws NullPointerException {
        if (c == null) throw new NullPointerException("Coordinata Null non ammessa.");
        return !(c.isPositive())
                || c.getX() >= this.height
                || c.getY() >= this.width;
    }


    /**
     * Metodo che verifica se la Coordinata specificata
     * corrisponde a un certo tipo di Cella.
     *
     * @param  coordinata                 la Coordinata da verificare
     * @param  cella                      la Cella che identifica la posizione nella Mappa
     * @return true                       se la Coordinata corrisponde a una data Cella, false altrimenti
     * @throws NullPointerException       se almeno uno degli argomenti passati è <@code> null
     * @throws IllegalArgumentException   se la Coordinata passata non è valida
     */
    @Override
    public boolean isPosition(Coordinata coordinata, Celle cella) throws NullPointerException, IllegalArgumentException {
        if (coordinata == null || cella == null) throw new NullPointerException("Valori Null non ammessi.");
        if (isNotValid(coordinata)) throw new IllegalArgumentException("Coordinata non valida");
        return this.getCellaFromCoordinata(coordinata) == cella;
    }


    /**
     * Metodo che restituisce la lista di tutte le Coordinate
     * rappresentanti la linea di Start della pista.
     *
     * @return  una lista di Coordinate
     */
    @Override
    public List<Coordinata> getStart() {
        List<Coordinata> lista = new ArrayList<>();
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                if (this.getGriglia()[i][j] == Celle.START){
                    lista.add(new Coordinata(j, i));
                }
            }

        }
        return lista;
    }


    /**
     * Metodo che restituisce la lista di tutte le Coordinate
     * rappresentanti la linea di Finish della pista.
     *
     * @return  una lista di Coordinate
     */
    @Override
    public List<Coordinata> getFinish() {
        List<Coordinata> lista = new ArrayList<>();
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                Coordinata c = new Coordinata(i, j);
                if (this.getGriglia()[i][j] == Celle.FINISH){
                    lista.add(new Coordinata(j, i));
                }
            }

        }
        return lista;
    }


    /**
     * Metodo che restituisce la lista di tutte le Coordinate
     * rappresentanti le posizioni dei Bot presenti nella Mappa.
     *
     * @return  una lista di Coordinate
     */
    @Override
    public List<Coordinata> getPlayers() {
        List<Coordinata> lista = new ArrayList<>();
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                Coordinata c = new Coordinata(i, j);
                if (this.getGriglia()[i][j] == Celle.BOT){
                    lista.add(new Coordinata(j, i));
                }
            }

        }
        return lista;
    }



    /**
     * Metodo Getter per la classe Mappa.
     *
     */

    public Celle[][] getGriglia() {
        return this.griglia;
    }
}
