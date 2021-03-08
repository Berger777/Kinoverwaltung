package View;

import Entities.Nutzer;
import Exceptions.NutzerNotFoundException;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    public Pane kinoPane;

    public void login(ActionEvent event){
        try {
            Nutzer nutzer = databaseService.getNutzerAusDB(vornameTextfield.getText(),nachnameTextfield.getText());
            if(passwortField.getText().equals(nutzer.getPasswort())){
                loginPane.setVisible(false);
            }else{
                passwortField.setText("Passwort falsch!");
            }
        } catch (NutzerNotFoundException e) {
            vornameTextfield.setText("Nutzer nicht");
            nachnameTextfield.setText("gefunden!");
        }
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
    }


}
