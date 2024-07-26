package it.unicam.cs.metodologie_di_programmazione.formula1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Engine.
 *
 * @author Davide Cabitza
 *
 */
class EngineTest {


    @Test
    void creaPlayers() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        assertFalse(motore.getGiocatori().isEmpty());
        assertEquals(3, motore.getGiocatori().size());
        assertEquals(Celle.START, motore.getMappa().getCellaFromCoordinata(motore.getGiocatori().get(0).getM().getC()));
        assertEquals(Celle.START, motore.getMappa().getCellaFromCoordinata(motore.getGiocatori().get(1).getM().getC()));
        assertEquals(Celle.START, motore.getMappa().getCellaFromCoordinata(motore.getGiocatori().get(2).getM().getC()));
    }


    @Test
    void mossePossibili() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        Bot g = motore.getGiocatori().get(0);
        Bot g2 = motore.getGiocatori().get(1);
        Bot g3 = motore.getGiocatori().get(2);
        Celle cella = motore.getMappa().getCellaFromCoordinata(g.getM().getC());
        List<Coordinata> mosse = g.getM().getMosseDisponibili();
        g.getM().moveTo(new Coordinata(0,0));
        motore.mossePossibili(g);
        List<Coordinata> mosse2 = g.getM().getMosseDisponibili();
        assertTrue(mosse2.isEmpty());
        Macchina m2 = g2.getM();
        Macchina m3 = g3.getM();
        m2.moveTo(new Coordinata(3,3));
        m3.moveTo(new Coordinata(3,4));
        motore.mossePossibili(g2);
        List<Coordinata> mosse3 = g2.getM().getMosseDisponibili();
        assertFalse(mosse3.contains(g3.getM().getC()));
    }


    @Test
    void checkPlayer() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        Bot g = new Bot(motore.getGiocatori().get(0).getM());
        g.getM().moveTo(new Coordinata(0,0));
        motore.checkPlayer(g);
        assertFalse(g.isOn());
        assertTrue(g.getM().getMosseDisponibili().isEmpty());
    }


    @Test
    void playerState() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        motore.playerState();
        assertFalse(motore.isFinished());
        for(Bot g : motore.getGiocatori()){
            g.setLive(false);
        }
        motore.playerState();
        assertTrue(motore.isFinished());
    }


    @Test
    void matchState() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        Bot g = motore.getGiocatori().get(0);
        g.getM().moveTo(new Coordinata(36,3));
        List<Coordinata> lista = motore.getMappa().getFinish();
        Coordinata gC = g.getM().getC();
        assertTrue(lista.contains(gC));
        motore.aggiornaMappa();
        assertTrue(motore.matchState(g));
    }


    @Test
    void aggiornaMappa() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        Bot g = motore.getGiocatori().get(0);
        motore.aggiornaMappa();
        assertTrue(motore.getMappa().isPosition(g.getM().getC(), Celle.BOT));
        g.turno();
        Coordinata gC2 = g.getM().getC();
        motore.aggiornaMappa();
        assertTrue(motore.getMappa().isPosition(gC2, Celle.BOT));
    }


    @Test
    public void startPartita() throws IOException{
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        LeggiBot lb = new LeggiBot("giocatori.txt");
        Engine motore = new Engine(lm, lb);
        motore.startPartita();
        assertTrue(motore.isFinished());
    }



}