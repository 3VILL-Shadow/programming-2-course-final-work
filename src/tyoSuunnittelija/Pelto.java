package tyoSuunnittelija;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.random.RandomGenerator;

/**
 * Pelto-luokka joka pitää huolta esim omasta tunnuksestaan
 * @author Ville
 * @version 17 Mar 2025
 *
 */
public class Pelto {
    private int tunnusNro;
    private int tallennusNro;
    private String nimi = "";
    private String maanMuok = "";
    private Boolean maanMuokTeht;
    private String vilja = "";
    private Boolean viljaTeht;
    private String lannoitus = "";
    private Boolean lannoitusTeht;
    private String rikkaruohot = "";
    private Boolean rikkaruohotTeht;
    private String korjuu = "";
    private Boolean korjuuTeht;
    
    private static int seuraavaNro = 1;
    
    RandomGenerator random = RandomGenerator.getDefault();
    
    
    /**
     * Ei tarvetta vielä
     */
    public Pelto() {
        // ei tarvetta vielä
    }
    
    /**
     * Alustetaan tietyn tallennuksen pelto
     * @param tallennusNro tallennuksen numero
     */
    public Pelto(int tallennusNro) {
        this.tallennusNro = tallennusNro;
    }
    
    
    /**
     * 
     * @return pellon nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Testi metodi jolla annetaan testiarvot
     * @param nro viite tallennukseen, jonka pellosta kyse
     */
    public void kokeilePelto(int nro) {
        tallennusNro = nro;
        nimi = "Pelto " + random.nextInt(20);
        maanMuok = "Äestys";
        maanMuokTeht = true;
        vilja = "Maissi";
        viljaTeht = true;
        lannoitus = "Kerran";
        lannoitusTeht = true;
        rikkaruohot = "Poisto";
        rikkaruohotTeht = false;
        korjuu = "Puinti";
        korjuuTeht = false;
    }
    
    
    /**
     * Tulostetaan pellon tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(nimi + " \n" + maanMuok + " " + maanMuokTeht + " \n" + vilja + " " + viljaTeht + " \n" +
                    lannoitus + " " + lannoitusTeht + " \n" + rikkaruohot + " " + rikkaruohotTeht + " \n" +
                    korjuu + " " + korjuuTeht);
    }
    
    /**
     * Tulostetaan pellon tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa harrastukselle seuraavan rekisterinumeron.
     * @return harrastuksen uusi tunnus_nro
     * @example
     * <pre name="test">
     *   Pelto testiPelto2 = new Pelto();
     *   testiPelto2.getTunnusNro() === 0;
     *   testiPelto2.rekisteroi();
     *   Pelto testiPelto3 = new Pelto();
     *   testiPelto3.rekisteroi();
     *   int n1 = testiPelto2.getTunnusNro();
     *   int n2 = testiPelto3.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }


    /**
     * Palautetaan harrastuksen oma id
     * @return harrastuksen id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }


    /**
     * Palautetaan mille tallennukselle pelto kuuluu
     * @return tallennuksen id
     */
    public int getTallennusNro() {
        return tallennusNro;
    }

    
    
    /**
     * Testi ohjelma pelloille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelto testiPelto = new Pelto();
        testiPelto.kokeilePelto(1);
        testiPelto.tulosta(System.out);
    }
}
