package it.unicam.cs.metodologie_di_programmazione.formula1;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Coordinata.
 *
 * @author Cabitza Davide
 *
 */
class CoordinataTest {


    @Test
    void ottoVicini() {
        Coordinata c = new Coordinata(2, 2);
        List<Coordinata> lista = c.ottoVicini();
        assertEquals(8, lista.size());
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
    void isPositive(){
        Coordinata c = new Coordinata(2, 2);
        assertTrue(c.isPositive());
        Coordinata c2 = new Coordinata(-2, 2);
        assertFalse(c2.isPositive());
    }


    @Test
    void testEquals() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        assertEquals(c1, c2);
        assertNotEquals(c1, null);
        assertNotEquals(c1, new Object());
    }


    @Test
    void testHashCode() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        assertEquals(c1.hashCode(), c2.hashCode());
        Coordinata c3 = new Coordinata(4,6);
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }


    @Test
    void testToString() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        assertEquals(c1.toString(), c2.toString());
        assertEquals("Coordinata{x=3, y=4}", c1.toString());
    }


    @Test
    void getX() {
        Coordinata c1 = new Coordinata(2,3);
        assertEquals(c1.getX(), 2);
        assertNotEquals(3, c1.getX());
    }


    @Test
    void getY() {
        Coordinata c1 = new Coordinata(2,3);
        assertEquals(c1.getY(), 3);
        assertNotEquals(5, c1.getY());
    }


}