package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Engine.
 *
 * @author Cabitza Davide
 *
 */
class EngineTest {


    @Test
    void creaPlayers() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        assertFalse(motore.getGiocatori().isEmpty());
        assertEquals(3, motore.getGiocatori().size());
        assertEquals(Celle.START, map.getCellaFromCoordinata(motore.getGiocatori().get(0).getM().getC()));
        assertEquals(Celle.START, map.getCellaFromCoordinata(motore.getGiocatori().get(1).getM().getC()));
        assertEquals(Celle.START, map.getCellaFromCoordinata(motore.getGiocatori().get(2).getM().getC()));
    }


    @Test
    void mossePossibili() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        Bot g = motore.getGiocatori().get(0);
        Celle cella = map.getCellaFromCoordinata(g.getM().getC());
        List<Coordinata> mosse = g.getM().getMosseDisponibili();
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        g.turno();
        System.out.println(g.getM().getC().toString());
        motore.mossePossibili(g);
        List<Coordinata> mosse2 = g.getM().getMosseDisponibili();
    }


    @Test
    void checkPlayer() throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        Bot g = new Bot(motore.getGiocatori().get(0).getM());
        g.getM().moveTo(new Coordinata(0,0));
        motore.checkPlayer(g);
        assertFalse(g.isOn());
        assertTrue(g.getM().getMosseDisponibili().isEmpty());
    }


    @Test
    void playerState() throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        assertFalse(motore.playerState());
        for(Bot g : motore.getGiocatori()){
            g.setLive(false);
        }
        assertTrue(motore.playerState());
    }


    @Test
    void matchState() throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        Bot g = motore.getGiocatori().get(0);
        Coordinata gC = g.getM().getC();
        g.getM().moveTo(new Coordinata(36,3));
        List<Coordinata> lista = map.getFinish();
        assertTrue(lista.contains(gC));
        motore.aggiornaMappa();
        assertTrue(motore.matchState(g));
    }


    @Test
    void aggiornaMappa() throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        Bot g = motore.getGiocatori().get(0);
        motore.aggiornaMappa();
        assertTrue(map.isPosition(g.getM().getC(), Celle.BOT));
        g.turno();
        Coordinata gC2 = g.getM().getC();
        motore.aggiornaMappa();
        assertTrue(map.isPosition(gC2, Celle.BOT));
    }


    @Test
    void resetCelle() throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
        Bot g = motore.getGiocatori().get(0);
        Coordinata gC = g.getM().getC();
        motore.aggiornaMappa();
        assertTrue(map.isPosition(gC, Celle.BOT));
        g.turno();
        Coordinata gC2 = g.getM().getC();
        motore.resetCelle(g);
        assertTrue(map.isPosition(gC, Celle.START));
        assertFalse(map.getPlayers().isEmpty());
    }


    @Test
    void startPartita()throws IOException {
        String fileName = "mappa.txt";
        LeggiMappa lm = new LeggiMappa(fileName);
        Mappa map = new Mappa(lm);
        Engine motore = new Engine(map);
    }




}