package fxTyoSuunnittelija;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
 * @author ville
 * @version 27.1.2025
 *
 */
public class TyoSuunnittelijaGUIController implements Initializable {
      
    @FXML private ListChooser<Tallennus> chooserTallennukset;
    @FXML private ListChooser<Pelto> chooserPellot;
    @FXML private TextField fieldMaanMuok;
    @FXML private TextField fieldKylvetty;
    @FXML private TextField fieldLannoitus;
    @FXML private TextField fieldRikat;
    @FXML private TextField fieldKorjuu;
    @FXML private CheckBox checkMaanMuok;
    @FXML private CheckBox checkKylvetty;
    @FXML private CheckBox checkLannoitus;
    @FXML private CheckBox checkRikat;
    @FXML private CheckBox checkKorjuu;
    @FXML private ComboBoxChooser<String> haettava;
    @FXML private TextField hakuEhto;

    
    /**
     * Lisätään uusi tallennus
     */
    @FXML private void handleUusiTallennus() {
        String talNimi = Dialogs.showInputDialog("Anna Tallennuksen nimi", "");
        if (talNimi == null) return;
        uusiTallennus(talNimi);
        //Dialogs.showMessageDialog("Ei osata vielä lisätä tallennusta");
    }

    /**
     * Lisätään uusi pelto
     */
    @FXML private void handleUusiPelto() {
        String pelNimi = Dialogs.showInputDialog("Anna Pellon nimi", "");
        if (pelNimi == null) return;
        uusiPelto(pelNimi);
        //Dialogs.showMessageDialog("Ei osata vielä lisätä peltoa");
    }
    
    
    /**
     * Muutetaan tallennuksen nimeä
     */
    @FXML private void handleMuutaTallennusta() {
        String uusiTalNimi = Dialogs.showInputDialog("Muuta Tallennuksen nimeä", chooserTallennukset.getSelectedObject().getNimi());
        if (uusiTalNimi == null) return;
        muokkaaTal(uusiTalNimi);
        //Dialogs.showMessageDialog("Ei osata vielä muuttaa tallennuksen nimeä");
    }
    
    
    /**
     * Muutetaan pellon nimeä
     */
    @FXML private void handleMuutaPeltoa() {
        String uusiPelNimi = Dialogs.showInputDialog("Muuta Pellon nimeä", chooserPellot.getSelectedObject().getNimi());
        if (uusiPelNimi == null) return;
        muokkaaPel(uusiPelNimi);
        //Dialogs.showMessageDialog("Ei osata vielä muuttaa pellon nimeä");
    }
    
    
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
     * Tallennetaan tiedot
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna(); 
        Platform.exit();
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
        tallenna();
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
            cb.selectedProperty().addListener((_, _, _) -> muokkaaPelTietoja());
        }

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
            }
        }
    }
    
    /**
     * Näytä listasta valitun pellon tiedot
     */
    protected void naytaPelto() {
        peltoKohdalla = chooserPellot.getSelectedObject();
        
        if (peltoKohdalla == null) return;
        
        muokattavat[0].setText(peltoKohdalla.getMaanMuok());
        muokattavat[1].setText(peltoKohdalla.getVilja());
        muokattavat[2].setText(peltoKohdalla.getLannoitus());
        muokattavat[3].setText(peltoKohdalla.getRikat());
        muokattavat[4].setText(peltoKohdalla.getKorjuu());
        
        checkBoxit[0].setSelected(Boolean.parseBoolean(peltoKohdalla.getMaanMuokTeht()));
        checkBoxit[1].setSelected(Boolean.parseBoolean(peltoKohdalla.getViljaTeht()));
        checkBoxit[2].setSelected(Boolean.parseBoolean(peltoKohdalla.getLannoitusTeht()));
        checkBoxit[3].setSelected(Boolean.parseBoolean(peltoKohdalla.getRikatTeht()));
        checkBoxit[4].setSelected(Boolean.parseBoolean(peltoKohdalla.getKorjuuTeht()));
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
     * Hakee tallennusten tiedot listaan
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
     * Luo uuden tallennuksen jota aletaan editoimaan 
     * @param talNimi tallennuksen nimi joka annetaan uutta luodessa
     */
    protected void uusiTallennus(String talNimi) {
        for (Tallennus t : chooserTallennukset.getObjects()) {
            if (t.getNimi().equalsIgnoreCase(talNimi)) {
                Dialogs.showMessageDialog("Nimi '" + talNimi + "' on jo käytössä.");
                return;
            }
        }
        
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
     * Tekee uuden tyhjän pellon editointia varten 
     * @param pelNimi pellon nimi joka annetaan uutta luodessa
     */ 
    public void uusiPelto(String pelNimi) { 
        if ( tallennusKohdalla == null ) return; 
        
        for (Pelto p : chooserPellot.getObjects()) {
            if (p.getNimi().equalsIgnoreCase(pelNimi)) {
                Dialogs.showMessageDialog("Nimi '" + pelNimi + "' on jo käytössä.");
                return;
            }
        }
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
     * Tulostaa pellon tiedot
     * @param os tietovirta johon tulostetaan
     * @param pelto tulostettava pelto
     */
    public void tulosta(PrintStream os, final Pelto pelto) {
        os.println("----------------------------------------------");
        pelto.tulosta(os);
        os.println("----------------------------------------------");
    }

    
    
    /**
     * @param tyoSuunnittelija työSuunnittelija jota käytetään tässä käyttöliittymässä
     */
    public void setTyoSuunnittelija(TyoSuunnittelija tyoSuunnittelija) {
        this.tyoSuunnittelija = tyoSuunnittelija;
        naytaTallennus();
    }

}