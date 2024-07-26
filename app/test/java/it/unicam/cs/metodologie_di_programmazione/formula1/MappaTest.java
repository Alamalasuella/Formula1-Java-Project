package it.unicam.cs.metodologie_di_programmazione.formula1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della Classe Mappa.
 *
 * @author Davide Cabitza
 *
 */

class MappaTest {


    @Test
    void getCellaFromCoordinata() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Coordinata c = new Coordinata(0, 2);
        Coordinata c2 = new Coordinata(0, 4);
        Coordinata c3 = new Coordinata(0, 7);
        Coordinata c4 = new Coordinata(36, 3);
        Celle cella = map.getCellaFromCoordinata(c);
        Celle cella2 = map.getCellaFromCoordinata(c2);
        Celle cella3 = map.getCellaFromCoordinata(c3);
        Celle cella4 = map.getCellaFromCoordinata(c4);
        assertEquals(cella, Celle.START);
        assertEquals(cella2, Celle.START);
        assertEquals(cella3, Celle.OUT);
        assertEquals(cella4, Celle.FINISH);
    }


    @Test
    void setCella() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Coordinata c = new Coordinata(5, 5);
        assertEquals(map.getCellaFromCoordinata(c), Celle.TRACK);
        map.setCella(c, Celle.FINISH);
        assertEquals(map.getCellaFromCoordinata(c), Celle.FINISH);
        map.setCella(c, Celle.START);
        assertEquals(map.getCellaFromCoordinata(c), Celle.START);
    }


    @Test
    void isNotValid() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Coordinata c = new Coordinata(100, 105);
        assertTrue(map.isNotValid(c));
        Coordinata c1 = new Coordinata(-2, 4);
        assertTrue(map.isNotValid(c1));
        Coordinata c2 = new Coordinata(3, -2);
        assertTrue(map.isNotValid(c2));
        Coordinata c3 = new Coordinata(-3, -5);
        assertTrue(map.isNotValid(c3));
        Coordinata c4 = new Coordinata(3, 105);
        assertTrue(map.isNotValid(c4));
        Coordinata c5 = new Coordinata(326, 4);
        Coordinata c6 = new Coordinata(3, 3);
        assertTrue(map.isNotValid(c5));
        assertFalse(map.isNotValid(c6));
    }


    @Test
    void isPosition() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        Coordinata c = new Coordinata(5, 5);
        Celle cella = map.getCellaFromCoordinata(c);
        assertTrue(map.isPosition(c, Celle.TRACK));
        assertFalse(map.isPosition(c, Celle.FINISH));
        Coordinata c2 = new Coordinata(0, 5);
        Celle cella2 = map.getCellaFromCoordinata(c2);
        assertTrue(map.isPosition(c2, Celle.START));
    }


    @Test
    void getStart() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        List<Coordinata> lista = map.getStart();
        assertTrue(lista.contains(new Coordinata(0,2)));
        assertTrue(lista.contains(new Coordinata(0,3)));
        assertTrue(lista.contains(new Coordinata(0,4)));
        assertTrue(lista.contains(new Coordinata(0,5)));
    }


    @Test
    void getFinish() throws IOException {
        LeggiMappa lm = new LeggiMappa("mappa.txt");
        Mappa map = new Mappa(lm);
        List<Coordinata> lista = map.getFinish();
        assertFalse(lista.isEmpty());
        assertTrue(lista.contains(new Coordinata(36,2)));
        assertTrue(lista.contains(new Coordinata(36,3)));
        assertTrue(lista.contains(new Coordinata(36,4)));
        assertTrue(lista.contains(new Coordinata(36,5)));
    }



}