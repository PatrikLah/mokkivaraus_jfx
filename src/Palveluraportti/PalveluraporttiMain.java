package Palveluraportti;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class PalveluraporttiMain {

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("palveluraportti.fxml")));
        primaryStage.setTitle("Palvelu raportti");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}
