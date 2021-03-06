package Application;

import Enums.Scenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Scenes.LOGIN.getUri()));
        primaryStage.setTitle("Kinoverwaltungsoftware 1.0");
        primaryStage.setScene(new Scene(root, 1000, 777));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
