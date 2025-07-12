package fxTyoSuunnittelija;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tyoSuunnittelija.Pelto;
import tyoSuunnittelija.SailoException;
import tyoSuunnittelija.Tallennus;
import tyoSuunnittelija.TyoSuunnittelija;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * Lähetetään käyttöliittymälle tiedot.
 * Tehdään käyttöliittymässä tehdyt toimet ja välitetään niistä tieto ja pyydetään tietoa muilta luokilta
 * @author ville
 * @version 23.04.2025
 */
public class TyoSuunnittelijaGUIController implements Initializable {
      
    @FXML private ListChooser<Tallennus> chooserTallennukset;
    @FXML private ListChooser<Pelto> chooserPellot;
    @FXML private TextField fieldMaanMuok;
    @FXML private TextField fieldKylvetty;
    @FXML private TextField fieldLannoitus;
    @FXML private TextField fieldRikat;
    @FXML private TextField fieldKorjuu;
    @FXML private TextArea areaLisaTieto;
    @FXML private CheckBox checkMaanMuok;
    @FXML private CheckBox checkKylvetty;
    @FXML private CheckBox checkLannoitus;
    @FXML private CheckBox checkRikat;
    @FXML private CheckBox checkKorjuu;
    @FXML private ComboBoxChooser<String> haettava;
    @FXML private TextField hakuEhto;
    @FXML private Button buttonLajittele;

    
    /**
     * Lisätään uusi tallennus
     * ja tarkastetaan onko saman niminen tallennus jo olemassa
     * mikäli saman niminen löytyy ilmoitetaan tästä käyttäjälle 
     * ja käyttäjän sulkiessa ilmoitus dialogi annetaan käyttäjän 
     * antaa heti uusi nimi, mutta myös pitäen edellisen yrityksen 
     * syöte jotta käyttäjältä säästetään kirjoittamista
     */
    @FXML private void handleUusiTallennus() {
        String uusi = kokeileNimi("Anna tallennuksen nimi", "", true);
        if (uusi != null) uusiTallennus(uusi);
    }


