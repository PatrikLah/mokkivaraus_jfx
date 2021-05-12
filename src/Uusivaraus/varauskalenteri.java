package Uusivaraus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class varauskalenteri extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override  // ladataan kalenteri fxml tiedosto
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("kalenteri.fxml"));
        primaryStage.setTitle("Lisää palvelu");
        primaryStage.setScene(new Scene(root, 428, 309));
        primaryStage.show();
    }
}
