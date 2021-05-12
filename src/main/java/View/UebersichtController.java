package View;

import Enums.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class UebersichtController extends Controller{
    public Button buchungsseiteButton;
    public Button logoutButton;


    public void buchungseite(ActionEvent event) {
        changeSceneTo(event, Scenes.GUI);
    }

    public void logout(ActionEvent event) {
        changeSceneTo(event, Scenes.LOGIN);
    }
}
