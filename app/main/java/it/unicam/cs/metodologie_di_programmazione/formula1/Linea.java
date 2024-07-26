package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Classe che crea una Linea tra due Coordinate.
 *
 * Un oggetto Linea rappresenta un segmento che unisce i punti di due Coordinate in uno spazio cartesiano.
 *
 * @author Davide Cabitza
 *
 */

public class Linea implements Intersection{

    private Coordinata c1;
    private Coordinata c2;


    /**
     * Costruisce una Linea
     *
     * @param  c1                       un ogggetto Coordinatache rappresenta il punto iniziale di un segmento
     * @param  c2                       un oggetto Coordinata che rappresenta il punto finale di un segmento
     * @throws NullPointerException     se almeno una delle Coordinate passate è <@code> null
     */
    public Linea(Coordinata c1, Coordinata c2) throws NullPointerException{
        if (c1 == null || c2 == null) throw new NullPointerException("Coordinata Null non ammessa.");
        this.c1 = c1;
        this.c2 = c2;
    }


    /**
     * Metodo che verifica se due Linee si intersecano o meno.
     *
     * @param  altra                    un oggetto Linea rappresentante l'altra Linea con cui fare la verifica
     * @return true                     se le Linee si intersecano, false altrimenti
     * @throws NullPointerException     se la Linea passata è <@code> null
     */
    @Override
    public boolean interseca(Linea altra) throws NullPointerException{
        if (altra == null) throw new NullPointerException("Linea Null non ammessa.");
        return intersezione(this.c1, this.c2, altra.c1, altra.c2);
    }


    /**
     * Metodo che verifica se due Linee si intersecano o meno.
     *
     * @param  c1                        un ogggetto Coordinatache rappresenta il punto iniziale di un segmento
     * @param  c2                        un oggetto Coordinata che rappresenta il punto finale di un segmento
     * @param  q1                        un oggetto Coordinata che rappresenta il punto iniziale di un altro segmento
     * @param  q2                        un oggetto Coordinata che rappresenta il punto finale di un altro segmento
     * @return true                      se le Linee si intersecano, false altrimenti
     * @throws NullPointerException      se almeno uno degli elementi passati è <@code> null
     */
    @Override
    public boolean intersezione(Coordinata c1, Coordinata c2, Coordinata q1, Coordinata q2) throws NullPointerException {
        if (c1 == null || c2 == null || q1 == null || q2 == null) {
            throw new NullPointerException("Coordinata Null non amessa.");
        }
        // Calcolo delle direzioni
        int d1 = direzione(q1, q2, c1);
        int d2 = direzione(q1, q2, c2);
        int d3 = direzione(c1, c2, q1);
        int d4 = direzione(c1, c2, q2);
        // Condizioni per verificare se i segmenti si intersecano
        if (d1 != d2 && d3 != d4) {
            return true;
        }
        // Verifica se i punti sono collineari (appartenenti a una medesima retta)
        if (d1 == 0 && sullaLinea(q1, q2, c1)) {
            return true;
        }
        if (d2 == 0 && sullaLinea(q1, q2, c2)) {
            return true;
        }
        if (d3 == 0 && sullaLinea(c1, c2, q1)) {
            return true;
        }
        if (d4 == 0 && sullaLinea(c1, c2, q2)) {
            return true;
        }
        return false;
    }


    /**
     * Metodo che calcola la direzione di un punto rispetto a un segmento definito da due altre Coordinate.
     *
     * @param   a                        un oggetto Coordinata che rappresenta il primo punto del segmento
     * @param   b                        un oggetto Coordinata che rappresenta il secondo punto del segmento
     * @param   c                        un oggetto Coordinata che rappresenta il punto rispetto al quale si calcola la direzione
     *
     * @return  1                        se il punto c è alla destra del segmento ab,
     *          2                        se il punto c è alla sinistra del segmento ab,
     *          0                        se i punti sono collineari
     *
     * @throws  NullPointerException     se almeno una delle Coordinate passate è <code>null
     */
    @Override
    public int direzione(Coordinata a, Coordinata b, Coordinata c) throws NullPointerException {
        if (a == null || b == null || c == null) throw new NullPointerException("Coordinata Null non ammessa.");
        int val = (b.getY() - a.getY()) * (c.getX() - b.getX()) - (b.getX() - a.getX()) * (c.getY() - b.getY());
        // collineari
        if (val == 0) return 0;
        // orario o antiorario
        return (val > 0) ? 1 : 2;
    }


    /**
     * Metodo che verifica se un punto è sulla Linea.
     *
     * @param  a                        un oggetto Coordinata che rappresenta il punto
     * @param  b                        un oggetto Coordinata che rappresenta il punto
     * @param  c                        un oggetto Coordinata che rappresenta il punto
     * @return true                     se il punto è sulla Linea, false altrimenti
     * @throws NullPointerException     se almeno una delle Coordinate passate è <code>null
     *
     */
    @Override
    public boolean sullaLinea(Coordinata a, Coordinata b, Coordinata c) throws NullPointerException {
        if (a == null || b == null || c == null) throw new NullPointerException("Coordinata Null non ammessa.");
        return (c.getX() <= Math.max(a.getX(), b.getX()) && c.getX() >= Math.min(a.getX(), b.getX()) &&
                c.getY() <= Math.max(a.getY(), b.getY()) && c.getY() >= Math.min(a.getY(), b.getY()));
    }



    /**
     * Metodi Getter per la classe Linea.
     */

    public Coordinata getC1() {
        return c1;
    }

    public Coordinata getC2() {
        return c2;
    }



}