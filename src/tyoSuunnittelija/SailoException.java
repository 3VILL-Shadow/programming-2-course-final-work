package tyoSuunnittelija;


/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author Ville
 * @version 23.04.2025
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;


    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}