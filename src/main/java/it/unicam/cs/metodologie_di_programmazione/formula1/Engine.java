package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Classe che crea un Motore di Gioco per la partita.
 *
 * Il motore di gioco si occupa di coordinare tutte le componenti del gioco,
 * definisce metodi per creare i bot, calcolare le mosse, aggiornare la mappa e verificare lo stato della partita.
 *
 * Gestisce anche la partita garantendo il ciclo di vita del gioco, incluse le condizioni di vittoria e squalifica.
 *
 * @author Davide Cabitza
 *
 */
public class Engine implements MotoreDiGioco {

    private Mappa mappa;
    private List<Bot> giocatori;
    private boolean isFinished;
    private String mapFile;
    private String botFile;


    /**
     * Costruisce un Motore di Gioco, detto Engine.
     *
     * @param  lm                       un Ogetto LeggiMappa rappresentante
     *                                  l'effettiva Mappa di Gioco per una partita
     * @param  lb                       un Ogetto LeggiBot rappresentante
     *                                  i nomi dei Bot da utilizzare per una partita
     * @throws NullPointerException     se l'Oggetto LeggiMappa passato è <@code> null
     * @throws IOException              se scaturisce un errore all lettura del file
     */
    public Engine(LeggiMappa lm, LeggiBot lb) throws NullPointerException, IOException {
        if (lm == null || lb == null) throw new NullPointerException("Risorsa da File Nulla non ammessa.");
        this.mapFile = lm.getFileName();
        this.botFile = lb.getFileName();
        this.mappa = new Mappa(lm);
        this.giocatori = new ArrayList<>();
        creaPlayers();
        this.isFinished = false;
    }


    /**
     * Metodo che genera e posiziona tre Bot con Nome casuale
     * ciascuno in un diverso punto randomico della linea di partenza.
     *
     * @throws IOException       se scaturisce un errore all lettura del file
     */
    @Override
    public void creaPlayers() throws IOException {
        Random random = new Random();
        List<Coordinata> startPositions = new ArrayList<>(this.mappa.getStart());
        LeggiBot leggiBot = new LeggiBot(this.botFile);
        List<String> nomi = leggiBot.getNames();
        for (int i = 0; i < 3; i++) {
            int indiceRandom = random.nextInt(startPositions.size());
            int nomeRandom = random.nextInt(nomi.size());
            Coordinata randomStart = startPositions.remove(indiceRandom);
            String randomNome = nomi.remove(nomeRandom);
            Bot g = new Bot(new Macchina(randomStart));
            g.setNome(randomNome);
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
        List<Coordinata> NewMossePossibili = new ArrayList<>();
        for (Coordinata mossa : mosse) {
            if (this.mappa.isPosition(mossa, Celle.OUT) || this.mappa.isPosition(mossa, Celle.BOT) || this.mappa.isNotValid(mossa)) {
                continue;
            }
            NewMossePossibili.add(mossa);
        }
        macchina.setMosseDisponibili(NewMossePossibili);
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
     * @return true     se tutti i Bot nella lista di giocatori.txt
     *                  che partecipano alla partita sono squalificati.
     */
    @Override
    public void playerState() {
        List<Bot> squalificati = new ArrayList<>();
        for (Bot bot : this.giocatori) {
            if (!bot.isOn()) {
                squalificati.add(bot);
            }
        }
        if (squalificati.size() == this.giocatori.size()) {
            setFinished(true);
        }
    }


    /**
     * Metodo che verifica lo stato della partita.
     *
     * Una partita è in corso fintanto che i Bot gareggiano
     * senza superare la linea di traguardo.
     *
     * @return true    se anche solo un Bot nella lista di giocatori.txt
     *                 che partecipano alla partita ha superato il traguardo.
     */
    @Override
    public boolean matchState(Bot giocatore) {
        Macchina m = giocatore.getM();
        Coordinata ultima = m.lastCoordinata();
        if (!m.getMossePrecedenti().isEmpty()) {
            Coordinata standard = m.mossaStandard();
            List<Coordinata> ottoStarndard = standard.ottoVicini();
            for (Coordinata mossa : ottoStarndard) {
                Linea line = new Linea(ultima,mossa);
                if (mossa.getX() >= mappa.getWidth()-1 || line.interseca(mappa.getFinishLine())){
                    setFinished(true);
                    return true;
                }
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
                this.mappa.setCella(current, Celle.BOT);
            }
        }
    }


    /**
     * Metodo che ripristina le Celle della Mappa ai loro valori originali
     * e posiziona i Bot nelle loro attuali posizioni.
     *
     * @throws IOException       se scaturisce un errore all lettura del file
     */
    @Override
    public void resetCelle() throws IOException {
        List<Coordinata> posizioniBot = new ArrayList<>();
        for (Bot bot : this.giocatori) {
            Coordinata coordinataBot = bot.getM().getC();
            if (bot.isOn()) {
                posizioniBot.add(coordinataBot);
            }
        }
        LeggiMappa lm = new LeggiMappa(this.mapFile);
        Mappa nuovaMappa = new Mappa(lm);
        for (Coordinata pos : posizioniBot) {
            nuovaMappa.setCella(pos, Celle.BOT);
        }
        this.mappa = nuovaMappa;
        StampaMappa stmp = new StampaMappa(this.mappa);
        stmp.stampaMappa();
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
     * @throws IOException       se scaturisce un errore all lettura del file
     */
    @Override
    public void startPartita() throws IOException {
        StampaBot stbt = new StampaBot(this.giocatori);
        StampaMappa stmp = new StampaMappa(this.mappa);
        stbt.stampaStart();
        stmp.stampaMappa();
        while (!isFinished()) {
            for (Bot g : this.giocatori) {
                if (g.isOn()) {
                    checkPlayer(g);
                    g.turno();
                    matchState(g);
                }
            }
            playerState();
            aggiornaMappa();
            stbt.stampaPlayers();
            resetCelle();
        }
        stbt.stampaEsito();
    }




    /**
     * Serie di Metodi Getter e Setter per la Classe Engine.
     *
     */

    public Mappa getMappa() {
        return mappa;
    }

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
