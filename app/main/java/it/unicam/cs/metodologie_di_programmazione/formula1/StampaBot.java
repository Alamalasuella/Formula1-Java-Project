package it.unicam.cs.metodologie_di_programmazione.formula1;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe per la Stampa delle informazioni sui Bot in gara.
 *
 * @author Davide Cabitza
 *
 */
public class StampaBot implements PlayerPrinter{

    List<Bot> giocatori;


    /**
     * Costruisce un oggetto StampaBot.
     *
     * @param  giocatori                una Lista di Bot rappresentanti i Bot che parteciperanno alla gara.
     * @throws NullPointerException     se la Lista passata è <@code> null
     */
    public StampaBot(List<Bot> giocatori) throws NullPointerException{
        if (giocatori == null) throw new NullPointerException("Lista Null non ammessa.");
        this.giocatori = giocatori;
    }


    /**
     * Metodo che annuncia i Bot che parteciperanno alla gara.
     *
     * Stampa il numero dei partecipanti alla gara e poi stampa i Nomi di ciascun Bot.
     *
     */
    @Override
    public void stampaStart(){
        System.out.println("I Bot che partecipano alla gara sono i seguenti " + this.giocatori.size() + ":" + "\n");
        for (Bot bot : this.giocatori) {
            String botName = bot.getNome();
            System.out.println("- Bot " + (botName) + "." + "\n");
        }
    }


    /**
     * Metodo che aggiorna la griglia della Mappa di gioco.
     *
     * Per rappresentare la posizione di un Bot sulla pista,
     * rende la Cella alla Coordinata del Bot una "Celle.BOT".
     *
     */
    @Override
    public void stampaPlayers() {
        for (Bot bot : this.giocatori) {
            String botName = bot.getNome();
            System.out.println("Bot " + (botName) + ":");
            if (bot.isOn()) {
                System.out.println(botName + " sta gareggiando! Posizione: " + bot.getM().toString() + "\n");
            } else {
                System.out.println(botName + " squalificato dalla gara." + "\n");
            }
        }
    }


    /**
     * Metodo che annuncia l'esito della gara.
     *
     * Annuncia l'eventuale Bot vincitore della gara o
     * il termine della gara per squalifica collettiva dei partecipanti.
     *
     */
    @Override
    public void stampaEsito(){
        List<Bot> squalificati = new ArrayList<>();
        Bot primo = null;
        int maxX = Integer.MIN_VALUE;
        for (Bot bot : this.giocatori) {
            if (!bot.isOn()) {
                squalificati.add(bot);
            }else{
                int botX = bot.getM().getC().getX();
                if (botX > maxX) {
                    maxX = botX;
                    primo = bot;
                }
            }
        }if(squalificati.size() == this.giocatori.size()) {
            System.out.println("Tutti i Bot sono squalificati, gara terminata!");
        }else{
            System.out.println("Il Bot " + primo.getNome() + " ha tagliato il tragurado!");
            System.out.println("\n" + primo.getNome() + " ha vinto la gara!");
        }
    }




}
