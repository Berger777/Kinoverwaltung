package View;

import Entities.Nutzer;
import Exceptions.NutzerNotFoundException;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    public TextField vornameTextfield;
    public TextField nachnameTextfield;
    public DatabaseService databaseService = new DatabaseService();
    public PasswordField passwortField;
    public Pane loginPane;

    public void login(ActionEvent event){
        try {
            Nutzer nutzer = databaseService.getNutzerAusDB(vornameTextfield.getText(),nachnameTextfield.getText());
            if(passwortField.getText().equals(nutzer.getPasswort())){
                loginPane.setVisible(false);
                try {
                    Parent secondStage = FXMLLoader.load(getClass().getResource("/gui.fxml"));
                    Scene scene = new Scene(secondStage, 1000, 777);
                    Stage stage = new Stage();
                    stage.setTitle("Startseite");
                    stage.setScene(scene);
                    FXMLLoader.load(getClass().getResource("/gui.fxml"));
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    stage.show();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Passwort falsch!", ButtonType.CLOSE);
                alert.showAndWait();
            }
        } catch (NutzerNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nutzer nicht gefunden!", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void registrieren(ActionEvent event) {
        loginPane.setVisible(false);
        try {
            Parent secondStage = FXMLLoader.load(getClass().getResource("/registrieren.fxml"));
            Scene scene = new Scene(secondStage, 1000, 777);
            Stage stage = new Stage();
            stage.setTitle("Registrierung");
            stage.setScene(scene);
            FXMLLoader.load(getClass().getResource("/registrieren.fxml"));
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}
