package tyoSuunnittelija;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Työsuunnittelijan pellot, joka osaa lisätä uuden pellon
 * @author Ville
 * @version 17 Mar 2025
 *
 */
public class Pellot implements Iterable<Pelto> {
    private boolean muutettu = false;
    private String tiedPerusNimi = "data\\pellot";

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
        muutettu = true;
    }
    
    /**
     * Lukee pellot tiedostosta.  
     * @param tiedosto joka luetaan
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Pellot pellot = new Pellot();
     *  Pelto testiPelto21 = new Pelto(); testiPelto21.kokeilePelto(2);
     *  Pelto testiPelto11 = new Pelto(); testiPelto11.kokeilePelto(1);
     *  Pelto testiPelto22 = new Pelto(); testiPelto22.kokeilePelto(2); 
     *  Pelto testiPelto12 = new Pelto(); testiPelto12.kokeilePelto(1); 
     *  Pelto testiPelto23 = new Pelto(); testiPelto23.kokeilePelto(2); 
     *  String tiedNimi = "testiPellot";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  pellot.lueTiedostosta(ftied.getName()); #THROWS SailoException
     *  pellot.lisaa(testiPelto21);
     *  pellot.lisaa(testiPelto11);
     *  pellot.lisaa(testiPelto22);
     *  pellot.lisaa(testiPelto12);
     *  pellot.lisaa(testiPelto23);
     *  pellot.talleta();
     *  pellot = new Pellot();
     *  pellot.lueTiedostosta(ftied.getName());
     *  Iterator<Pelto> i = pellot.iterator();
     *  i.next().toString() === testiPelto21.toString();
     *  i.next().toString() === testiPelto11.toString();
     *  i.next().toString() === testiPelto22.toString();
     *  i.next().toString() === testiPelto12.toString();
     *  i.next().toString() === testiPelto23.toString();
     *  i.hasNext() === false;
     *  pellot.lisaa(testiPelto23);
     *  pellot.talleta();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>

     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
       try (BufferedReader fi = new BufferedReader(new FileReader(tiedosto))) {
           String rivi;
           while ((rivi = fi.readLine()) != null) {
               rivi = rivi.trim();
               if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
               Pelto pelto = new Pelto();
               pelto.parse(rivi);
               lisaa(pelto);
           }
           muutettu = false;
       } catch ( FileNotFoundException e ) {
           throw new SailoException("Tiedosto " + tiedosto + " ei aukea");
       } catch ( IOException e ) {
           throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
       }
    }
    
    
    /**
     * Luetaan tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonNimi());
    }
    
    
    /**
     * Tallentaa pellot tiedostoon.  
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        if (!muutettu) return;
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
            for (Pelto pel : this) {
                fo.println(pel.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }

    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedPerusNimi + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedPerusNimi + ".bak";
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
     * Haetaan kaikki tallennuksen pellot
     * @param tunnusnro tallennuksen tunnusnumero jolle peltoja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin peltoihin
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

        System.out.println("============= Pellot testi =================");

        List<Pelto> testiPellot2 = testiPellot.annaPellot(2);

        for (Pelto pel : testiPellot2) {
            System.out.print(pel.getTallennusNro() + " ");
            pel.tulosta(System.out);
        }

    }

}
