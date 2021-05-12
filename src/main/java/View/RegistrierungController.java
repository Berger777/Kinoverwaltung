package View;

import Entities.Nutzer;
import Enums.Scenes;
import Exceptions.NutzerNotFoundException;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrierungController extends Controller{
    public Pane registerPane;
    public PasswordField passwortField;
    public TextField nachnameTextfield;
    public TextField vornameTextfield;
    public PasswordField passwortField1;
    public DatabaseService databaseService = new DatabaseService();
    public TextField benutzernameTextfield;

    public void registrieren(ActionEvent event) {
        if (nachnameTextfield.getText().isEmpty() || vornameTextfield.getText().isEmpty() || benutzernameTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vorname, Nachname oder Benutzername leer!", ButtonType.CLOSE);
            alert.showAndWait();
        }else if (!passwortField.getText().equals(passwortField1.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Passwörter nicht identisch!", ButtonType.CLOSE);
            alert.showAndWait();
        }else {
            try {
                databaseService.getBenutzerAusDB(benutzernameTextfield.getText());
            } catch (NutzerNotFoundException e) {
                try {
                    databaseService.getBenutzerAusDB(benutzernameTextfield.getText());
                } catch (NutzerNotFoundException ee) {
                    databaseService.speichereNutzerInDB(vornameTextfield.getText(),nachnameTextfield.getText(),benutzernameTextfield.getText(),passwortField.getText());
                    registerPane.setVisible(false);
                    changeSceneTo(event, Scenes.LOGIN);
                }
            }
        }
    }
}
