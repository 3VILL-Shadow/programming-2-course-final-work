package tyoSuunnittelija;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TyöSunnittelija-luokka, joka huolehtii tallennuksista.  Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" tallennuksiin.
 * @author Ville
 * @version 16 Mar 2025
 * 
 * Testien alustus
 * @example
 * <pre name="testJAVA">
 * #import tyoSuunnittelija.SailoException;
 *  private TyoSuunnittelija tyoSuunnittelija;
 *  private Tallennus testiTal1;
 *  private Tallennus testiTal2;
 *  private int tid1;
 *  private int tid2;
 *  private Pelto testiPelto21;
 *  private Pelto testiPelto11;
 *  private Pelto testiPelto22; 
 *  private Pelto testiPelto12; 
 *  private Pelto testiPelto23;
 *  
 *  public void alustaTyoSuunnittelija() {
 *    tyoSuunnittelija = new TyoSuunnittelija();
 *    testiTal1 = new Tallennus(); testiTal1.kokeileTallennus(); testiTal1.rekisteroi();
 *    testiTal2 = new Tallennus(); testiTal2.kokeileTallennus(); testiTal2.rekisteroi();
 *    tid1 = testiTal1.getTunnusNro();
 *    tid2 = testiTal2.getTunnusNro();
 *    testiPelto21 = new Pelto(tid2); testiPelto21.kokeilePelto(tid2);
 *    testiPelto11 = new Pelto(tid1); testiPelto11.kokeilePelto(tid1);
 *    testiPelto22 = new Pelto(tid2); testiPelto22.kokeilePelto(tid2); 
 *    testiPelto12 = new Pelto(tid1); testiPelto12.kokeilePelto(tid1); 
 *    testiPelto23 = new Pelto(tid2); testiPelto23.kokeilePelto(tid2);
 *    try {
 *    tyoSuunnittelija.lisaa(testiTal1);
 *    tyoSuunnittelija.lisaa(testiTal2);
 *    tyoSuunnittelija.lisaa(testiPelto21);
 *    tyoSuunnittelija.lisaa(testiPelto11);
 *    tyoSuunnittelija.lisaa(testiPelto22);
 *    tyoSuunnittelija.lisaa(testiPelto12);
 *    tyoSuunnittelija.lisaa(testiPelto23);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
*/

public class TyoSuunnittelija {
    private final Tallennukset tallennukset = new Tallennukset();
    private final Pellot pellot = new Pellot();
    
    
    /**
     * Listään uusi pelto tyosuunnittelijaan
     * @param pel lisättävä pelto 
     */
    public void lisaa(Pelto pel) {
        pellot.lisaa(pel);
    }
    
    /**
     * @param pelto pelto jonka nimeä muutetaan
     * @param uusiPelNimi nimi joka muokatessa annetaan pellolle
     */
    public void muutaPelNimi(Pelto pelto, String uusiPelNimi) {
        pellot.muutaNimi(pelto, uusiPelNimi);
    }

    
    /**
     * @param peltoKohdalla pelto jonka tietoja muutetaan
     * @param tiedot tiedot joita on muutettu
     */
    public void muokkaaPelTietoja(Pelto peltoKohdalla, ArrayList<String> tiedot) {
        pellot.muokkaaTiedot(peltoKohdalla, tiedot);
        
    }
    
    
    /**
     * Haetaan kaikki tallennuksen pellot
     * @param tallennus tallennus jolle peltoja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin peltoihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     *  alustaTyoSuunnittelija();
     *  TyoSuunnittelija tyoSuunnittelija = new TyoSuunnittelija();
     *  Tallennus testiTallennus3 = new Tallennus(), testiTallennus4 = new Tallennus(), testiTallennus5 = new Tallennus();
     *  testiTallennus3.rekisteroi(); testiTallennus4.rekisteroi(); testiTallennus5.rekisteroi();
     *  int id1 = testiTallennus3.getTunnusNro();
     *  int id2 = testiTallennus4.getTunnusNro();
     *  Pelto testiPelto11 = new Pelto(id1); tyoSuunnittelija.lisaa(testiPelto11);
     *  Pelto testiPelto12 = new Pelto(id1); tyoSuunnittelija.lisaa(testiPelto12);
     *  Pelto testiPelto21 = new Pelto(id2); tyoSuunnittelija.lisaa(testiPelto21);
     *  Pelto testiPelto22 = new Pelto(id2); tyoSuunnittelija.lisaa(testiPelto22);
     *  Pelto testiPelto23 = new Pelto(id2); tyoSuunnittelija.lisaa(testiPelto23);
     *  
     *  List<Pelto> loytyneet;
     *  loytyneet = tyoSuunnittelija.annaPellot(testiTallennus5);
     *  loytyneet.size() === 0; 
     *  loytyneet = tyoSuunnittelija.annaPellot(testiTallennus3);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == testiPelto11 === true;
     *  loytyneet.get(1) == testiPelto12 === true;
     *  loytyneet = tyoSuunnittelija.annaPellot(testiTallennus4);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == testiPelto21 === true;
     * </pre> 
     */
    public List<Pelto> annaPellot(Tallennus tallennus) {
        return pellot.annaPellot(tallennus.getTunnusNro());
    }
    
    
    /**
     * Palautaa TyöSuuttnittelijan tallennusten määrän
     * @return tallennusten määrä
     */
    public int getTallennuksia() {
        return tallennukset.getLkm();
    }
    
    /**
     * Palauttaa työsuunnittelijan peltojen määrän
     * @return peltojen määrä
     */
    public int getPeltoja() {
        return pellot.getLkm();
    }

    
    /**
     * Poistaa tallennuksista ja pelloista ne joilla on nro. Kesken.
     * @param tallennus viitenumero, jonka mukaan poistetaan
     * @return montako tallennusta poistettiin
     */
    public int poistaTal(Tallennus tallennus) {
        if ( tallennus == null ) return 0;
        int ret = tallennukset.poista(tallennus.getTunnusNro()); 
        pellot.poistaTallennuksenPellot(tallennus.getTunnusNro()); 
        return ret; 

    }

    /** 
     * Poistaa tämän pellon 
     * @param pelto poistettava pelto 
     * @example
     * <pre name="test">
     * #THROWS Exception
     *   alustaTyoSuunnittelija();
     *   tyoSuunnittelija.annaPellot(testiTal1).size() === 2;
     *   tyoSuunnittelija.poistaPel(testiPelto11);
     *   tyoSuunnittelija.annaPellot(testiTal1).size() === 1;
     */ 
    public void poistaPel(Pelto pelto) { 
        pellot.poista(pelto); 
    }

    
    
    /**
     * Lisää työsuunnittelijaan uuden tallennuksen
     * @param tallennus lisättävä tallennus
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * alustaTyoSuunnittelija();
     * TyoSuunnittelija tyoSuunnittelija = new TyoSuunnittelija();
     * Tallennus testiTallennus3 = new Tallennus(), testiTallennus4 = new Tallennus();
     * testiTallennus3.rekisteroi(); testiTallennus4.rekisteroi();
     * tyoSuunnittelija.getTallennuksia() === 0;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 1;
     * tyoSuunnittelija.lisaa(testiTallennus4); tyoSuunnittelija.getTallennuksia() === 2;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 3;
     * tyoSuunnittelija.annaTallennukset(0) === testiTallennus3;
     * tyoSuunnittelija.annaTallennukset(1) === testiTallennus4;
     * tyoSuunnittelija.annaTallennukset(2) === testiTallennus3;
     * tyoSuunnittelija.annaTallennukset(1) == testiTallennus3 === false;
     * tyoSuunnittelija.annaTallennukset(1) == testiTallennus4 === true;
     * tyoSuunnittelija.annaTallennukset(3) === testiTallennus3; #THROWS IndexOutOfBoundsException 
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 4;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 5;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 6;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 7;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 8;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 9;
     * tyoSuunnittelija.lisaa(testiTallennus3); tyoSuunnittelija.getTallennuksia() === 10;
     * </pre>
     */
    public void lisaa(Tallennus tallennus) throws SailoException {
        tallennukset.lisaa(tallennus);
    }
    
    
    /**
     * @param tallennus tallennus jonka nimeä muutetaan
     * @param uusiTalNimi nimi joka muokatessa annetaan tallennukselle
     */
    public void muutaTalNimi(Tallennus tallennus, String uusiTalNimi) {
        tallennukset.muutaNimi(tallennus, uusiTalNimi);
    }
    
    
    /**
     * @return onko tietoja muutettu
     */
    public boolean getMuutettu() {
        boolean muutettu = false;
        if (tallennukset.getMuutettu()) muutettu = true;
        if (pellot.getMuutettu()) muutettu = true;
        return muutettu;
    }
    
    /**
     * palautetaan muutetun arvoksi false
     * @param arvo onko tietoja muutettu 
     */
    public void setMuutettu(boolean arvo) {
        tallennukset.setMuutettu(arvo);
        pellot.setMuutettu(arvo);
    }
    
    
    /**
     * Palauttaa i:n tallennuksen
     * @param i monesko tallennus palautetaan
     * @return viite i:teen tallennukseen
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Tallennus annaTallennukset(int i) throws IndexOutOfBoundsException {
        return tallennukset.anna(i);
    }

    
    /**
     * Lukee TyöSuunnittelijan tiedot tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta() throws SailoException {
        tallennukset.lueTiedostosta();
        pellot.lueTiedostosta();
    }


    /**
     * Tallettaa TyöSuunnittelijan tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        String virhe = "";
        try {
            tallennukset.talleta();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            pellot.talleta();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new SailoException(virhe);

    }
    
    
    /**
     * Siirretään etsiminen tallennus luokalle ja palautetaan sen tulos käyttöliittymälle
     * @param hakuehto ehto jolla haetaan
     * @return löydetyt tallennukset
     * @throws SailoException jos hakeminen ei onnistu
     */
    public Collection<Tallennus> etsiTal(String hakuehto) throws SailoException {
        return tallennukset.etsi(hakuehto); 
    } 

    
    /**
     * Siirretään etsiminen pellot luokalle ja palautetaan sen tulos käyttöliittymälle
     * @param hakuehto ehto jolla haetaan
     * @param valittuTal valittu tallennus josta haetaan peltoja
     * @return löydetyt pellot
     * @throws SailoException jos hakeminen ei onnistu
     */
    public Collection<Pelto> etsiPel(String hakuehto, Tallennus valittuTal) throws SailoException {
        return pellot.etsi(hakuehto, valittuTal.getTunnusNro()); 
    }
    
    
    /**
     * Testiohjelma TyöSuunnittelijasta
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        TyoSuunnittelija tyoSuunnittelija = new TyoSuunnittelija();

        try {
            Tallennus testiTallennus = new Tallennus(), testiTallennus2 = new Tallennus();
            testiTallennus.rekisteroi();
            testiTallennus.kokeileTallennus();
            
            testiTallennus2.rekisteroi();
            testiTallennus2.kokeileTallennus();
            
            tyoSuunnittelija.lisaa(testiTallennus);
            tyoSuunnittelija.lisaa(testiTallennus2);
            
            int id1 = testiTallennus.getTunnusNro();
            int id2 = testiTallennus2.getTunnusNro();
            Pelto testiPelto11 = new Pelto(id1); testiPelto11.kokeilePelto(id1); tyoSuunnittelija.lisaa(testiPelto11);
            Pelto testiPelto12 = new Pelto(id1); testiPelto12.kokeilePelto(id1); tyoSuunnittelija.lisaa(testiPelto12);
            Pelto testiPelto21 = new Pelto(id2); testiPelto21.kokeilePelto(id2); tyoSuunnittelija.lisaa(testiPelto21);
            Pelto testiPelto22 = new Pelto(id2); testiPelto22.kokeilePelto(id2); tyoSuunnittelija.lisaa(testiPelto22);
            Pelto testiPelto23 = new Pelto(id2); testiPelto23.kokeilePelto(id2); tyoSuunnittelija.lisaa(testiPelto23);


            System.out.println("============= TyoSuunnittelijan testi =================");

            for (int i = 0; i < tyoSuunnittelija.getTallennuksia(); i++) {
                Tallennus tallennus = tyoSuunnittelija.annaTallennukset(i);
                System.out.println("Tallennus nro: " + i);
                tallennus.tulosta(System.out);
                List<Pelto> loytyneet = tyoSuunnittelija.annaPellot(tallennus);
                for (Pelto testiPelto : loytyneet)
                    testiPelto.tulosta(System.out);

            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
