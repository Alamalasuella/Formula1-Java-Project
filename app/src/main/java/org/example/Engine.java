package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che crea un Motore di Gioco per la partita.
 *
 * @author Cabitza Davide
 *
 */
public class Engine implements MotoreDiGioco, Stampa {

    private Mappa mappa;
    private List<Bot> giocatori;
    private boolean isFinished;


    /**
     * Costruisce un Motore di Gioco, detto Engine.
     *
     * @param  map                     un Ogetto Mappa rappresentante
     *                                 l'effettiva Mappa di Gioco per una partita
     * @throws NullPointerException    se la Mappa passata è <@code> null
     *
     */
    public Engine(Mappa map) throws NullPointerException {
        if (map == null) throw new NullPointerException("Mappa Null non ammessa.");
        this.mappa = map;
        this.giocatori = new ArrayList<>();
        creaPlayers();
        this.isFinished = false;
    }


    /**
     * Metodo che genera e posiziona tre Bot ciascuno in
     * un diverso punto randomico della linea di partenza.
     *
     */
    @Override
    public void creaPlayers() {
        Random random = new Random();
        List<Coordinata> startPositions = new ArrayList<>(this.mappa.getStart());
        for (int i = 0; i < 3; i++) {
            int indiceRandom = random.nextInt(startPositions.size());
            Coordinata randomStart = startPositions.remove(indiceRandom);
            Bot g = new Bot(new Macchina(randomStart));
            this.giocatori.add(g);
        }
    }


    /**
     * Metodo che calcola le mosse possibili per un Bot.
     *
     * Modifica la Lista di mosseDisponibili della rispettiva Macchina.
     *
     * @param  giocatore                un oggetto Bot
     * @throws NullPointerException     se il giocatore passato è <@code> null
     */
    @Override
    public void mossePossibili(Bot giocatore) throws NullPointerException {
        if (giocatore == null) throw new NullPointerException("Bot Null non ammesso.");
        Macchina macchina = giocatore.getM();
        List<Coordinata> mosse = macchina.getMosseDisponibili();
        List<Coordinata> mossePossibili = new ArrayList<>();
        for (Coordinata mossa : mosse) {
            if (this.mappa.isPosition(mossa, Celle.OUT) || this. mappa.isNotValid(mossa)) {
                continue;
            }
            mossePossibili.add(mossa);
        }
        macchina.setMosseDisponibili(mossePossibili);
    }


    /**
     * Metodo che verifica se un Bot è in partita o squalificato.
     *
     * Un Bot è in partita finchè la sua Macchian ha delle mosseDisponibili
     * o finchè non supera la linea di traguardo, è squalificato altrimenti.
     *
     * @param  giocatore                un oggetto Bot
     * @throws NullPointerException     se il giocatore passato è <@code> null
     */
    @Override
    public void checkPlayer(Bot giocatore) throws NullPointerException {
        if (giocatore == null) throw new NullPointerException("Bot Null non ammesso.");
        Macchina m = giocatore.getM();
        Coordinata c = m.getC();
        if (!mappa.isPosition(c, Celle.START)) {
            mossePossibili(giocatore);
            if (m.getMosseDisponibili().isEmpty()) {
                giocatore.setLive(false);
            }
        }
    }


    /**
     * Metodo che verifica lo stato dei Bot che partecipano alla partita.
     *
     * @return true     se tutti i Bot nella lista di giocatori
     *                  che partecipano alla partita sono squalificati.
     */
    @Override
    public boolean playerState() {
        return this.giocatori.stream().allMatch(Bot::isOut);
    }


    /**
     * Metodo che verifica lo stato della partita.
     *
     * Una partita è in corso fintanto che i Bot gareggiano
     * senza superare la linea di traguardo.
     *
     * @return true    se anche solo un Bot nella lista di giocatori
     *                 che partecipano alla partita ha superato il traguardo.
     */
    @Override
    public boolean matchState(Bot giocatore) {
        Macchina m = giocatore.getM();
        Coordinata currentPos = m.getC();
        if (!m.getMossePrecedenti().isEmpty()) {
            Coordinata lastPos = m.lastCoordinata();
            Traguardo traguardo = new Traguardo(this.mappa);
            if (traguardo.checkIntersection(lastPos, currentPos)) {
                setFinished(true);
                return true;
            }
            if (this.mappa.getFinish().contains(m.getC())){
                setFinished(true);
                return true;
            }
        }
        return false;
    }


