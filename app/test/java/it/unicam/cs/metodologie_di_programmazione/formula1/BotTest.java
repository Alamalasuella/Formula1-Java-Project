package it.unicam.cs.metodologie_di_programmazione.formula1;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Bot.
 *
 * @author Davide Cabitza
 */

class BotTest {


    @Test
    void move() {
        Coordinata c1 = new Coordinata(0, 2);
        Macchina m1 = new Macchina(c1);
        Bot b1 = new Bot(m1);
        b1.move();
        assertEquals(b1.getM().getC() != b1.getM().lastCoordinata(), true);
        assertEquals(!Objects.equals(b1.getM().getV(), new Coordinata(0, 0)), true);
    }


    @Test
    void turno() {
        Coordinata c1 = new Coordinata(0, 2);
        Macchina m1 = new Macchina(c1);
        Bot b1 = new Bot(m1);
        b1.turno();
        assertEquals(b1.isTurn(), false);
        assertEquals(b1.getM().getC() != c1, true);
    }


    @Test
    void testEquals() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        Bot b1 = new Bot(m1);
        Bot b2 = new Bot(m2);
        assertEquals(b1, b2);
        assertNotEquals(b1, null);
        assertNotEquals(b1, new Object());
    }


    @Test
    void testHashCode() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        Bot b1 = new Bot(m1);
        Bot b2 = new Bot(m2);
        assertEquals(b1.hashCode(), b2.hashCode());
        Coordinata b3 = new Coordinata(4,6);
        assertNotEquals(c1.hashCode(), b3.hashCode());
    }


    @Test
    void testToString() {
        Coordinata c1 = new Coordinata(2,3);
        Coordinata c2 = new Coordinata(2,3);
        Macchina m1 = new Macchina(c1);
        Macchina m2 = new Macchina(c2);
        Bot b1 = new Bot(m1);
        Bot b2 = new Bot(m2);
        assertEquals(b1.toString(), b2.toString());
        assertEquals("Bot{Macchina{Coordinata{x=3, y=4}}}", b1.toString());
    }


}