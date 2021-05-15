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
    private final DatabaseService databaseService = new DatabaseService();
    public Button buchungsseiteButton;
    public Button logoutButton;
    public Button stornierenButton;
    public ChoiceBox<String> reservierungChoice;

    public void buchungseite(ActionEvent event) {
        changeSceneTo(event, Scenes.GUI);
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }

    public void stornieren(ActionEvent actionEvent) {
        databaseService.deleteReservierung(reservierungChoice.getValue().toString());
        //TODO: Sitz free
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
            bob.append("Reservierungs-Nr: ").append(reservierung.getReservierungId());
            bob.append("\n------------------------------\nReservierte-Sitze:\n");
            AtomicInteger i = new AtomicInteger(1);
            sitze.forEach(sitz -> {
                for (SitzReservierung sr: databaseService.getSitzReservierungenAsListSimple()
                     ) {
                    if (sr.getReservierungID().equals(reservierung.getReservierungId()) && sitz.getSitzId().equals(sr.getSitzID())){
                        bob.append("Sitz-").append(i.getAndIncrement()).append(" ---> ").append("Sitz-Reihe: ")
                                .append(sitz.getReihe()).append(" - Sitzplatz-Nummer: ").append(sitz.getNr()).append("\n");
                    }
                }
            });
            bob.append("--------------\nVorstellungs-Daten:\n");
            return ("Film-Titel: " +
                    reservierung.getVorfuehrung().getFilm().getTitel() + "\n" + "Datum: " + reservierung.getVorfuehrung().getDatum()
                    + "\nUhrzeit: " + reservierung.getVorfuehrung().getZeit() + "\n" + "Film-Typ: "
                    + reservierung.getVorfuehrung().getAufschlag() + "\n" + "------------------------------\n");
        }).forEach(bob::append);

        reservierungenTextArea.setText(bob.toString());
    }
}
