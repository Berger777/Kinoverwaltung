package View;

import Enums.Scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Controller {

    protected static final String gui = "/gui.fxml";
    protected static final String admin = "/admin.fxml";
    protected static final String login = "/login.fxml";
    protected static final String uebersicht = "/ueberischt.fxml";
    protected static final String registrieren = "/registrieren.fxml";

    public void changeSceneTo(ActionEvent event, Scenes ressource){
        try {
            Parent secondStage = FXMLLoader.load(getClass().getResource(ressource.getUri()));
            Scene scene = new Scene(secondStage, 1000, 777);
            Stage stage = new Stage();
            stage.setTitle(ressource.getTitel());
            stage.setScene(scene);
            FXMLLoader.load(getClass().getResource(ressource.getUri()));
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}
