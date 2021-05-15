package View;

import Entities.*;
import Enums.Scenes;
import Services.DatabaseService;
import Util.Buchung;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GuiController extends Controller implements Initializable {

    private String vorstellungID = null;

    public Pane loginPane;
    public Button buttonBuchen;
    public Button buttonUbersicht;
    public TextArea buchungDetails;

    public CheckBox studentenRabattCheckbox;

    private final CheckBox[][] sitze = new CheckBox[4][4];
    public CheckBox r4s1;
    public CheckBox r4s2;
    public CheckBox r4s3;
    public CheckBox r4s4;
    public CheckBox r3s1;
    public CheckBox r3s2;
    public CheckBox r3s3;
    public CheckBox r3s4;
    public CheckBox r2s1;
    public CheckBox r2s2;
    public CheckBox r2s3;
    public CheckBox r2s4;
    public CheckBox r1s1;
    public CheckBox r1s2;
    public CheckBox r1s3;
    public CheckBox r1s4;

    private final Buchung buchung = new Buchung();
    private Saal saal = null;

    DatabaseService databaseService = new DatabaseService();
    public ChoiceBox<Vorfuehrung> vorstellungChoice;
    StringConverter<Vorfuehrung> strconv = new StringConverter<Vorfuehrung>() {
        @Override
        public String toString(Vorfuehrung v) {
            return v.getFilm().getTitel() + " , " + v.getDatum() + " , " + v.getZeit() + " , " + v.getAufschlag() + " , " + v.getSaal().getSaalname();
        }

        @Override
        public Vorfuehrung fromString(String string) {
            return null;
        }
    };

    public void uebersicht(ActionEvent actionEvent) {
        changeSceneTo(actionEvent, Scenes.UEBERSICHT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vorstellungChoice.setConverter(strconv);
        sitze[0] = new CheckBox[]{r1s1, r1s2, r1s3, r1s4};
        sitze[1] = new CheckBox[]{r2s1, r2s2, r2s3, r2s4};
        sitze[2] = new CheckBox[]{r3s1, r3s2, r3s3, r3s4};
        sitze[3] = new CheckBox[]{r4s1, r4s2, r4s3, r4s4};
        sychnronisiereViewMitDB();
        checkboxenDisable();
    }

    private void sychnronisiereViewMitDB() {
        vorstellungChoice.getItems().clear();

        ArrayList<Vorfuehrung> vor = databaseService.getVorfuehrungenAusDB();
        for (Vorfuehrung v : vor) {
            vorstellungChoice.getItems().add(v);
        }
    }

    public void buchen(javafx.event.ActionEvent actionEvent) {
        String reservierungID = databaseService.speichereReservierung(getCurrentUser(),vorstellungID);
        for (Sitz sitz: buchung.getSitzBuchungen()) {
            databaseService.speichereSitzReservierung(reservierungID,sitz);
        }
        checkboxenReset();
        buchungDetails.setText("");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Buchung erfolgreich!", ButtonType.CLOSE);
        alert.showAndWait();
        sychnronisiereViewMitDB();
    }

    public void choice_click(Event actionEvent) {
        checkboxenReset();
        if (vorstellungChoice.getValue() != null) {
            vorstellungID = vorstellungChoice.getValue().getVorfuehrungId();
            saal = vorstellungChoice.getValue().getSaal();
            buchung.setVorfuehrung(vorstellungChoice.getValue());
            updateSitzeBelegung(vorstellungID);
            buchungDetails.setText("");
        }

    }

    private void checkboxenReset() {
        for (CheckBox[] s : sitze) {
            for (CheckBox c : s) {
                c.setDisable(false);
                c.setSelected(false);
            }
        }
    }

    private void checkboxenDisable() {
        for (CheckBox[] s : sitze) {
            for (CheckBox c : s) {
                c.setDisable(true);
            }
        }
    }

    public void updateSitzeBelegung(String vorstellungID) {
        ArrayList<Reservierung> reservierungArrayList = databaseService.getReservierungenAsList();
        ArrayList<SitzReservierung> sitzeReservierungArrays = databaseService.getSitzReservierungenAsListSimple();
        databaseService.getSitzeAsList().stream().filter(sitz -> {
                for (SitzReservierung sr: sitzeReservierungArrays) {
            for (Reservierung r : reservierungArrayList) {
                    if (sr.getSitzID().equals(sitz.getSitzId()) && sr.getReservierungID().equals(r.getReservierungId())){
                        return r.getVorfuehrung().getVorfuehrungId().equals(vorstellungID);
                    }
                }
            }
            return false;
        }).forEach(sitz -> {
            sitze[Integer.parseInt(sitz.getReihe()) - 1][Integer.parseInt(sitz.getNr()) - 1].setDisable(true);
            sitze[Integer.parseInt(sitz.getReihe()) - 1][Integer.parseInt(sitz.getNr()) - 1].setSelected(true);
        });
    }

    public void sitzplatzVormerken(ActionEvent actionEvent) {
        String reihe = actionEvent.getSource().toString().substring(actionEvent.getSource().toString().indexOf("id=") + 4, actionEvent.getSource().toString().indexOf(",") - 2);
        String sitzplatz = actionEvent.getSource().toString().substring(actionEvent.getSource().toString().indexOf("id=") + 6, actionEvent.getSource().toString().indexOf(","));
        Optional<Sitz> sitzOptional = databaseService.getSitzeAsList().stream().filter(sitzDB -> sitzDB.getNr().equals(sitzplatz) && sitzDB.getReihe().equals(reihe) && sitzDB.getSaal().getSaalId().equals(saal.getSaalId())).findFirst();
        Sitz sitz = null;
        if (sitzOptional.isPresent()){
            sitz = sitzOptional.get();
            if (((CheckBox) actionEvent.getSource()).isSelected()) { //add
                buchung.getSitzBuchungen().add(sitz);
            }else { //rem
                buchung.getSitzBuchungen().remove(sitz);
            }
        }
        else{
            System.err.println("Fehler Sitz nicht gefunden!");
        }
        buchungDetails.setText(buchung.toString());
    }

    public void rabatt(ActionEvent actionEvent) {
        buchung.setRabatt(((CheckBox) actionEvent.getSource()).isSelected());
        if (buchung.getSitzBuchungen().size()!=0)buchungDetails.setText(buchung.toString());
    }
}
