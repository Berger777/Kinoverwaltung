package View;

import Enums.Scenes;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController extends Controller{
    public TextField filmLaengeField;
    public TextField filmTitelTextfield;
    public TextField filmpreisField;
    public TextField filmregisseurField;
    public TextField filmbeschreibung;
    public TextField filmAufschlag;

    public TextField zeitField;
    public TextField vordatumTextfield;

    public Button filmHinzuButton;
    public Pane adminPane;
    public DatabaseService databaseService = new DatabaseService();

    public void filmDelete(ActionEvent actionEvent) {
    }

    public void vorfuehrungDelete(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }

    public void vorfuehrungHinzufuegen(ActionEvent actionEvent) {
       // databaseService.speichereVorfuehrungInDB();
    }

    public void filmHinzu(ActionEvent actionEvent) {
        databaseService.speichereFilmInDB(filmTitelTextfield.getText(),filmbeschreibung.getText(),filmLaengeField.getText(),filmregisseurField.getText(),filmpreisField.getText());
    }
}
