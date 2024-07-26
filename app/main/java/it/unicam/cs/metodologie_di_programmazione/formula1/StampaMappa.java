package it.unicam.cs.metodologie_di_programmazione.formula1;


/**
 * Classe per la Stampa della Mappa di Gioco.
 *
 * @author Davide Cabitza
 *
 */
public class StampaMappa implements MapPrinter {

    private Mappa map;


    /**
     * Costruisce un oggetto StampaBot.
     *
     * @param  map                      un oggetto Mappa rappresentante la Mappa di gioco corrente
     * @throws NullPointerException     se la Mappa passata è <@code> null
     */
    public StampaMappa(Mappa map) throws NullPointerException {
        if (map == null) throw new NullPointerException("Mappa Nulla non ammessa.");
        this.map = map;
    }


    /**
     * Metodo che stampa la Mappa di Gioco corrente.
     *
     */
    @Override
    public void stampaMappa() {
        for (Celle[] linea : this.map.getGriglia()) {
            StringBuilder lineaString = new StringBuilder();
            for (int j = 0; j < linea.length; j++) {
                lineaString.append(linea[j]);
                if (j < linea.length - 1) {
                    lineaString.append(", ");
                }
            }
            System.out.println(lineaString);
        }
        System.out.println("\n\n\n");
    }


}
