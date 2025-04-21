package tyoSuunnittelija;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.random.RandomGenerator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Pelto-luokka joka pitää huolta esim omasta tunnuksestaan
 * @author Ville
 * @version 17 Mar 2025
 *
 */
public class Pelto implements Cloneable {
    private int tunnusNro;
    private int tallennusNro;
    private String nimi = "";
    private String maanMuok = "";
    private String maanMuokTeht = "";
    private String vilja = "";
    private String viljaTeht = "";
    private String lannoitus = "";
    private String lannoitusTeht = "";
    private String rikkaruohot = "";
    private String rikkaruohotTeht = "";
    private String korjuu = "";
    private String korjuuTeht = "";
    
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
     * @return pellon nimi
     */
    public String getNimi() {
        return nimi;
    }

    
    /**
     * @return maanmuokkaus
     */
    public String getMaanMuok() {
        return maanMuok;
    }
    
    
    /**
     * @return kylvetty vilja
     */
    public String getVilja() {
        return vilja;
    }
    
    
    /**
     * @return lannoitus
     */
    public String getLannoitus() {
        return lannoitus;
    }
    
    
    /**
     * @return rikkaruohot
     */
    public String getRikat() {
        return rikkaruohot;
    }
    
    
    /**
     * @return korjuu
     */
    public String getKorjuu() {
        return korjuu;
    }

    
    /**
     * Testi metodi jolla annetaan testiarvot
     * @param nro viite tallennukseen, jonka pellosta kyse
     */
    public void kokeilePelto(int nro) {
        tallennusNro = nro;
        nimi = "Pelto " + random.nextInt(20);
        maanMuok = "Äestys";
        maanMuokTeht = "true";
        vilja = "Maissi";
        viljaTeht = "true";
        lannoitus = "Kerran";
        lannoitusTeht = "true";
        rikkaruohot = "Poisto";
        rikkaruohotTeht = "false";
        korjuu = "Puinti";
        korjuuTeht = "false";
    }
    
    
    /**
     * asetetaan pellolle tallennuksen numero, jotta pelto pysyy omassa tallennuksessaan ja asetetaan nimeksi
     * nimi, joka annetaan uutta luodessa
     * @param nro tallennuksen numero, johon pelto kuuluu
     * @param pelNimi pellon nimi joka annetaan uutta luodessa
     */
    public void asetaNimi(int nro, String pelNimi) {
        tallennusNro = nro;
        nimi = pelNimi;
    }
    
    /**
     * @param uusiPelNimi nimi joksi pellon nimi muutetaan
     */
    public void muutaNimi(String uusiPelNimi) {
        nimi = uusiPelNimi;
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
     * Asettaa tunnusnumeron ja samalla varmistaa että seuraava numero on aina suuermpi kuin edellinen
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    /**
     * Palauttaa harrastuksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return harrastus tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Pelto pelto = new Pelto();
     *   pelto.parse("   2   |  10  |   Pelto 20  | Äestys | false | Maissi | false | Kerran | false | Poisto | false | Puinti | false |");
     *   pelto.toString() === "2|10|Pelto 20|Äestys|false|Maissi|false|Kerran|false|Poisto|false|Puinti|false";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getTunnusNro() + "|" + tallennusNro + "|" + nimi + "|" + maanMuok + "|" + maanMuokTeht + "|" + vilja + "|" +
                viljaTeht + "|" +lannoitus + "|" + lannoitusTeht + "|" + rikkaruohot + "|" + rikkaruohotTeht + "|" + korjuu
                + "|" + korjuuTeht;
    }
    
    
    /**
     * Selvittää pellon tiedot | erotellusta merkkijonosta
     * @param rivi josta pellon tiedot otetaan
     * @example
     * <pre name="test">
     * Pelto pelto = new Pelto();
     * pelto.parse("   2   |  10  |   Pelto 20  | Äestys | false | Maissi | false | Kerran | false | Poisto | false | Puinti | false |");
     * pelto.getTallennusNro() === 10;
     * pelto.toString() === "2|10|Pelto 20|Äestys|false|Maissi|false|Kerran|false|Poisto|false|Puinti|false";
     * pelto.rekisteroi();
     * int n = pelto.getTunnusNro();
     * pelto.parse(""+(n+20));
     * pelto.rekisteroi();
     * pelto.getTunnusNro() === n+20+1;
     * pelto.toString() === "" + (n+20+1) + "|10|Pelto 20|Äestys|false|Maissi|false|Kerran|false|Poisto|false|Puinti|false";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb,  '|', getTunnusNro()));
        tallennusNro = Mjonot.erota(sb, '|', tallennusNro);
        nimi = Mjonot.erota(sb, '|', nimi);
        maanMuok = Mjonot.erota(sb, '|', maanMuok);
        maanMuokTeht = Mjonot.erota(sb, '|', maanMuokTeht);
        vilja = Mjonot.erota(sb, '|', vilja);
        viljaTeht = Mjonot.erota(sb, '|', viljaTeht);
        lannoitus = Mjonot.erota(sb, '|', lannoitus);
        lannoitusTeht = Mjonot.erota(sb, '|', lannoitusTeht);
        rikkaruohot = Mjonot.erota(sb, '|', rikkaruohot);
        rikkaruohotTeht = Mjonot.erota(sb, '|', rikkaruohotTeht);
        korjuu = Mjonot.erota(sb, '|', korjuu);
        korjuuTeht = Mjonot.erota(sb, '|', korjuuTeht);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    
    @Override
    public int hashCode() {
        return tunnusNro;
    }
    
    
    @Override
    public Pelto clone() throws CloneNotSupportedException {
        Pelto uusi;
        uusi = (Pelto) super.clone();
        return uusi;
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
