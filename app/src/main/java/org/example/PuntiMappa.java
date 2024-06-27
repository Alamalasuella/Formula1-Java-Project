package org.example;
import java.util.List;

/**
 * Interfaccia per una Mappa generica
 */
public interface PuntiMappa {


    /**
     * Metodo che restituisce la Cella associata alla Coordinata passata.
     */
    public Celle getCellaFromCoordinata(Coordinata coordinata);


    /**
     * Metodo che associa una determinata Cella alla Coordinata passata.
     */
    public void setCella(Coordinata c, Celle cella);



    /**
     * Metodo che verifica se la Coordinata passata è valida o meno.
     */
    public boolean isNotValid(Coordinata c);



    /**
     * Metodo che verifica se a una data Coordinata è associata una certa Cella.
     */
    public boolean isPosition(Coordinata c, Celle cella);



    /**
     * Metodo che restituisce l'insieme di tutte le Coordinate
     * rappresentanti la linea di partenza della pista.
     */
    public List<Coordinata> getStart();


    /**
     * Metodo che restituisce l'insieme di tutte le Coordinate
     * rappresentanti la linea di arrivo della pista.
     */
    public List<Coordinata> getFinish();


    /**
     * Metodo che restituisce l'insieme di tutte le Coordinate
     * rappresentanti la posizione di un Player nella Mappa.
     */
    public List<Coordinata> getPlayers();

}
