package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Coordinata.
 *
 * @author Davide Cabitza
 *
 */

class MacchinaTest {


    @Test
    void lastCoordinata() {
        Coordinata c = new Coordinata(0, 2);
        Macchina macchina = new Macchina(c);
        Coordinata c2 = new Coordinata(2, 4);
        macchina.moveTo(c2);
        assertEquals(macchina.lastCoordinata(), c);
        Coordinata c3 = new Coordinata(3, 5);
        macchina.moveTo(c3);
        assertEquals(macchina.lastCoordinata(), c2);
        assertEquals(2, macchina.getMossePrecedenti().size());
    }


    @Test
    void velocita() {
        Coordinata c = new Coordinata(0, 2);
        Macchina macchina = new Macchina(c);
        macchina.velocita();
        assertEquals(new Coordinata(0,0), macchina.getV());
        macchina.moveTo(new Coordinata(2,4));
        assertEquals(new Coordinata(2,2), macchina.getV());
    }


    @Test
    void mosseIniziali() {
        Coordinata c = new Coordinata(2, 2);
        Macchina macchina = new Macchina(c);
        macchina.mosseIniziali();
        List<Coordinata> lista = macchina.getMosseDisponibili();
        assertEquals(8, macchina.getMosseDisponibili().size());
        assertTrue(lista.contains(new Coordinata(1, 1)));
        assertTrue(lista.contains(new Coordinata(1, 2)));
        assertTrue(lista.contains(new Coordinata(1, 3)));
        assertTrue(lista.contains(new Coordinata(2, 1)));
        assertTrue(lista.contains(new Coordinata(2, 3)));
        assertTrue(lista.contains(new Coordinata(3, 1)));
        assertTrue(lista.contains(new Coordinata(3, 2)));
        assertTrue(lista.contains(new Coordinata(3, 3)));
    }


    @Test
    void mossaStandard(){
        Coordinata c = new Coordinata(0, 2);
        Macchina macchina = new Macchina(c);
        macchina.moveTo(new Coordinata(2,4));
        Coordinata standard = macchina.mossaStandard();
        assertEquals(standard, new Coordinata(4, 6));
        macchina.moveTo(new Coordinata(3,5));
        Coordinata standard2 = macchina.mossaStandard();
        assertEquals(standard2, new Coordinata(4, 6));
        macchina.moveTo(new Coordinata(5,6));
        Coordinata standard3 = macchina.mossaStandard();
        assertEquals(standard3, new Coordinata(7, 7));
    }


    @Test
    void mosse() {
        Coordinata c = new Coordinata(0, 2);
        Macchina macchina = new Macchina(c);
        macchina.mosse();
        assertEquals(5, macchina.getMosseDisponibili().size());
        Coordinata c2 = new Coordinata(2, 4);
        macchina.moveTo(c2);
        List<Coordinata> lista = macchina.getMosseDisponibili();
        assertEquals(9, lista.size());
    }


    @Test
    void moveTo() {
        Coordinata c = new Coordinata(0, 2);
        Macchina macchina = new Macchina(c);
        Coordinata c2 = new Coordinata(2, 4);
        macchina.moveTo(c2);
        assertEquals(c2, macchina.getC());
        assertEquals(c, macchina.lastCoordinata());
        assertEquals(1, macchina.getMossePrecedenti().size());
    }


    @Test
    void testEquals() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        assertEquals(m1, m2);
        assertNotEquals(m1, null);
        assertNotEquals(m1, new Object());
    }


    @Test
    void testHashCode() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        assertEquals(m1.hashCode(), m2.hashCode());
        Coordinata m3 = new Coordinata(4,6);
        assertNotEquals(c1.hashCode(), m3.hashCode());
    }


    @Test
    void testToString() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        assertEquals(m1.toString(), m2.toString());
        assertEquals("Macchina{Coordinata{x=2, y=3}}", m1.toString());
    }




}