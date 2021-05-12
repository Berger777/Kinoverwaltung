package View;

import Entities.Nutzer;
import Enums.Scenes;
import Exceptions.NutzerNotFoundException;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends Controller implements Initializable {

    public DatabaseService databaseService = new DatabaseService();
    public PasswordField passwortField;
    public Pane loginPane;
    public TextField benutzernameTextfield;
    public ImageView image;

    public void login(ActionEvent event){
        if(benutzernameTextfield.getText().equals("admin")){
            if (passwortField.getText().equals("admin")){
                changeSceneTo(event, Scenes.ADMIN);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Passwort falsch!", ButtonType.CLOSE);
                alert.showAndWait();
            }
        }else{
        try {
            Nutzer nutzer = databaseService.getBenutzerAusDB(benutzernameTextfield.getText());
            if(passwortField.getText().equals(nutzer.getPasswort())){
                setCurrentUser(nutzer);
                changeSceneTo(event,Scenes.UEBERSICHT);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Passwort falsch!", ButtonType.CLOSE);
                alert.showAndWait();
            }
        } catch (NutzerNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nutzer nicht gefunden!", ButtonType.CLOSE);
            alert.showAndWait();
        }
        }
    }

    public void registrieren(ActionEvent event) {
        loginPane.setVisible(false);
        changeSceneTo(event,Scenes.REGISTRIEREN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("/kinsnox.png"));
    }
}
