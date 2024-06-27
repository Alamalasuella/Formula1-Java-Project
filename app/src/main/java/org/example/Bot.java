package org.example;

import java.util.Objects;
import java.util.Random;

/**
 * Classe che crea Giocatori Bot.
 *
 * Un oggetto Giocatore Bot possiede una Macchina con la Coordinata indicante
 * la sua posizione nella Mappa di gioco, analogamente alla classe Gicatore,
 * inoltre fornisce i metodi per spostare la loro Macchina lungo la Mappa di gioco.
 *
 * @author Cabitza Davide
 *
 */
public class Bot implements movimentoPlayer{

    private Macchina macchina;
    private boolean isTurn;
    private boolean isAlive;


    /**
     * Costruisce un Giocatore Bot
     *
     * @param  m                       un oggetto Macchina m rappresentate la pedina di gioco del Bot
     * @throws NullPointerException    se la Macchina passata al Bot è <@code> null
     */
    public Bot(Macchina m) throws NullPointerException{
        if (m == null) throw new NullPointerException("Macchina Null non ammessa.");
        this.macchina = m;
        this.isTurn = false;
        this.isAlive = true;

    }


    /**
     * Metodo che sposta la Macchina del Bot su una Coordinata randomica
     * tra quelle disponibili per la Macchina.
     *
     */
    @Override
    public void move() {
        if (this.isTurn) {
            Random random = new Random();
            int i = random.nextInt(this.macchina.getMosseDisponibili().size());
            Coordinata rnd = this.macchina.getMosseDisponibili().get(i);
            this.macchina.moveTo(rnd);
            this.isTurn = false;
        }
    }


    /**
     * Metodo che esegue il turno del Bot.
     *
     */
    @Override
    public void turno() {
        this.setTurn(true);
        this.move();
    }


    /**
     * Ridefinizione del metodo "equals" per la Classe Bot.
     *
     * Due Bot sono uguali se e solo se hanno la stessa Macchina
     * e di conseguenza la stessa Coordinata/posizione nella Mappa di gioco.
     *
     * @param  obj    l'oggetto da confrontare con questo Bot
     * @return true   se l'oggetto specificato è una Bot con la stessa Macchina, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bot bot = (Bot) obj;
        return macchina.equals(bot.macchina);
    }


    /**
     * Ridefinizione del metodo "hashCode" per la Classe Bot.
     * Genera un valore di hash basato sul valore della propria Coordinata.
     *
     * @return  un valore di hash per questo Bot
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.macchina);
    }


    /**
     * Ridefinizione del metodo "toString" per la Classe Bot.
     * Restituisce una rappresentazione Stringa di questo Bot.
     *
     * @return  una Stringa che rappresenta questo Bot nel formato "Bot{Macchina{Coordinata}}"
     */
    @Override
    public String toString() {
        return "Bot{" +
                this.macchina.toString() + "}";
    }



    /**
     * Serie di metodi Getter e Setter per la Classe Macchina.
     *
     */

    public Macchina getM() {
        return macchina;
    }

    public boolean isTurn() {
        return isTurn;
    }


    public void setTurn(boolean turno) {
        isTurn = turno;
    }


    public boolean isOn() {
        return isAlive;
    }


    public boolean isOut() {
        return !isAlive;
    }


    public void setLive(boolean vivo) {
        isAlive = vivo;
    }



}
