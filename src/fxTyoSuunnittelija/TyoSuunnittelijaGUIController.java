package fxTyoSuunnittelija;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
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
import java.util.List;
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
    @FXML private TextArea areaPelto;

    
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
        Dialogs.showMessageDialog("Ei osata vielä muuttaa tallennuksen nimeä");
    }
    
    
    /**
     * Muutetaan pellon nimeä
     */
    @FXML private void handleMuutaPeltoa() {
        Dialogs.showMessageDialog("Ei osata vielä muuttaa pellon nimeä");
    }
    
    
    /**
     * Poistetaan tallennus
     */
    @FXML private void handlePoistaTallennus() {
        boolean vastaus = Dialogs.showQuestionDialog("Poista tallennus?",
                "EI VIELÄ TOTEUTETTU! \nHaluatko poistaa tallennuksen: TALLENNUKSEN_NIMI", "Kyllä", "Ei");
        if (vastaus); //poistaTallennus(); tallennuksen poistamista ei vielä osata toteuttaa
        }
    
    
    /**
     * Poistetaan pelto
     */
    @FXML private void handlePoistaPelto() {
        boolean vastaus = Dialogs.showQuestionDialog("Poista pelto?",
                "EI VIELÄ TOTEUTETTU! \nHaluatko poistaa pellon: PELLON_NIMI", "Kyllä", "Ei");
        if (vastaus); //poistaPelto(); pellon poistamista ei vielä osata toteuttaa
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
//        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
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
     * 
     */
    protected void alusta() {
        chooserTallennukset.clear();
        chooserTallennukset.addSelectionListener(_ -> naytaTallennus());
        
        chooserPellot.clear();
        chooserPellot.addSelectionListener(_ -> naytaPelto());
    }

    
    /**
     * Näyttää listasta valitun tallennuksen tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaTallennus() {
        tallennusKohdalla = chooserTallennukset.getSelectedObject();
        
        if (tallennusKohdalla == null) return;
        areaPelto.setText("");
        haeP(tallennusKohdalla.getTunnusNro());
    }
    
    /**
     * Näytä listasta valitun pellon tiedot
     */
    protected void naytaPelto() {
        peltoKohdalla = chooserPellot.getSelectedObject();
        
        if (peltoKohdalla == null) return;
        
        areaPelto.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPelto)) {
            tulosta(os, peltoKohdalla);  
        }
        
    }
    
    
    /**
     * Hakee tallennusten tiedot listaan
     * @param tnro tallennuksen numero, joka aktivoidaan haun jälkeen
     */
    protected void haeT(int tnro) {
        chooserTallennukset.clear();

        int index = 0;
        for (int i = 0; i < tyoSuunnittelija.getTallennuksia(); i++) {
            Tallennus tallennus = tyoSuunnittelija.annaTallennukset(i);
            if (tallennus.getTunnusNro() == tnro) index = i;
            chooserTallennukset.add(tallennus.getNimi(), tallennus);
        }
        chooserTallennukset.setSelectedIndex(index); 
    }

    
    /**
     * Hakee tallennusten tiedot listaan
     * @param pnro tallennuksen numero, joka aktivoidaan haun jälkeen
     */
    protected void haeP(int pnro) {
        chooserPellot.clear();
        Tallennus valittuTal = chooserTallennukset.getSelectedObject();
        
        if (valittuTal == null) return;
        
        List<Pelto> pellot = tyoSuunnittelija.annaPellot(valittuTal);
        
        int index = 0;
        for (int i = 0; i < pellot.size(); i++) {
            Pelto pelto = pellot.get(i);
            if (pelto.getTunnusNro() == pnro) index = i;
            chooserPellot.add(pelto.getNimi(), pelto);
        }
        chooserPellot.setSelectedIndex(index); 
    }
    
    
    /**
     * Luo uuden tallennuksen jota aletaan editoimaan 
     * @param talNimi tallennuksen nimi joka annetaan uutta luodessa
     */
    protected void uusiTallennus(String talNimi) {
        Tallennus uusi = new Tallennus();
        uusi.rekisteroi();
//        uusi.kokeileTallennus();
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
     * Tekee uuden tyhjän pellon editointia varten 
     * @param pelNimi pellon nimi joka annetaan uutta luodessa
     */ 
    public void uusiPelto(String pelNimi) { 
        if ( tallennusKohdalla == null ) return;  
        Pelto pel = new Pelto();  
        pel.rekisteroi();  
//        pel.kokeilePelto(tallennusKohdalla.getTunnusNro());  
        pel.asetaNimi(tallennusKohdalla.getTunnusNro(), pelNimi);
        tyoSuunnittelija.lisaa(pel);  
        haeP(pel.getTunnusNro());          
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