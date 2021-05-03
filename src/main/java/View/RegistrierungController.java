package View;

import Entities.Nutzer;
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

public class RegistrierungController {
    public Pane registerPane;
    public PasswordField passwortField;
    public TextField nachnameTextfield;
    public TextField vornameTextfield;
    public PasswordField passwortField1;
    public DatabaseService databaseService = new DatabaseService();

    public void registrieren(ActionEvent event) {
        if (nachnameTextfield.getText().isEmpty() || vornameTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vorname oder Nachname leer!", ButtonType.CLOSE);
            alert.showAndWait();
        }else if (!passwortField.getText().equals(passwortField1.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Passwörter nicht identisch!", ButtonType.CLOSE);
            alert.showAndWait();
        } else {
            try {
                databaseService.getNutzerAusDB(vornameTextfield.getText(),nachnameTextfield.getText());
            } catch (NutzerNotFoundException e) {
                databaseService.speichereNutzerInDB(vornameTextfield.getText(),nachnameTextfield.getText(),passwortField.getText());
                registerPane.setVisible(false);
                try {
                    Parent secondStage = FXMLLoader.load(getClass().getResource("/login.fxml"));
                    Scene scene = new Scene(secondStage, 1000, 777);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    FXMLLoader.load(getClass().getResource("/login.fxml"));
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    stage.show();
                } catch (IOException er) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", er);
                }
            }
        }
    }
}
