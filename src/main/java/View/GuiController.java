package View;

import Entities.Saal;
import Entities.Vorfuehrung;
import Enums.Scenes;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuiController extends Controller implements Initializable {

    public Pane loginPane;

    public Button buttonBuchen;
    public Button buttonUbersicht;
    public TextArea buchungDetails;

    public CheckBox studentenRabattCheckbox;

    private final ChoiceBox[][] sitze = new ChoiceBox[4][4];
    public ChoiceBox vorstellungChoice;
    DatabaseService databaseService = new DatabaseService();

    public void uebersicht(ActionEvent actionEvent) {
        changeSceneTo(actionEvent, Scenes.UEBERSICHT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       sychnronisiereViewMitDB();
    }

    private void sychnronisiereViewMitDB() {
        vorstellungChoice.getItems().clear();

        ArrayList<Vorfuehrung> vor = databaseService.getVorfuehrungenAusDB();
        for (Vorfuehrung v : vor) {
            vorstellungChoice.getItems().add(v.getFilm().getTitel()+ " , "+ v.getDatum() +" , "+ v.getZeit() + " , "+ v.getAufschlag());
        }
        StringBuilder bob = new StringBuilder();
    }

    public void buchen(javafx.event.ActionEvent actionEvent) {
        //TODO: Sitze buchen
        //TODO: Reservierung erstellen
        //TODO: Preis errechnen + Rabatt (abfrage vor buchen button)
        sychnronisiereViewMitDB();
    }

    public void choice_click(KeyEvent actionEvent) {
        //TODO: View updaten aus DB checkboxen disablen
        //TODO: Danach Checkboxen mappen auf Array und auslesen -> Buchung
    }
}
