package it.unicam.cs.metodologie_di_programmazione.formula1;

/**
 * Enumerazione che rappresenta i tipi di celle del tracciato
 * (pista, zone non accessibili, partenza, traguardo).
 *
 * @author Davide Cabitza
 *
 */

public enum Celle {

    TRACK(0),
    OUT(1),
    START(3),
    FINISH(9),
    BOT(7);

    private final int value;


    /**
     * Crea una Cella con un valore intero specifico.
     *
     * @param  value                        valore intero della Cella
     */
    Celle(int value) throws NullPointerException{
        this.value = value;
    }


    /**
     * Metodo che restituisce il tipo di cella corrispondente al valore intero specificato.
     *
     * @param  value                       il valore intero da convertire in `CellType`
     * @return oggetto Celle               il tipo di cella corrispondente al valore intero passato
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
     * Metodo Getter per la classe Celle
     *
     */

    public int getValue() {
        return value;
    }

}
