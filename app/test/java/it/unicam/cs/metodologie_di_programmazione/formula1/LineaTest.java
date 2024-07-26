package it.unicam.cs.metodologie_di_programmazione.formula1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di Test per i metodi della classe Linea.
 *
 * @author Davide Cabitza
 *
 */

class LineaTest {


    @Test
    void interseca() {
        Coordinata c1 = new Coordinata(1, 1);
        Coordinata c2 = new Coordinata(4, 4);
        Linea linea1 = new Linea(c1, c2);
        Coordinata q1 = new Coordinata(1, 4);
        Coordinata q2 = new Coordinata(4, 1);
        Linea linea2 = new Linea(q1, q2);
        assertTrue(linea1.interseca(linea2));
        Coordinata q3 = new Coordinata(5, 5);
        Coordinata q4 = new Coordinata(6, 6);
        Linea linea3 = new Linea(q3, q4);
        assertFalse(linea1.interseca(linea3));
    }


    @Test
    void intersezione() {
        Coordinata c1 = new Coordinata(1, 1);
        Coordinata c2 = new Coordinata(4, 4);
        Coordinata q1 = new Coordinata(1, 4);
        Coordinata q2 = new Coordinata(4, 1);
        Linea linea = new Linea(c1, c2);
        assertTrue(linea.intersezione(c1, c2, q1, q2));
        Coordinata q3 = new Coordinata(5, 5);
        Coordinata q4 = new Coordinata(6, 6);
        assertFalse(linea.intersezione(c1, c2, q3, q4));
    }


    @Test
    void direzione() {
        Coordinata a = new Coordinata(1, 1);
        Coordinata b = new Coordinata(4, 4);
        Coordinata c = new Coordinata(1, 4);
        Linea linea = new Linea(a, b);
        assertEquals(2, linea.direzione(a, b, c));
        Coordinata d = new Coordinata(4, 1);
        assertEquals(1, linea.direzione(a, b, d));
        Coordinata e = new Coordinata(2, 2);
        assertEquals(0, linea.direzione(a, b, e));
    }


    @Test
    void sullaLinea() {
        Coordinata a = new Coordinata(1, 1);
        Coordinata b = new Coordinata(4, 4);
        Coordinata c = new Coordinata(2, 2);
        Linea linea = new Linea(a, b);
        assertTrue(linea.sullaLinea(a, b, c));
        Coordinata d = new Coordinata(5, 5);
        assertFalse(linea.sullaLinea(a, b, d));
    }



}