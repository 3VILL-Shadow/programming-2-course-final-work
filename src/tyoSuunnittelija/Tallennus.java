package tyoSuunnittelija;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Ville
 * @version 16 Mar 2025
 *
 */
public class Tallennus {
    private int tunnusNro;
    private String nimi = "";
    
    private static int seuraavaNro    = 1;
    
    
    /**
     * @return tallennuksen nimi
     * @example
     * <pre name="test">
     *   Tallennus testiTallennus2 = new Tallennus();
     *   testiTallennus2.kokeileTallennus();
     *   testiTallennus2.getNimi() =R= "Tallennus 1";
     * </pre>

     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Testi metodi jolla annetaan testiarvot
     */
    public void kokeileTallennus() {
        nimi = "Tallennus 1";
        
    }
    
    
    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + " " + nimi);
    }


    /**
     * Tulostetaan henkilön tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    
    /**
     * Antaa jäsenelle seuraavan rekisterinumeron.
     * @return jäsenen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Tallennus testiTallennus2 = new Tallennus();
     *   testiTallennus2.getTunnusNro() === 0;
     *   testiTallennus2.rekisteroi();
     *   Tallennus testiTallennus3 = new Tallennus();
     *   testiTallennus3.rekisteroi();
     *   int n1 = testiTallennus2.getTunnusNro();
     *   int n2 = testiTallennus3.getTunnusNro();
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
     * Testiohjelma jäsenelle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tallennus testiTallennus = new Tallennus();
        
        testiTallennus.rekisteroi();
        testiTallennus.tulosta(System.out);
        testiTallennus.kokeileTallennus();
        testiTallennus.tulosta(System.out);
        
    }
}
