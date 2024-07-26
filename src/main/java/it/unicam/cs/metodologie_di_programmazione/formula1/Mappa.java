package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe che crea la Mappa di gioco.
 *
 * Un oggetto Mappa possiede un'istanza della Classe LeggiMappa dalla quale
 * ricava la "griglia" rappresentante la Mappa di gioco.
 *
 * @author Davide Cabitza
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
     * @throws NullPointerException    se l'elemento passato è <@code> null
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
        if (y >= this.height + 1) throw new IllegalArgumentException("Valore non ammesso.");
        return this.height - 1 - y;
    }


    /**
     * Metodo che restituisce il tipo di cella corrispondente al valore intero specificato.
     *
     * @param  coordinata             la Coordinata da cui risalire alla Cella
     * @return oggetto Celle          la Cella alla posizione della Coordinata data,
     *                                Cella <@code> null se la coordinata è fuori dai limiti della Mappa
     * @throws NullPointerException   se la Coordinata passata è <@code> null
     */
    @Override
    public Celle getCellaFromCoordinata(Coordinata coordinata) throws NullPointerException {
        if (coordinata == null) throw new NullPointerException("Coordinata Null non ammessa.");
        if (!isNotValid(coordinata)) {
            return this.griglia[conversioneOridinata(coordinata.getY())][coordinata.getX()];
        } else {
            return null;
        }
    }


    /**
     * Metodo che permette la modifica del valore
     * di una Cella della griglia della Mappa che si trova alla Coordinata data.
     *
     * I valori sono rivisitati allo scopo di semplificare l'osservazione della griglia visualizzabile.
     *
     * @param  c                           un Oggetto Coordinata
     * @param  cella                       il tipo di Cella da inserire nel punto alla Coordinata data
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
        return !(c.isPositive()) || c.getY() >= this.height || c.getX() >= this.width;
    }


    /**
     * Metodo che verifica se la Coordinata specificata
     * corrisponde a un certo tipo di Cella.
     *
     * @param  coordinata                 la Coordinata da verificare
     * @param  cella                      la Cella che identifica la posizione nella Mappa
     * @return true                       se la Coordinata corrisponde a una data Cella, false altrimenti
     * @throws NullPointerException       se almeno uno degli argomenti passati è <@code> null
     */
    @Override
    public boolean isPosition(Coordinata coordinata, Celle cella) throws NullPointerException {
        if (coordinata == null || cella == null) throw new NullPointerException("Valori Null non ammessi.");
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
        List<Coordinata> listaStart = new ArrayList<>();
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                if (this.getGriglia()[i][j] == Celle.START){
                    listaStart.add(new Coordinata(j, i));
                }
            }

        }
        return listaStart;
    }


    /**
     * Metodo che restituisce la lista di tutte le Coordinate
     * rappresentanti la linea di Finish della pista.
     *
     * @return  una lista di Coordinate
     */
    @Override
    public List<Coordinata> getFinish() {
        List<Coordinata> listaFinish = new ArrayList<>();
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                Coordinata c = new Coordinata(i, j);
                if (this.getGriglia()[i][j] == Celle.FINISH){
                    listaFinish.add(new Coordinata(j, i));
                }
            }

        }
        return listaFinish;
    }


    /**
     * Metodo che restituisce la Linea
     * rappresentante la linea di arrivo della pista nella Mappa di gioco.
     *
     * @return  una oggetto Linea rappresentante il traguardo
     */
    @Override
    public Linea getFinishLine(){
        List <Coordinata> lista = getFinish();
        return new Linea(lista.get(0), lista.get(lista.size() - 1));
    }



    /**
     * Metodi Getter per la classe Mappa.
     *
     */

    public Celle[][] getGriglia() {
        return this.griglia;
    }


    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }




}
