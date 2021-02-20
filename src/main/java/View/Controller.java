package View;

import Entities.Nutzer;
import Exceptions.NutzerNotFoundException;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {

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
                kinoPane.setVisible(true);
            }else{
                passwortField.setText("Passwort falsch!");
            }
        } catch (NutzerNotFoundException e) {
            vornameTextfield.setText("Nutzer nicht");
            nachnameTextfield.setText("gefunden!");
        }
    }
}
