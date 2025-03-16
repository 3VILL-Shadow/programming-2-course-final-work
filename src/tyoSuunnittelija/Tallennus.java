package tyoSuunnittelija;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.random.RandomGenerator;

/**
 * Työ suunnittelijan tallennus, joka pitää huolta numerostaan ja nimestään
 * @author Ville
 * @version 16 Mar 2025
 */
public class Tallennus {
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
