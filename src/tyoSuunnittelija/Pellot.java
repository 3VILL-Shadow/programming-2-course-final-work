package tyoSuunnittelija;

import java.util.*;

/**
 * Työsuunnittelijan pellot, joka osaa lisätä uuden pellon
 * @author Ville
 * @version 17 Mar 2025
 *
 */
public class Pellot implements Iterable<Pelto> {
    private String tiedNimi = "";

    /*Taulukko pelloista*/
    private final Collection<Pelto> alkiot = new ArrayList<Pelto>();

    

    /**
     * peltojen alustaminen
     */
    public Pellot() {
        // toistaiseksi ei tarvitse tehdä mitään
    }

    
    /**
     * Lisää uuden pellon tietorakenteeseen ottaa pellon omistukseensa
     * @param Pel Lisättävä pelto
     */
    public void lisaa(Pelto Pel) {
        alkiot.add(Pel);
    }
    
    /**
     * Lukee pellot tiedostosta.  
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedNimi = hakemisto + ".har";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedNimi);
    }
    
    
    /**
     * Tallentaa pellot tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedNimi);
    }

    
    /**
     * Palauttaa työsuunnittelijan peltojen lukumäärän
     * @return peltojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }

    
    /**
     * Iteraattori kaikkien harrastusten läpikäymiseen
     * @return harrastusiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Pellot testiPellot2 = new Pellot();
     *  Pelto testiPelto21 = new Pelto(2); testiPellot2.lisaa(testiPelto21);
     *  Pelto testiPelto11 = new Pelto(1); testiPellot2.lisaa(testiPelto11);
     *  Pelto testiPelto22 = new Pelto(2); testiPellot2.lisaa(testiPelto22);
     *  Pelto testiPelto12 = new Pelto(1); testiPellot2.lisaa(testiPelto12);
     *  Pelto testiPelto23 = new Pelto(2); testiPellot2.lisaa(testiPelto23);
     * 
     *  Iterator<Pelto> i2=testiPellot2.iterator();
     *  i2.next() === testiPelto21;
     *  i2.next() === testiPelto11;
     *  i2.next() === testiPelto22;
     *  i2.next() === testiPelto12;
     *  i2.next() === testiPelto23;
     *  i2.next() === testiPelto12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int tnrot[] = {2,1,2,1,2};
     *  
     *  for ( Pelto pel : testiPellot2) { 
     *    pel.getTallennusNro() === tnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */

    @Override
    public Iterator<Pelto> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Haetaan kaikki jäsen harrastukset
     * @param tunnusnro jäsenen tunnusnumero jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Pellot testiPellot2 = new Pellot();
     *  Pelto testiPelto21 = new Pelto(2); testiPellot2.lisaa(testiPelto21);
     *  Pelto testiPelto11 = new Pelto(1); testiPellot2.lisaa(testiPelto11);
     *  Pelto testiPelto22 = new Pelto(2); testiPellot2.lisaa(testiPelto22);
     *  Pelto testiPelto12 = new Pelto(1); testiPellot2.lisaa(testiPelto12);
     *  Pelto testiPelto23 = new Pelto(2); testiPellot2.lisaa(testiPelto23);
     *  Pelto testiPelto51 = new Pelto(5); testiPellot2.lisaa(testiPelto51);
     *  
     *  List<Pelto> loytyneet;
     *  loytyneet = testiPellot2.annaPellot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = testiPellot2.annaPellot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == testiPelto11 === true;
     *  loytyneet.get(1) == testiPelto12 === true;
     *  loytyneet = testiPellot2.annaPellot(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == testiPelto51 === true;
     * </pre> 
     */
    public List<Pelto> annaPellot(int tunnusnro) {
        List<Pelto> loydetyt = new ArrayList<Pelto>();
        for (Pelto pel : alkiot)
            if (pel.getTallennusNro() == tunnusnro) loydetyt.add(pel);
        return loydetyt;
    }

    
    
    /**
     * Testiohjelma harrastuksille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pellot testiPellot = new Pellot();
        Pelto testiPelto1 = new Pelto();
        testiPelto1.kokeilePelto(2);
        Pelto testiPelto2 = new Pelto();
        testiPelto2.kokeilePelto(1);
        Pelto testiPelto3 = new Pelto();
        testiPelto3.kokeilePelto(2);
        Pelto testiPelto4 = new Pelto();
        testiPelto4.kokeilePelto(2);

        testiPellot.lisaa(testiPelto1);
        testiPellot.lisaa(testiPelto2);
        testiPellot.lisaa(testiPelto3);
        testiPellot.lisaa(testiPelto2);
        testiPellot.lisaa(testiPelto4);

        System.out.println("============= Harrastukset testi =================");

        List<Pelto> testiPellot2 = testiPellot.annaPellot(2);

        for (Pelto pel : testiPellot2) {
            System.out.print(pel.getTallennusNro() + " ");
            pel.tulosta(System.out);
        }

    }

}
