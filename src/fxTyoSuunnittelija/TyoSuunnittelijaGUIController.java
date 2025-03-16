package fxTyoSuunnittelija;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import tyoSuunnittelija.SailoException;
import tyoSuunnittelija.Tallennus;
//import javafx.fxml.Initializable;
import tyoSuunnittelija.TyoSuunnittelija;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * @author ville
 * @version 27.1.2025
 *
 */
public class TyoSuunnittelijaGUIController {
      
    
    @FXML private ScrollPane  paneTallennus;
    @FXML private ListChooser<Tallennus> chooserTallennukset;
    
    /**
     * Lisätään uusi tallennus
     */
    @FXML private void handleUusiTallennus() {
        uusiTallennus();
        //Dialogs.showMessageDialog("Ei osata vielä lisätä tallennusta");
    }

    /**
     * Lisätään uusi pelto
     */
    @FXML private void handleUusiPelto() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä peltoa");
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
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
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
     * Haetaan apua netistä 
     */
    @FXML private void handleApua() {
        avustus();
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
     * Näytetään käyttäjälle tietoja sovelluksesta
     */
    @FXML private void handleTietoja() {
        ModalController.showModal(TyoSuunnittelijaGUIController.class.getResource("TyoSuunnittelijaAboutGUIView.fxml"), "TyöSuunnittelija", null, "");
    }

    
    //===========================================================================================    
    // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
  
    private TyoSuunnittelija tyoSuunnittelija;
    private Tallennus tallennusKohdalla;
    private TextArea areaTallennus = new TextArea();
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan ListChooserin tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös Tallennuslistan kuuntelija 
     */
    protected void alusta() {
        //paneTallennus.setContent(areaTallennus);
        //areaTallennus.setFont(new Font("Courier New", 12));
        //paneTallennus.setFitToHeight(true);
        
        chooserTallennukset.clear();
        chooserTallennukset.addSelectionListener(e -> naytaTallennus());
    }

    
    /**
     * Näyttää listasta valitun jäsenen tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaTallennus() {
        tallennusKohdalla = chooserTallennukset.getSelectedObject();

        if (tallennusKohdalla == null) return;

        areaTallennus.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTallennus)) {
            tallennusKohdalla.tulosta(os);
        }
    }
    
    
    /**
     * Hakee tallennusten tiedot listaan
     * @param tnro tallennuksen numero, joka aktivoidaan haun jälkeen
     */
    protected void hae(int tnro) {
        chooserTallennukset.clear();

        int index = 0;
        for (int i = 0; i < tyoSuunnittelija.getTallennuksia(); i++) {
            Tallennus tallennus = tyoSuunnittelija.annaTallennukset(i);
            if (tallennus.getTunnusNro() == tnro) index = i;
            chooserTallennukset.add(tallennus.getNimi(), tallennus);
        }
        chooserTallennukset.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
    }

    
    /**
     * Luo uuden tallennuksen jota aletaan editoimaan 
     */
    protected void uusiTallennus() {
        Tallennus uusi = new Tallennus();
        uusi.rekisteroi();
        uusi.kokeileTallennus();
        try {
            tyoSuunnittelija.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae(uusi.getTunnusNro());
    }

    
    /**
     * @param tyoSuunnittelija työSuunnittelija jota käytetään tässä käyttöliittymässä
     */
    public void setTyoSuunnittelija(TyoSuunnittelija tyoSuunnittelija) {
        this.tyoSuunnittelija = tyoSuunnittelija;
        naytaTallennus();

    }

}