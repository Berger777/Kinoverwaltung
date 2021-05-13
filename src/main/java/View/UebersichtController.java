package View;

import Entities.*;
import Enums.Scenes;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class UebersichtController extends Controller implements Initializable {
    public TextArea reservierungenTextArea;
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
        //Reservierungen delete
        //Sitz free
        sychnronisiereViewMitDB();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservierungenTextArea.setDisable(false);
        reservierungenTextArea.setEditable(false);
        sychnronisiereViewMitDB();
    }

    private void sychnronisiereViewMitDB() {
        StringBuilder bob = new StringBuilder();
        reservierungChoice.getItems().clear();
        ArrayList<Reservierung> reservierungen = databaseService.getReservierungenAsList(getCurrentUser());
        for (Reservierung res : reservierungen) {
            reservierungChoice.getItems().add(res.getReservierungId());
        }
        reservierungenTextArea.clear();
        ArrayList<Sitz> sitze = databaseService.getSitzeAsList();

        reservierungen.stream().map(reservierung -> {
            bob.append("Reservierte-Sitze:\n------------------------------\n");
            AtomicInteger i = new AtomicInteger(1);
            sitze.forEach(sitz -> {
                if (sitz.getReservierungId() != null && sitz.getReservierungId().equals(reservierung.getReservierungId())) {
                    bob.append("Sitz-Nummer: ").append(i.getAndIncrement()).append("\n").append("Sitz-Reihe: ")
                            .append(sitz.getReihe()).append("\nSitz-Nummer: ").append(sitz.getNr()).append("\n");
                }
            });
            return ("Reservierungs-Nummer: " + reservierung.getReservierungId() + "\nFilm-Titel: " +
                    reservierung.getVorfuehrung().getFilm().getTitel() + "\n" + "Datum: " + reservierung.getVorfuehrung().getDatum()
                    + "\nUhrzeit: " + reservierung.getVorfuehrung().getZeit() + "\n" + "Film-Typ: "
                    + reservierung.getVorfuehrung().getAufschlag() + "\n" + "\n------------------------------");
        }).forEach(bob::append);

        reservierungenTextArea.setText(bob.toString());
    }
}
