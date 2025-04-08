package fxTyoSuunnittelija;

import javafx.application.Application;
import javafx.stage.Stage;
import tyoSuunnittelija.TyoSuunnittelija;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;



/**
 * @author ville
 * @version 27.1.2025
 *
 */
public class TyoSuunnittelijaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TyoSuunnittelijaGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final TyoSuunnittelijaGUIController tyosuunnittelijaCtrl = (TyoSuunnittelijaGUIController) ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tyosuunnittelija.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("TyoSuunnittelija");
            
            primaryStage.setOnCloseRequest((event) -> {
                if (!tyosuunnittelijaCtrl.voikoSulkea() ) event.consume();
            });
            
            
            TyoSuunnittelija tyoSuunnittelija = new TyoSuunnittelija();  
            tyosuunnittelijaCtrl.setTyoSuunnittelija(tyoSuunnittelija);
            tyosuunnittelijaCtrl.lueTiedosto();

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei kaytossa
     */
    public static void main(String[] args) {
        launch(args);
    }
}