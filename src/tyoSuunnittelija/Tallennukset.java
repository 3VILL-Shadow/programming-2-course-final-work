package tyoSuunnittelija;

/**
 * Työ suunnittelijan tallennukset
 * @author Ville
 * @version 16 Mar 2025
 */
public class Tallennukset {
    private static final int MAX_TALLENNUKSIA = 10;
    private int lkm = 0;
    private String tiedNimi = "";
    private Tallennus alkiot[] = new Tallennus[MAX_TALLENNUKSIA];
    
    /**
     * Oletus muodostaja
     */
    public Tallennukset() {
        // Oletus muodostajalle riittää atribuuttien alustus
    }
    
    
    /**
     * Lisää uuden tallennuksen tietorakenteeseen.  Ottaa tallennuksen omistukseensa.
     * @param tallennus lisätäävän tallennukseen viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Tallennukset tallennukset = new Tallennukset();
     * Tallennus testiTallennus3 = new Tallennus(), testiTallennus4 = new Tallennus();
     * tallennukset.getLkm() === 0;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 1;
     * tallennukset.lisaa(testiTallennus4); tallennukset.getLkm() === 2;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 3;
     * tallennukset.anna(0) === testiTallennus3;
     * tallennukset.anna(1) === testiTallennus4;
     * tallennukset.anna(2) === testiTallennus3;
     * tallennukset.anna(1) == testiTallennus3 === false;
     * tallennukset.anna(1) == testiTallennus4 === true;
     * tallennukset.anna(3) === testiTallennus3; #THROWS IndexOutOfBoundsException 
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 4;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 5;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 6;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 7;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 8;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 9;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 10;
     * tallennukset.lisaa(testiTallennus3); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Tallennus tallennus) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = tallennus;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen tallennukseen.
     * @param i monennenko tallennuksen viite halutaan
     * @return viite tallennukseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Tallennus anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }


    
    /**
     * Lukee tallennukset tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedNimi = hakemisto + "/tallennukset.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedNimi);
    }


    /**
     * Tallentaa tallennukset tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedNimi);
    }

    
    /**
     * Palauttaa TyoSuunnittelijan tallennusten lukumäärän
     * @return tallennusten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    
    
    /**
     * Testiohjelma tallennuksille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tallennukset tallennukset = new Tallennukset();

        Tallennus testiTallennus = new Tallennus(), testiTallennus2 = new Tallennus();
        testiTallennus.rekisteroi();
        testiTallennus.kokeileTallennus();
        
        testiTallennus2.rekisteroi();
        testiTallennus2.kokeileTallennus();

        try {
            tallennukset.lisaa(testiTallennus);
            tallennukset.lisaa(testiTallennus2);

            System.out.println("============= Tallennukset testi =================");

            for (int i = 0; i < tallennukset.getLkm(); i++) {
                Tallennus tallennus = tallennukset.anna(i);
                System.out.println("Tallennus nro: " + i);
                tallennus.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