    /**
     * Metodo che aggiorna il valore delle Celle con le posizioni dei Bot.
     *
     * Assegna una "Celle.BOT" a tutte le Celle che sono state visitate da un Bot.
     *
     */
    @Override
    public void aggiornaMappa(){
        for (Bot giocatore : this.giocatori) {
            if (giocatore.isOn()) {
                Macchina macchina = giocatore.getM();
                Coordinata current = macchina.getC();
                List<Coordinata> mossePrecedenti = macchina.getMossePrecedenti();
                if (mossePrecedenti.isEmpty()) {
                    this.mappa.setCella(current, Celle.BOT);
                    continue;
                }
                this.mappa.setCella(current, Celle.BOT);
            }
        }
    }


    /**
     * Metodo che reimposta il velore originale delle Celle
     * della Mappa di gioco dove hanno transitato i Bot che partecipano alla gara.
     *
     * @param  giocatore                un oggetto Bot
     * @throws NullPointerException     se il Bot passato è <@code> null
     */
    public void resetCelle(Bot giocatore) throws NullPointerException{
        if (giocatore == null) throw new NullPointerException("Bot Null non ammesso.");
        if (giocatore.isOn()) {
            Macchina macchina = giocatore.getM();
            Coordinata lastPosition = macchina.lastCoordinata();
            if (lastPosition == null) {
                return;
            }
            if (this.mappa.getStart().contains(lastPosition)) {
                this.mappa.setCella(lastPosition, Celle.START);
            } else {
                this.mappa.setCella(lastPosition, Celle.TRACK);
            }
        }
    }


    /**
     * Metodo che aggiorna la griglia della Mappa di gioco.
     *
     * Per rappresentare la posizione di un Bot sulla pista,
     * rende la Cella alla Coordinata del Bot una "Celle.BOT".
     *
     * Se un Bot si sposta il valore della cella viene ripristinato coerentemente.
     *
     */
    @Override
    public void stampaBOT() {
        for (int i = 0; i < this.giocatori.size(); i++){
            System.out.println("Bot numero " + (i + 1) + ":");
            if(giocatori.get(i).isOn()){
                System.out.println(" - Il Bot sta gareggiando! Posizione: " + giocatori.get(i).getM().toString());
            } else if (giocatori.get(i).isOut()) {
                System.out.println("Il Bot è squalificato." + "\n");
            }
        }
        System.out.println("\n");
    }


    /**
     * Metodo che stampa la Mappa di Gioco corrente.
     *
     */
    @Override
    public void stampaMappa() {
        for (Celle[] row : mappa.getGriglia()) {
            StringBuilder rowString = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                rowString.append(row[j]);
                if (j < row.length - 1) {
                    rowString.append(", ");
                }
            }
            System.out.println(rowString);
        }
        System.out.println("\n\n\n");
    }


    /**
     * Metodo che avvia una partita.
     *
     * Una partita inizia con l'inizializzazione della Mappa di gioco
     * e dei Bot che partecipano alla partita.
     *
     * I Bot si muovono a turno finchè non sono tutti squalificati
     * o finchè un Bot non vince la gara (supera la linea di traguardo).
     *
     */
    @Override
    public void startPartita(){
        while (!playerState() || isFinished()) {
            for (Bot g : this.giocatori) {
                if (g.isOn()) {
                    checkPlayer(g);
                    g.turno();
                    matchState(g);
                    resetCelle(g);
                }
            }
            stampaBOT();
            aggiornaMappa();
            stampaMappa();
        }
        System.out.println("La partita è terminata!");
    }



    /**
     * Serie di Metodi Getter e Setter per la Classe Engine.
     *
     */

    public List<Bot> getGiocatori () {
        return giocatori;
    }


    public boolean isFinished() {
        return isFinished;
    }


    public void setFinished(boolean finished) {
        isFinished = finished;
    }


}