    /**
     * Lisätään uusi pelto
     * ja tarkastetaan onko saman niminen pelto jo olemassa
     * mikäli saman niminen löytyy ilmoitetaan tästä käyttäjälle 
     * ja käyttäjän sulkiessa ilmoitus dialogi annetaan käyttäjän 
     * antaa heti uusi nimi, mutta myös pitäen edellisen yrityksen 
     * syöte jotta käyttäjältä säästetään kirjoittamista
     */
    @FXML private void handleUusiPelto() {
        String uusi = kokeileNimi("Anna pellon nimi", "", false);
        if (uusi != null )uusiPelto(uusi);
    }
    
    
    /**
     * Muutetaan tallennuksen nimeä
     * ja tarkastetaan onko saman niminen tallennus jo olemassa
     * mikäli saman niminen löytyy ilmoitetaan tästä käyttäjälle 
     * ja käyttäjän sulkiessa ilmoitus dialogi annetaan käyttäjän 
     * antaa heti uusi nimi, mutta myös pitäen edellisen yrityksen 
     * syöte jotta käyttäjältä säästetään kirjoittamista
     */
    @FXML private void handleMuutaTallennusta() {
        String uusi = kokeileNimi("Anna tallennuksen nimi", chooserTallennukset.getSelectedObject().getNimi(), true);
        if (uusi != null) muokkaaTal(uusi);
    }
    
    
    /**
     * Muutetaan pellon nimeä
     * ja tarkastetaan onko saman niminen pelto jo olemassa
     * mikäli saman niminen löytyy ilmoitetaan tästä käyttäjälle 
     * ja käyttäjän sulkiessa ilmoitus dialogi annetaan käyttäjän 
     * antaa heti uusi nimi, mutta myös pitäen edellisen yrityksen 
     * syöte jotta käyttäjältä säästetään kirjoittamista
     */
    @FXML private void handleMuutaPeltoa() {
        String uusi = kokeileNimi("Anna Pellon nimi", chooserPellot.getSelectedObject().getNimi(), false);
        if (uusi != null) muokkaaPel(uusi);
    }
    
    
    /**
     * Muokataan pellon tietoja sen kentissä tai checkboxeissa
     */
    @FXML private void handleMuokkaPelTietoja() {
        muokkaaPelTietoja();
    }
    
    
    /**
     * Poistetaan tallennus
     */
    @FXML private void handlePoistaTallennus() {
        boolean vastaus = Dialogs.showQuestionDialog("Poista tallennus?","Haluatko poistaa tallennuksen: " + tallennusKohdalla.getNimi(), "Kyllä", "Ei");
        if (vastaus) poistaTallennus();
    }
    
    
    /**
     * Poistetaan pelto
     */
    @FXML private void handlePoistaPelto() {
        boolean vastaus = Dialogs.showQuestionDialog("Poista pelto?", "Haluatko poistaa pellon: " + peltoKohdalla.getNimi(), "Kyllä", "Ei");
        if (vastaus) poistaPelto(); 
    }
    
    
    /**
     * haetaan valitun ehdon mukaan
     */
    @FXML private void handleHaku() {
        if (haettava.getSelectedIndex() == 0) haeT(0);
        if (haettava.getSelectedIndex() == 1) haeP(0);
    }
    
    
    /**
     * lajitellaan valitusta listasta
     */
    @FXML private void handleLajittele() {
        if (haettava.getSelectedIndex() == 0) lajittele(haettava.getSelectedIndex()); 
        if (haettava.getSelectedIndex() == 1) lajittele(haettava.getSelectedIndex());
    }
    
    
    /**
     * Tallennetaan tiedot
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        if (tyoSuunnittelija.getMuutettu()) {
            if (voikoSulkea()) tallenna();
        }
        Platform.exit();
        tyoSuunnittelija.setMuutettu(false);
    }
    
    
    /**
     * Haetaan apua netistä 
     */
    @FXML private void handleApua() {
        avustus();
    }

    
    /**
     * Näytetään käyttäjälle tietoja sovelluksesta
     */
    @FXML private void handleTietoja() {
        ModalController.showModal(TyoSuunnittelijaGUIController.class.getResource("TyoSuunnittelijaAboutGUIView.fxml"), "TyöSuunnittelija", null, "");
    }

    
    //===========================================================================================    
    // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
  
