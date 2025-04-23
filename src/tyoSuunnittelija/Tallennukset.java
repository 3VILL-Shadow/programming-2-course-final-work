package tyoSuunnittelija;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.ohj2.WildChars;

/**
 * Työ suunnittelijan tallennukset
 * @author Ville
 * @version 16 Mar 2025
 */
public class Tallennukset implements Iterable<Tallennus> {
    private static final int MAX_TALLENNUKSIA = 10;
    private int lkm = 0;
    private Tallennus alkiot[] = new Tallennus[MAX_TALLENNUKSIA];
    private boolean muutettu = false;
    private String tiedNimi = "data\\tallennukset";
    
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
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 4;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 5;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 6;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 7;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 8;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 9;
     * tallennukset.lisaa(testiTallennus3); tallennukset.getLkm() === 10;
     * </pre>
     */
    public void lisaa(Tallennus tallennus) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+20); 
        alkiot[lkm] = tallennus;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * @param tallennus tallennus, jonka nimeä muutetaan
     * @param uusiTalNimi tallennuksen uusi nimi
     */
    public void muutaNimi(Tallennus tallennus, String uusiTalNimi) {
        int nro = tallennus.getTunnusNro();
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getTunnusNro() == nro) {
                alkiot[i].muutaNimi(uusiTalNimi);
                muutettu = true;
                return;                
            }
        }
        try {
            lisaa(tallennus);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    
    /** 
     * Poistaa tallennuksen jolla on valittu tunnusnumero  
     * @param id poistettavan tallennuksen tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Tallennukset tallennukset = new Tallennukset(); 
     * Tallennus testiTal1 = new Tallennus(), testiTal2 = new Tallennus(), testiTal3 = new Tallennus(); 
     * testiTal1.rekisteroi(); testiTal2.rekisteroi(); testiTal3.rekisteroi(); 
     * int id1 = testiTal1.getTunnusNro(); 
     * tallennukset.lisaa(testiTal1); tallennukset.lisaa(testiTal2); tallennukset.lisaa(testiTal3); 
     * tallennukset.poista(id1+1) === 1; 
     * tallennukset.annaId(id1+1) === null; tallennukset.getLkm() === 2; 
     * tallennukset.poista(id1) === 1; tallennukset.getLkm() === 1; 
     * tallennukset.poista(id1+3) === 0; tallennukset.getLkm() === 1; 
     * </pre> 
     *  
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    } 

    
    /** 
     * Etsii tallennuksen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return tallennus jolla etsittävä id tai null 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Tallennukset tallennukset = new Tallennukset(); 
     * Tallennus testiTal1 = new Tallennus(), testiTal2 = new Tallennus(), testiTal3 = new Tallennus(); 
     * testiTal1.rekisteroi(); testiTal2.rekisteroi(); testiTal3.rekisteroi(); 
     * int id1 = testiTal1.getTunnusNro(); 
     * tallennukset.lisaa(testiTal1); tallennukset.lisaa(testiTal2); tallennukset.lisaa(testiTal3); 
     * tallennukset.annaId(id1  ) == testiTal1 === true; 
     * tallennukset.annaId(id1+1) == testiTal2 === true; 
     * tallennukset.annaId(id1+2) == testiTal3 === true; 
     * </pre> 
     */ 
    public Tallennus annaId(int id) { 
        for (Tallennus tallennus : this) { 
            if (id == tallennus.getTunnusNro()) return tallennus; 
        } 
        return null; 
    } 


    /** 
     * Etsii tallennuksen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen tallennuksen indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Tallennukset Tallennukset = new Tallennukset(); 
     * Tallennus testiTal1 = new Tallennus(), testiTal2 = new Tallennus(), testiTal3 = new Tallennus(); 
     * testiTal1.rekisteroi(); testiTal2.rekisteroi(); testiTal3.rekisteroi(); 
     * int id1 = testiTal1.getTunnusNro(); 
     * Tallennukset.lisaa(testiTal1); Tallennukset.lisaa(testiTal2); Tallennukset.lisaa(testiTal3); 
     * Tallennukset.etsiId(id1+1) === 1; 
     * Tallennukset.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
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
     * Lukee tallennukset tiedostosta.
     * @param tiedosto joka luetaan
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * 
     *  Tallennukset tallennukset = new Tallennukset();
     *  Tallennus tallennus1 = new Tallennus(), tallennus2 = new Tallennus();
     *  tallennus1.kokeileTallennus();
     *  tallennus2.kokeileTallennus();
     *  String tiedNimi = "data\\testidata\\testiTallennukset";
     *  File ftied = new File(tiedNimi+".dat");
     *  //ftied.delete();
     *  tallennukset.lueTiedostosta(ftied.getName()); #THROWS SailoException
     *  tallennukset.lisaa(tallennus1);
     *  tallennukset.lisaa(tallennus2);
     *  tallennukset.talleta();
     *  //tallennukset = new Tallennukset();            
     *  tallennukset.lueTiedostosta(tiedNimi+".dat"); 
     *  Iterator<Tallennus> i = tallennukset.iterator();
     *  i.next() === tallennus1;
     *  i.next() === tallennus2;
     *  i.hasNext() === false;
     *  tallennukset.lisaa(tallennus2);
     *  tallennukset.talleta();
     *  ftied.delete() === true;
     * </pre>

     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        try ( BufferedReader fi = new BufferedReader(new FileReader(tiedosto)) ) {
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Tallennus tallennus= new Tallennus();
                tallennus.parse(rivi);
                lisaa(tallennus);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + tiedosto + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonNimi());
    }


    /**
     * Tallentaa tallennukset tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        if (!muutettu) return;
        File ftied = new File(getTiedostonNimi());
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
            fo.println(alkiot.length);
            for (Tallennus tal : this) {
                fo.println(tal.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }

    
    /**
     * Palauttaa TyoSuunnittelijan tallennusten lukumäärän
     * @return tallennusten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedNimi + ".dat";
    }

    
    /**
     * @author Ville
     * @version 7 Apr 2025
     *
     */
    public class TallennuksetIterator implements Iterator<Tallennus> {
        private int kohdalla = 0;
        
        
        /**
         * Onko olemassa vielä seuraavaa tallennusta
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }
        
        
        /**
         * Annetaan seuraava tallennus
         * @return seuraava tallennus
         * @throws NoSuchElementException jos seuraavaa alkiota ei enää ole
         */
        @Override
        public Tallennus next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("Ei ole lisää");
            return anna(kohdalla++);
        }
        
        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Ei poisteta");
        }
    }

    /**
     * Palautetaan iteraattori tallennuksesta.
     * @return tallennus iteraattori
     */

    @Override
    public Iterator<Tallennus> iterator() {
        return new TallennuksetIterator();
    }
    
    
    /**
     * Etsitään hakuehdon mukaisesti ja lisätään löytyneet collectioniin joka palautetaan 
     * työSuunnittelija luokalle
     * @param hakuehto ehto jolla haetaan
     * @return colletion löytyneistä
     */
    public Collection<Tallennus> etsi(String hakuehto) { 
        String ehto = "*"; 
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
        Collection<Tallennus> loytyneet = new ArrayList<Tallennus>(); 
        for (Tallennus tal: this) { 
            if (WildChars.onkoSamat(tal.getNimi(), ehto)) loytyneet.add(tal);   
        } 
        
        return loytyneet;
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
