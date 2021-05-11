package View;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class GuiController {

    public Button buttonBuchen;
    public ChoiceBox saal_auswahl;

    public void buchen(javafx.event.ActionEvent actionEvent) {
        System.out.println("Saal Auswahl: "+ saal_auswahl.getValue());
    }
}