    private TyoSuunnittelija tyoSuunnittelija;
    private Tallennus tallennusKohdalla;
    private Pelto peltoKohdalla;
    private TextField muokattavat[];
    private CheckBox checkBoxit[];
    private boolean valinnanVaihto = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/v/2025/kevat/ht/ruotvive");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }

    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        if (tyoSuunnittelija.getMuutettu()) {
            boolean vastaus = Dialogs.showQuestionDialog("Tallentamattomia tietoja!", "Tietoja ei ole tallennettu!\nTallennetaanko?", "Tallenna", "Älä tallenna");
            if (vastaus) tallenna();
        }
        return true;
    }
    
 
    /**
     * Tietojen tallennus
     */
    private String tallenna() {
        try {
            tyoSuunnittelija.talleta();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }

    }
    
    
    /**
     * @return null jos onnistuu, muuten virhe
     */
    protected String lueTiedosto() {
        try {
            tyoSuunnittelija.lueTiedostosta();
            haeT(0);
            haeP(0);
            lajittele(0);
            for (int i = 0; i < tyoSuunnittelija.getTallennuksia(); i++) {   
                chooserTallennukset.setSelectedIndex(i);
                lajittele(1);
            }
            chooserTallennukset.setSelectedIndex(0);
            return null;
        } catch (SailoException e) {
            haeT(0);
            haeP(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
    
    
    /**
     * alustetaan listchooserit ja asetetaan niihin selection listenerit
     * alustetaan hakuehto chooser
     * alustetaan muokattavat kentät
     * alustetaan checkboxit ja lisätään niihin kuuntelijat niiden arvojen tallentamiseksi
     */
    protected void alusta() {
        chooserTallennukset.clear();
        chooserTallennukset.addSelectionListener(_ -> naytaTallennus());
        
        chooserPellot.clear();
        chooserPellot.addSelectionListener(_ -> naytaPelto());
        
        haettava.clear();
        haettava.add("Tallennukset");
        haettava.add("Pellot");
        haettava.getSelectionModel().select(0);
        
        muokattavat = new TextField[] {fieldMaanMuok,  fieldKylvetty, fieldLannoitus, fieldRikat, fieldKorjuu};
        checkBoxit = new CheckBox[] {checkMaanMuok,  checkKylvetty, checkLannoitus, checkRikat, checkKorjuu};
        for (CheckBox cb : checkBoxit) {
            cb.selectedProperty().addListener((_, _, _) ->{ if (!valinnanVaihto) muokkaaPelTietoja();});
        }
        
        areaLisaTieto.clear();
    }
    
   
    /**
     * Näyttää listasta valitun tallennuksen tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaTallennus() {
        tallennusKohdalla = chooserTallennukset.getSelectedObject();
        
        if (tallennusKohdalla == null) return;
        haeP(tallennusKohdalla.getTunnusNro());
        
        peltoKohdalla = chooserPellot.getSelectedObject();
        if (peltoKohdalla == null) {
            for (int i = 0; i < Pelto.getKenttia(); i++) {
                muokattavat[i].setText("");
                checkBoxit[i].setSelected(false);
            }
            areaLisaTieto.setText("");
        }
    }
    
    
    /**
     * Näytä listasta valitun pellon tiedot
     */
    protected void naytaPelto() {
        peltoKohdalla = chooserPellot.getSelectedObject();
        if (peltoKohdalla == null) return;
        
        valinnanVaihto = true; 
        
        muokattavat[0].setText(peltoKohdalla.getMaanMuok());
        muokattavat[1].setText(peltoKohdalla.getVilja());
        muokattavat[2].setText(peltoKohdalla.getLannoitus());
        muokattavat[3].setText(peltoKohdalla.getRikat());
        muokattavat[4].setText(peltoKohdalla.getKorjuu());
        
        areaLisaTieto.setText(peltoKohdalla.getLisaTieto().replace("\\n", "\n"));
        
        checkBoxit[0].setSelected(Boolean.parseBoolean(peltoKohdalla.getMaanMuokTeht()));
        checkBoxit[1].setSelected(Boolean.parseBoolean(peltoKohdalla.getViljaTeht()));
        checkBoxit[2].setSelected(Boolean.parseBoolean(peltoKohdalla.getLannoitusTeht()));
        checkBoxit[3].setSelected(Boolean.parseBoolean(peltoKohdalla.getRikatTeht()));
        checkBoxit[4].setSelected(Boolean.parseBoolean(peltoKohdalla.getKorjuuTeht()));
        
        valinnanVaihto = false; 
    }
    

    /**
     * Hakee tallennusten tiedot listaan
     * @param tnr tallennuksen numero, joka aktivoidaan haun jälkeen
     */
    protected void haeT(int tnr) {
        int tnro = tnr;
        if (tnro <= 0) {
            Tallennus kohdalla = tallennusKohdalla;
            if (kohdalla != null) tnro = kohdalla.getTunnusNro();
        }
        
        String ehto = hakuEhto.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";
        
        chooserTallennukset.clear();

        int index = 0;
        Collection<Tallennus> tallennukset;
        try {
            tallennukset = tyoSuunnittelija.etsiTal(ehto);
            int i = 0;
            for (Tallennus tal : tallennukset) {
                if (tal.getTunnusNro() == tnro) index = i;
                chooserTallennukset.add(tal.getNimi(), tal);
                i++;
            } 
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksen haussa ongelmia" + ex.getMessage());
        }
        
        chooserTallennukset.setSelectedIndex(index); 
    }
    
    
    /**
     * lajitellaan haluttu lista
     * @param i lista jota lajitellaan
     */
    protected void lajittele(int i) {
        if (i == 0) {
            ArrayList<Tallennus> tallennuksetLajiteltu = new ArrayList<>();
            for (int j = 0; j < tyoSuunnittelija.getTallennuksia(); j++) {
                tallennuksetLajiteltu.add(tyoSuunnittelija.annaTallennukset(j));
            }
            tallennuksetLajiteltu.sort((t1, t2) -> t1.getNimi().compareToIgnoreCase(t2.getNimi()));
            chooserTallennukset.clear();
            for (Tallennus tal : tallennuksetLajiteltu) {
                chooserTallennukset.add(tal.getNimi(), tal);
            }
            tyoSuunnittelija.asetaTallennukset(tallennuksetLajiteltu);

        }
        
        if (i == 1) {
            ArrayList<Pelto> pellotLajiteltu = new ArrayList<>(tyoSuunnittelija.getPeltoja());
            pellotLajiteltu.addAll(tyoSuunnittelija.annaPellot(chooserTallennukset.getSelectedObject()));
            pellotLajiteltu.sort((t1, t2) -> t1.getNimi().compareToIgnoreCase(t2.getNimi()));
            chooserPellot.clear();
            for (Pelto pel : pellotLajiteltu) {
                chooserPellot.add(pel.getNimi(), pel);
            }
            tyoSuunnittelija.asetaPellot(chooserTallennukset.getSelectedObject(), pellotLajiteltu);
        }
    }

    
    /**
     * Hakee peltojen tiedot listaan
     * @param pnr tallennuksen numero, joka aktivoidaan haun jälkeen
     */
    protected void haeP(int pnr) {
        int pnro = pnr;
        if (pnro <= 0) {
            Pelto kohdalla = peltoKohdalla;
            if (kohdalla != null) pnro = kohdalla.getTunnusNro();
        }
        
        String ehto = hakuEhto.getText();
        boolean haePeltoa = (haettava.getSelectedIndex() == 1);
        
        if (!haePeltoa) ehto = "*";
        else if (!ehto.contains("*")) ehto = "*" + ehto + "*";
        
        chooserPellot.clear();

        Tallennus valittuTal = chooserTallennukset.getSelectedObject();
        
        if (valittuTal == null) return;
        
        int index = 0;
        Collection<Pelto> pellot;
        try {
            pellot = tyoSuunnittelija.etsiPel(ehto, valittuTal);
            int i = 0;
            for (Pelto pel : pellot) {
                if (pel.getTunnusNro() == pnro) index = i;
                chooserPellot.add(pel.getNimi(), pel);
                i++;
            } 
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksen haussa ongelmia" + ex.getMessage());
        }
        
        chooserPellot.setSelectedIndex(index); 
    }
    
    
    /**
     * Luo uuden tallennuksen ja antaa sille nimen
     * @param talNimi tallennuksen nimi joka annetaan uutta luodessa
     */
    protected void uusiTallennus(String talNimi) {
        Tallennus uusi = new Tallennus();
        uusi.rekisteroi();
        uusi.asetaNimi(talNimi);
        try {
            tyoSuunnittelija.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        haeT(uusi.getTunnusNro());
    }
    
    
    /**
     * Muutetaan tallennuksen nimeä
     * @param uusiTalNimi tallennuksen nimi joka annetaan sitä muuttaessa
     */
    private void muokkaaTal(String uusiTalNimi) {
        if (tallennusKohdalla == null) return;
        try {
            Tallennus tallennus;
            tallennus = tallennusKohdalla.clone();
            if (tallennus == null) return;
            tyoSuunnittelija.muutaTalNimi(tallennus, uusiTalNimi);
            haeT(tallennus.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    
    /*
     * Poistetaan listalta valittu tallennus
     */
    private void poistaTallennus() {
        Tallennus tallennus = tallennusKohdalla;
        if ( tallennus == null ) return;
        tyoSuunnittelija.poistaTal(tallennus);
        int index = chooserTallennukset.getSelectedIndex();
        haeT(tallennus.getTunnusNro());
        chooserTallennukset.setSelectedIndex(index);
    }

    
    /** 
     * Tekee uuden tyhjän pellon ja nataa sille nimen
     * @param pelNimi pellon nimi joka annetaan uutta luodessa
     */ 
    public void uusiPelto(String pelNimi) { 
        if ( tallennusKohdalla == null ) return; 
        
        Pelto pel = new Pelto();  
        pel.rekisteroi();  
        pel.asetaNimi(tallennusKohdalla.getTunnusNro(), pelNimi);
        tyoSuunnittelija.lisaa(pel);  
        haeP(pel.getTunnusNro());          
    } 
    
    
    /**
     * @param uusiPelNimi pellon nimi joka annettu sitä muokatessa
     */
    private void muokkaaPel(String uusiPelNimi) {
        if (tallennusKohdalla == null) return;
        try {
            Pelto pelto;
            pelto = peltoKohdalla.clone();
            if (pelto == null) return;
            tyoSuunnittelija.muutaPelNimi(pelto, uusiPelNimi);
            haeP(pelto.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    /**
     * Muokataan pellon teksikenttien tietoja ja pellon checkboxien tilaa
     * nämä tiedot lähetetän listana pää luokan kautta tietojen käsittely luokille
     */
    private void muokkaaPelTietoja() {
        if (peltoKohdalla == null) return;
        
        ArrayList<String> tiedot = new ArrayList<>();
        tiedot.add(fieldMaanMuok.getText());
        tiedot.add(Boolean.toString(checkMaanMuok.isSelected()));
        tiedot.add(fieldKylvetty.getText());
        tiedot.add(Boolean.toString(checkKylvetty.isSelected()));
        tiedot.add(fieldLannoitus.getText());
        tiedot.add(Boolean.toString(checkLannoitus.isSelected()));
        tiedot.add(fieldRikat.getText());
        tiedot.add(Boolean.toString(checkRikat.isSelected()));
        tiedot.add(fieldKorjuu.getText());
        tiedot.add(Boolean.toString(checkKorjuu.isSelected()));
        tiedot.add(areaLisaTieto.getText());
        
        tyoSuunnittelija.muokkaaPelTietoja(peltoKohdalla, tiedot); 
    }

    
    /*
     * Poistetaan listalta valittu pelto
     */
    private void poistaPelto() {
        Pelto pelto = peltoKohdalla;
        if ( pelto == null ) return;
        tyoSuunnittelija.poistaPel(pelto);
        int index = chooserPellot.getSelectedIndex();
        haeP(pelto.getTunnusNro());
        chooserPellot.setSelectedIndex(index);
    }
    
    
    /**
     * Apumetodi nimen tarkastamiseksi 
     * @param kysymys input dialogin kysymys
     * @param nimi joka toimii oletus nimenä uuttaluodessa ja nimeä muutettaessa olion jo olemassa oleva nimi
     * @param talPel tallennus(true) vai pelto(false)
     * @return uniikki nimi jota ei ole vielä olemassa
     */
    private String kokeileNimi(String kysymys, String nimi, boolean talPel) {
        String kokeiltavaNimi = nimi == null ? "" : nimi;
        while (true) {
            String syote = Dialogs.showInputDialog(kysymys, kokeiltavaNimi);
            if (syote == null) return null;
            
            boolean onOlemassa = false;
            
            if (talPel) {
                for (Tallennus t : chooserTallennukset.getObjects()) {
                    if (t.getNimi().equalsIgnoreCase(syote)) {
                        onOlemassa = true;
                        break;
                    }
                }
            }
            else {
                for (Pelto p : chooserPellot.getObjects()) {
                    if (p.getNimi().equalsIgnoreCase(syote)) {
                        onOlemassa = true;
                        break;
                    }
                }
            }
            if (onOlemassa) {
                Dialogs.showMessageDialog("Nimi '" + syote + "' on jo käytössä");
                kokeiltavaNimi = syote;
            }
            else return syote;
        }
    }
    
    
    /**
     * Tulostaa pellon tiedot
     * Ei enää käytössä, sillä tiedot näkyvät kentissä ja checkboxeissa 
     * @param os tietovirta johon tulostetaan
     * @param pelto tulostettava pelto
     */
    public void tulosta(PrintStream os, final Pelto pelto) {
        os.println("----------------------------------------------");
        pelto.tulosta(os);
        os.println("----------------------------------------------");
    }

    
    /**
     * asetetaan pää luokan sisältö
     * @param tyoSuunnittelija työSuunnittelija jota käytetään tässä käyttöliittymässä
     */
    public void setTyoSuunnittelija(TyoSuunnittelija tyoSuunnittelija) {
        this.tyoSuunnittelija = tyoSuunnittelija;
        naytaTallennus();
    }

}