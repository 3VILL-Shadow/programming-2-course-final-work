package tyoSuunnittelija;

/**
 * TyöSunnittelija-luokka, joka huolehtii tallennuksista.  Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" tallennuksiin.
 * @author Ville
 * @version 16 Mar 2025
 */
public class TyoSuunnittelija {
    private final Tallennukset tallennukset = new Tallennukset();

    
    /**
     * Palautaa TyöSuuttnittelijan tallennusten määrän
     * @return tallennusten määrä
     */
    public int getTallennuksia() {
        return tallennukset.getLkm();
    }

    
    /**
     * Poistaa tallennuksista ja pelloista ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako tallennusta poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }

    
    
    /**
     * Lisää kerhoon uuden jäsenen
     * @param jasen lisättävä jäsen
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
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
     * tyoSuunnittelija.lisaa(testiTallennus3); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Tallennus jasen) throws SailoException {
        tallennukset.lisaa(jasen);
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
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        tallennukset.lueTiedostosta(nimi);
    }


    /**
     * Tallettaa TyöSuunnittelijan tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        tallennukset.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
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

            System.out.println("============= TyoSuunnittelijan testi =================");

            for (int i = 0; i < tyoSuunnittelija.getTallennuksia(); i++) {
                Tallennus tallennus = tyoSuunnittelija.annaTallennukset(i);
                System.out.println("Tallennus nro: " + i);
                tallennus.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
