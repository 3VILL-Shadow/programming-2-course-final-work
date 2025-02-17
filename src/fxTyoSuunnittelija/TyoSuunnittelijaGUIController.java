package fxTyoSuunnittelija;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * @author ville
 * @version 27.1.2025
 *
 */
public class TyoSuunnittelijaGUIController {
      
    
    /**
     * Lisätään uusi tallennus
     */
    @FXML private void handleUusiTallennus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä tallennusta");
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

}