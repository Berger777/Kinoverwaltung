package View;

import Enums.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController extends Controller{
    public TextField filmLaengeField;
    public Pane adminPane;
    public TextField zeitField;
    public TextField filmTitelTextfield;
    public TextField datumTextfield;
    public TextField preisField;
    public TextField regisseurField;

    public void hinzufuegen(ActionEvent actionEvent) {
    }

    public void filmDelete(ActionEvent actionEvent) {
    }

    public void vorfuehrungDelete(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }
}
