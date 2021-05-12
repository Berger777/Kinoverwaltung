package View;

import Enums.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class GuiController extends Controller{

    public Button buttonBuchen;
    public ChoiceBox saal_auswahl;
    public Pane loginPane;
    public ChoiceBox saal_auswahl1;
    public Button buttonUbersicht;

    public void buchen(javafx.event.ActionEvent actionEvent) {
        System.out.println("Saal Auswahl: "+ saal_auswahl.getValue());
    }

    public void uebersicht(ActionEvent actionEvent) {
        changeSceneTo(actionEvent, Scenes.UEBERSICHT);
    }
}
