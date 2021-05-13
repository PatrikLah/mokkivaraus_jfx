package Uusivaraus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UusiMain {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Uusivaraus/kokeilu.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
