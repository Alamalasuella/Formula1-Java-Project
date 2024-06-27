package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di Test per i metodi della classe Celle.
 *
 * @author Cabitza Davide
 *
 */

class CelleTest {

    @Test
    public void testFromValueWithValidValues() {
        assertEquals(Celle.TRACK, Celle.getCellaFromValue(0));
        assertEquals(Celle.OUT, Celle.getCellaFromValue(1));
        assertEquals(Celle.START, Celle.getCellaFromValue(3));
        assertEquals(Celle.FINISH, Celle.getCellaFromValue(9));
    }

    @Test
    public void testFromValueWithInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Celle.getCellaFromValue(5); // 5 non è un valore valido
        });
        String expectedMessage = "Valore cella: 5 non valido.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getValue() {
        Celle pista = Celle.TRACK;
        assertEquals(0, pista.getValue());
        Celle fuori = Celle.OUT;
        assertEquals(1, fuori.getValue());
        Celle start = Celle.START;
        assertEquals(3, start.getValue());
        Celle finish = Celle.FINISH;
        assertEquals(9, finish.getValue());
        Celle bot = Celle.BOT;
        assertEquals(7, bot.getValue());
    }
}