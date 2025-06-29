package tyoSuunnittelija;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.random.RandomGenerator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Pelto-luokka joka pitää huolta esim omasta tunnuksestaan
 * @author Ville
 * @version 23.04.2025
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
    private String lisaTieto ="";
    
    private static int seuraavaNro = 1;
    
    /*Käytössä vain testatessa pääohjelmalla ja esimerkki luonnissa, ennen oikeaa nimen antamista*/
    RandomGenerator random = RandomGenerator.getDefault();
    
    
    /**
     * Ei tarvetta
     */
    public Pelto() {
        // ei tarvetta
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
     * @return onko maanmuokkaus tehty
     */
    public String getMaanMuokTeht() {
        return maanMuokTeht;
    }
    
    
    /**
     * @return kylvetty vilja
     */
    public String getVilja() {
        return vilja;
    }
    
    
    /**
     * @return onko kylvö tehty
     */
    public String getViljaTeht() {
        return viljaTeht;
    }
    
    
    /**
     * @return lannoitus
     */
    public String getLannoitus() {
        return lannoitus;
    }
    
    
    /**
     * @return onko lannoitus tehty
     */
    public String getLannoitusTeht() {
        return lannoitusTeht;
    }
    
    
    /**
     * @return rikkaruohot
     */
    public String getRikat() {
        return rikkaruohot;
    }

    
    /**
     * @return onko rikkaruohojen poisto tehty
     */
    public String getRikatTeht() {
        return rikkaruohotTeht;
    }
    
    /**
     * @return korjuu
     */
    public String getKorjuu() {
        return korjuu;
    }

    
    /**
     * @return onko maanmuokkaus tehty
     */
    public String getKorjuuTeht() {
        return korjuuTeht;
    }
    
    
    /**
     * @return lisatiedot
     */
    public String getLisaTieto() {
        return lisaTieto;
    }
    
    /**
     * @return kenttien maara
     */
    public static int getKenttia() {
        return 5;
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
        lisaTieto ="Testi testi";
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
     * Vaihdetaan pellon nimi nimellä, jonka käyttäjä on käyttöliittymässä antanut
     * @param uusiPelNimi nimi joksi pellon nimi muutetaan
     */
    public void muutaNimi(String uusiPelNimi) {
        nimi = uusiPelNimi;
    }
    
    
    /**
     * asetetaan maanmuokkaus muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto kentän sisältö
     */
    public void asetaMaanMuok(String tieto) {
        maanMuok = tieto;
    }
    
    
     /**
      * Asetetaan maanMuokTeht muuttujalle arvo joka on tuotu käyttöliittymästä
      * @param tieto checkboxin tila
      */
    public void asetaMaanMuokTeht(String tieto) {
        maanMuokTeht = tieto;
    }
    
    /**
     * asetetaan vilja muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto kentän sisältö
     */
    public void asetaVilja(String tieto) {
        vilja = tieto;
    }
    
    
    /**
     * Asetetaan viljaTeht muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto checkboxin tila
     */
   public void asetaViljaTeht(String tieto) {
       viljaTeht = tieto;
   }
    
   
    /**
     * asetetaan lannoitus muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto kentän sisältö
     */
    public void asetaLannoitus(String tieto) {
        lannoitus= tieto;
    }
    
    
    /**
     * Asetetaan lannoitusTeht muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto checkboxin tila
     */
   public void asetaLannoitusTeht(String tieto) {
       lannoitusTeht = tieto;
   }
   
    
    /**
     * asetetaan rikkaruohot muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto kentän sisältö
     */
    public void asetaRikat(String tieto) {
        rikkaruohot = tieto;
    }
    
    
     /**
     * Asetetaan rikkaruohotTeht muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto checkboxin tila
     */
   public void asetaRikatTeht(String tieto) {
       rikkaruohotTeht = tieto;
   }
   
        
    /**
     * asetetaan korjuu muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto kentän sisältö
     */
    public void asetaKorjuu(String tieto) {
        korjuu = tieto;
    }
    
    
    /**
     * Asetetaan korjuuTeht muuttujalle arvo joka on tuotu käyttöliittymästä
     * @param tieto checkboxin tila
     */
   public void asetaKorjuuTeht(String tieto) {
       korjuuTeht = tieto;
   }
   
   /**
    * Asetetaan lisaTieto muuttujalle arvo joka on tuotu käyttöliittymästä
    * @param tieto kentän sisältö
    */
   public void asetaLisaTiedot(String tieto) {
       lisaTieto = tieto;
   }
    
    
    /**
     * Tulostetaan pellon tiedot ei enää käytössä sillä tiedot näkyvät käyttöliittymän kentissä
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(nimi + " \n" + maanMuok + " " + maanMuokTeht + " \n" + vilja + " " + viljaTeht + " \n" +
                    lannoitus + " " + lannoitusTeht + " \n" + rikkaruohot + " " + rikkaruohotTeht + " \n" +
                    korjuu + " " + korjuuTeht + " \n" + lisaTieto);
    }
    
    /**
     * Tulostetaan pellon tiedot ei enää käytössä sillä tiedot näkyvät käyttöliittymän kentissä
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa pellolle seuraavan rekisterinumeron.
     * @return pellon uusi tunnusNro
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
     * Palautetaan pellon oma id
     * @return pellon id
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
     * Palauttaa pellon tiedot merkkijonona jotta käyttöliittymässä tehdyt 
     * tietojen syötöt voidaan tallentaa tiedostoon
     * @return pelto tolppaeroteltuna merkkijonona 
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
                + "|" + korjuuTeht + "|" + lisaTieto;
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
        lisaTieto = Mjonot.erota(sb, '|', lisaTieto);
    }
    

    /**
     * tehdään pellosta klooni sitä muokatessa, jotta saadaan pidettyä alkuperäiset tiedot
     * mikäli muokkauksia ei tallenneta
     */
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
