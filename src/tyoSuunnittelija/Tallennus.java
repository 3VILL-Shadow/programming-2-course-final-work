package tyoSuunnittelija;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.random.RandomGenerator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Työ suunnittelijan tallennus, joka pitää huolta numerostaan ja nimestään
 * @author Ville
 * @version 16 Mar 2025
 */
public class Tallennus implements Cloneable {
    private int tunnusNro;
    private String nimi = "";
    
    private static int seuraavaNro    = 1;

    RandomGenerator random = RandomGenerator.getDefault();
    
    /**
     * @return tallennuksen nimi
     * @example
     * <pre name="test">
     *   Tallennus testiTallennus2 = new Tallennus();
     *   testiTallennus2.kokeileTallennus();
     *   testiTallennus2.getNimi() =R= "Tallennus .*";
     * </pre>

     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Testi metodi jolla annetaan testiarvot
     */
    public void kokeileTallennus() {
        nimi = "Tallennus " +  random.nextInt(20);
        
    }
    
    
    /**
     * Asetetaan tallennuksen nimeksi nimi jonka käyttäjä valtisee uutta tallennusta luodessaan
     * @param talNimi tallennuksen nimi, joka annetaan uutta luodessa
     */
    public void asetaNimi(String talNimi) {
        nimi = talNimi; 
    }
    
    
    /**
     * @param uusiTalNimi nimi joksi tallennuksen nimi muutetaan
     */
    public void muutaNimi(String uusiTalNimi) {
        nimi = uusiTalNimi;
    }
    
    
    /**
     * Tulostetaan tallennuksen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", tunnusNro, 2) + " " + nimi);
    }


    /**
     * Tulostetaan tallennuksen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    
    /**
     * Antaa tallennuksille seuraavan rekisterinumeron.
     * @return tallennuksen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Tallennus testiTallennus3 = new Tallennus();
     *   testiTallennus3.getTunnusNro() === 0;
     *   testiTallennus3.rekisteroi();
     *   Tallennus testiTallennus4 = new Tallennus();
     *   testiTallennus4.rekisteroi();
     *   int n1 = testiTallennus3.getTunnusNro();
     *   int n2 = testiTallennus4.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }

    
    
    /**
     * @return tallennuksen id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }

    
    /**
     * Palauttaa tallennuksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return tallennus tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Tallennus tallennus= new Tallennus();
     *   tallennus.parse(" 1 | Tallennus 1");
     *   tallennus.toString() === "1|Tallennus 1"
     * </pre>  
     */
    @Override
    public String toString() {
        return "" + getTunnusNro() + "|" + nimi;
    }

    
    /**
     * Selvitää tallennuksen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta tallennuksen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Tallennus tallennus = new Tallennus();
     *   tallennus.parse("   3  |  Tallennus 3");
     *   tallennus.getTunnusNro() === 3;
     *   tallennus.toString() === "3|Tallennus 3"
     *
     *   tallennus.rekisteroi();
     *   int n = tallennus.getTunnusNro();
     *   tallennus.parse(""+(n+20));
     *   tallennus.rekisteroi();
     *   tallennus.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        nimi = Mjonot.erota(sb, '|', nimi);
       
    }

    
    @Override
    public boolean equals(Object tallennus) {
        if ( tallennus == null ) return false;
        return this.toString().equals(tallennus.toString());
    }


    @Override
    public int hashCode() {
        return tunnusNro;
    }

    
    @Override
    public Tallennus clone() throws CloneNotSupportedException {
        Tallennus uusi;
        uusi = (Tallennus) super.clone();
        return uusi;
    }
    
    
    /**
     * Testiohjelma tallennuksille.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tallennus testiTallennus = new Tallennus(), testiTallennus2 = new Tallennus();
        
        testiTallennus.rekisteroi();
        testiTallennus.tulosta(System.out);
        testiTallennus.kokeileTallennus();
        testiTallennus.tulosta(System.out);
        
        testiTallennus2.rekisteroi();
        testiTallennus2.tulosta(System.out);
        testiTallennus2.kokeileTallennus();
        testiTallennus2.tulosta(System.out);
        
    }
}
