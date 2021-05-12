package View;

import Entities.Film;
import Entities.Saal;
import Entities.Vorfuehrung;
import Enums.Scenes;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collector;

public class AdminController extends Controller implements Initializable {
    public TextField filmLaengeField;
    public TextField filmTitelTextfield;
    public TextField filmpreisField;
    public TextField filmregisseurField;
    public TextField filmbeschreibung;

    public TextField vorAufschlag;
    public TextField vorzeitField;
    public TextField vordatumTextfield;

    public Pane adminPane;
    public DatabaseService databaseService = new DatabaseService();

    public Button vorDelButton;
    public Button vorHinzu;
    public Button filmDelButton;
    public Button filmHinzuButton;
    public ChoiceBox saalChoice;
    public ChoiceBox filmChoice;
    public ChoiceBox filmDelChoice;
    public ChoiceBox vorDelChoice;

    public void filmDelete(ActionEvent actionEvent) {
        //TODO
        sychnronisiereViewMitDB();
    }

    public void vorfuehrungDelete(ActionEvent actionEvent) {
        //TODO
        sychnronisiereViewMitDB();
    }

    private void sychnronisiereViewMitDB() {
        ArrayList<Film> filme = databaseService.getFilmeAusDB();
        for (Film f : filme) {
            filmDelChoice.getItems().add(f.getTitel());
            filmChoice.getItems().add(f.getTitel());
        }
        ArrayList<Vorfuehrung> vor = databaseService.getVorfuehrungenAusDB();
        for (Vorfuehrung v : vor) {
            vorDelChoice.getItems().add(v.getVorfuehrungId());
        }
        ArrayList<Saal> saale = databaseService.getSaaleAusDB();
        for (Saal s : saale) {
            saalChoice.getItems().add(s.getSaalname());
        }
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }

    public void vorfuehrungHinzufuegen(ActionEvent actionEvent) {
        String filmid = databaseService.getFilmeAusDB().stream().filter(f -> filmChoice.getValue().toString().equals(f.getTitel())).findFirst().get().getFilmId();
        String saalid = databaseService.getSaaleAusDB().stream().filter(s -> s.getSaalname().equals(saalChoice.getValue().toString())).findFirst().get().getSaalId();
        databaseService.speichereVorfuehrungInDB(filmid, vorAufschlag.getText(), vorzeitField.getText(), vordatumTextfield.getText(), saalid);
        sychnronisiereViewMitDB();
    }

    public void filmHinzu(ActionEvent actionEvent) {
        databaseService.speichereFilmInDB(filmTitelTextfield.getText(), filmbeschreibung.getText(), filmLaengeField.getText(), filmregisseurField.getText(), filmpreisField.getText());
        sychnronisiereViewMitDB();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sychnronisiereViewMitDB();
    }
}
