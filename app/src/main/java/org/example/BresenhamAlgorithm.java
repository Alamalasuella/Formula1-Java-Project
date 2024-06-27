package org.example;

/**
 * Classe che implementa l'algoritmo di Bresenham per il calcolo delle intersezioni.
 *
 * @author Cabitza Davide
 *
 */
public class BresenhamAlgorithm {

    /**
     * Metodo che calcola se due Coordinate si intersecano usando l'algoritmo di Bresenham.
     *
     * @param  start                   prima Coordinata con cui effettuare il confronto
     * @param  end                     seconda Coordinata con cui effettuare il confronto
     * @param  map                     oggetto Mappa in cui verificare la posizione
     * @return true                    se le due Coordinate si intersecano, false altrimenti
     * @throws NullPointerException    se anche solo uno degli argomenti passati è <@code> null
     */
    public boolean intersezione(Coordinata start, Coordinata end, Mappa map) throws NullPointerException {
        if (start == null || end == null || map == null) {
            throw new NullPointerException("Valori Null non ammessi.");
        }
        int[] initialValues = inizializzaValori(start, end);
        int x0 = initialValues[0], y0 = initialValues[1], x1 = initialValues[2], y1 = initialValues[3];
        int dx = initialValues[4], dy = initialValues[5];
        int sx = initialValues[6], sy = initialValues[7];
        int err = dx - dy;

        while (true) {
            if (map.isPosition(new Coordinata(x0, y0), Celle.FINISH)) {
                return true;
            }
            if (x0 == x1 && y0 == y1) break;
            int[] updatedValues = aggiornaValori(x0, y0, err, dx, dy, sx, sy);
            x0 = updatedValues[0];
            y0 = updatedValues[1];
            err = updatedValues[2];
        }
        return false;
    }


    /**
     * Metodo per inizializzare i valori necessari all'algoritmo di Bresenham.
     *
     * @param  start prima Coordinata
     * @param  end seconda Coordinata
     * @return array contenente i valori iniziali
     */
    private int[] inizializzaValori(Coordinata start, Coordinata end) {
        int x0 = start.getX();
        int y0 = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        return new int[]{x0, y0, x1, y1, dx, dy, sx, sy};
    }


    /**
     * Metodo per aggiornare i valori durante l'esecuzione dell'algoritmo di Bresenham.
     *
     * @param x0     coordinata x corrente
     * @param y0     coordinata y corrente
     * @param err    errore corrente
     * @param dx     differenza in x
     * @param dy     differenza in y
     * @param sx     passo in x
     * @param sy     passo in y
     * @return array contenente i valori aggiornati
     */
    private int[] aggiornaValori(int x0, int y0, int err, int dx, int dy, int sx, int sy) {
        int e2 = err * 2;
        if (e2 > -dy) {
            err -= dy;
            x0 += sx;
        }
        if (e2 < dx) {
            err += dx;
            y0 += sy;
        }
        return new int[]{x0, y0, err};
    }



}
