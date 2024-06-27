package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe che crea una Macchina.
 *
 * Un oggetto Macchina rappresenta la pedina utilizzata
 * da un Player per muoversi nella Mappa di gioco.
 *
 * @author Cabitza Davide
 *
 */
public class  Macchina implements Spostamento{

    private Coordinata coordinata;
    private Coordinata velocita;
    private List<Coordinata> mossePrecedenti;
    private List<Coordinata> mosseDisponibili;


    /**
     * Costruisce una Macchina
     *
     * @param  c                       un oggetto Coordinata
     * @throws NullPointerException    se la Coordinata passata è <@code> null
     */
    public Macchina(Coordinata c) throws NullPointerException {
        if (c == null) throw new NullPointerException("Coordinata <@code> Null non amessa.");
        this.coordinata = c;
        this.mossePrecedenti = new ArrayList<>();
        this.mosseDisponibili = new ArrayList<>();
        mosseIniziali();
    }


    /**
     * Metodo che restituisce la Coordinata dove si trovava la Macchina al turno precedente.
     *
     * @return last  l'ultima Coordinata registrata nella lista di Coordinate rappresentante
     *               l'insieme delle posizioni precedenti della Macchina nella pista
     */
    public Coordinata lastCoordinata(){
        return this.mossePrecedenti.isEmpty() ? null
                : this.mossePrecedenti.get(this.mossePrecedenti.size() - 1);
    }


    /**
     * Metodo che calcola e aggiorna la velocità della Macchina.
     *
     */
    @Override
    public void velocita() {
        if (this.mossePrecedenti.isEmpty()) {
            this.velocita = new Coordinata(0, 0);
        } else {
            Coordinata last = lastCoordinata();
            int newX = this.coordinata.getX() - last.getX();
            int newY = this.coordinata.getY() - last.getY();
            this.velocita = new Coordinata(newX, newY);
        }
    }


    /**
     * Metodo che calcola e aggiorna le mosse disponibili della Macchina.
     *
     * Se la Macchina non si è mai spostata prima, le mosse disponibili
     * sono le otto Coordinate adiacenti alla posizione attuale della Macchina.
     *
     */
    @Override
    public void mosseIniziali(){
        List<Coordinata> vicini = this.coordinata.ottoVicini();
        vicini.remove(this.coordinata);
        this.mosseDisponibili = vicini;
    }


    /**
     * Metodo che restituisce la mossa standard che la Macchina può eseguire.
     *
     * La mossa standard corrisponde alla mossa eseguibile spostando la Macchina
     * in una nuova Coordinata calcolata in base alla sua velocità.
     *
     * @return  una Coordinata calcolata in base alla velocità della Macchina
     */
    @Override
    public Coordinata mossaStandard() {
        int newX = this.coordinata.getX() + this.velocita.getX();
        int newY = this.coordinata.getY() + this.velocita.getY();
        Coordinata newCoordinata = new Coordinata(newX, newY);
        if (newCoordinata.isPositive()) {
            return newCoordinata;
        }
        return null;
    }


    /**
     * Metodo che calcola e aggiorna le mosse disponibili
     * per la Macchina in base alla sua velocità.
     *
     */
    @Override
    public void mosse() {
        if (!this.mossePrecedenti.isEmpty()) {
            Coordinata standard = this.mossaStandard();
            if (!(mossaStandard() == null)) {
                this.mosseDisponibili.add(standard);
                List<Coordinata> viciniStandard = standard.ottoVicini();
                for (Coordinata mossa : viciniStandard){
                    if (mossa.isPositive()){
                        this.mosseDisponibili.add(mossa);
                    }
                }
            }
        }
        else {
            mosseIniziali();
        }
    }


    /**
     * Metodo che sposta una macchina sulla Coordinata data.
     *
     * @param  c                        un oggetto Coordinata rappresentante la nuova Coordinata della Macchina.
     * @throws NullPointerException     se la Coordinata passata è <@code>
     */
    @Override
    public void moveTo(Coordinata c) throws NullPointerException{
        if (c == null) throw new NullPointerException("Coordinata Null non ammessa");
        this.mossePrecedenti.add(coordinata);
        this.coordinata = c;
        velocita();
        this.mosseDisponibili.clear();
        mosse();
    }


    /**
     * Ridefinizione del metodo "equals" per la Classe Macchina.
     *
     * Due Macchine sono uguali se e solo se hanno la stessa Coordinata.
     *
     * @param  obj    l'oggetto da confrontare con questa Macchina
     * @return true   se l'oggetto specificato è una Macchina con la stessa Coordinata, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Macchina macchina = (Macchina) obj;
        return coordinata.equals(macchina.coordinata);
    }


    /**
     * Ridefinizione del metodo "hashCode" per la Classe Macchina.
     * Genera un valore di hash basato sui valori di x e y della propria Coordinata.
     *
     * @return un valore di hash per questa Macchina
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.coordinata);
    }


    /**
     * Ridefinizione del metodo "toString" per la Classe Macchina.
     * Restituisce una rappresentazione Stringa di questa Macchina.
     *
     * @return una Stringa che rappresenta questa Macchina nel formato "Macchina{Coordinata}"
     */
    @Override
    public String toString() {
        return "Macchina{" +
                this.coordinata.toString() + "}";
    }


    /**
     * Serie di metodi Getter e Setter per la Classe Macchina.
     *
     */

    public Coordinata getC() {
        return coordinata;
    }

    public void setC(Coordinata c) {
        this.coordinata = c;
    }

    public Coordinata getV() {
        return velocita;
    }

    public void setV(Coordinata v) {
        this.velocita = v;
    }

    public List<Coordinata> getMossePrecedenti() {
        return mossePrecedenti;
    }

    public List<Coordinata> getMosseDisponibili() {
        return mosseDisponibili;
    }

    public void setMosseDisponibili(List<Coordinata> mosse){
        this.mosseDisponibili = mosse;
    }

}
