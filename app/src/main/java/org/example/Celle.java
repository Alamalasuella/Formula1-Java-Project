package org.example;

/**
 * Enumerazione che rappresenta i tipi di celle del tracciato
 * (pista, zone non accessibili, partenza, traguardo).
 *
 * @author Davide Cabitza
 *
 */

public enum Celle {

    TRACK(0), // Pista
    OUT(1), // Zona non accessibile
    START(3), // Linea di partenza
    FINISH(9), // Linea di traguardo
    BOT(7); // Giocatore org.example.Bot

    private final int value;


    /**
     * Crea una Cella con un valore intero specifico.
     *
     * @param value   valore intero della Cella
     */
    Celle(int value) {
        this.value = value;
    }


    /**
     * Metodo che restituisce il tipo di cella corrispondente al valore intero specificato.
     *
     * @param value                        il valore intero da convertire in `CellType`
     * @return                             il tipo di cella corrispondente al valore intero
     * @throws IllegalArgumentException    se il valore non corrisponde a nessun tipo di cella
     */
    public static Celle getCellaFromValue(int value) throws IllegalArgumentException {
        for (Celle type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Valore cella: " + value + " non valido.");
    }


    /**
     * Metodo Getter per la classe org.example.Celle
     *
     */

    public int getValue() {
        return value;
    }

}
