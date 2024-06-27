package org.example;

import java.util.List;

/**
 * Interfaccia per la gestione dei punti corrispondenti ad una Coordinata.
 *
 * @author Davide Cabitza
 *
 */
public interface PuntiCoordinata {


    /**
     * Metodo che restituisce una Lista di Coordinate vicine alla Coordinata.
     */
    public List<Coordinata> ottoVicini();



    /**
     * Metodo che verifica la "positività" della Coordinata.
     */
    public boolean isPositive();



}
