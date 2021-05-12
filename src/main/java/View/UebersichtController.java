package View;

import Entities.Reservierung;
import Enums.Scenes;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UebersichtController extends Controller implements Initializable {
    private DatabaseService databaseService = new DatabaseService();
    public Button buchungsseiteButton;
    public Button logoutButton;
    public Button stornierenButton;
    public ChoiceBox reservierungChoice;

    public void buchungseite(ActionEvent event) {
        changeSceneTo(event, Scenes.GUI);
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }

    public void stornieren(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Reservierung> reservierungen = databaseService.getReservierungenAsList(getCurrentUser());
        for (Reservierung res: reservierungen) {
            reservierungChoice.getItems().add(res.getReservierungId());
        }
        //TODO Reservierungen anzeigen im TextArea + Storno
    }
}
