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
     * Tallennukset.getLkm() === 0;
     * Tallennukset.lisaa(testiTallennus3); Tallennukset.getLkm() === 1;
     * Tallennukset.lisaa(testiTallennus4); Tallennukset.getLkm() === 2;
     * Tallennukset.lisaa(aktestiTallennus3u1); Tallennukset.getLkm() === 3;
     * Tallennukset.anna(0) === testiTallennus3;
     * Tallennukset.anna(1) === testiTallennus3;
     * Tallennukset.anna(2) === testiTallennus3;
     * Tallennukset.anna(1) == testiTallennus3 === false;
     * Tallennukset.anna(1) == testiTallennus4 === true;
     * Tallennukset.anna(3) === testiTallennus3; #THROWS IndexOutOfBoundsException 
     * Tallennukset.lisaa(testiTallennus3); Tallennukset.getLkm() === 4;
     * Tallennukset.lisaa(testiTallennus3); Tallennukset.getLkm() === 5;
     * Tallennukset.lisaa(testiTallennus3);  #THROWS SailoException
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
     * Testiohjelma jäsenistölle
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
