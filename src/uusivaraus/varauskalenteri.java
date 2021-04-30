package uusivaraus;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class varauskalenteri extends Application {
    @FXML
    private AnchorPane kalenteriPane;
    @FXML
    private DatePicker kalenteriPicker;

    public static void main(String[] args) {
        Tietokantayhteys tietokantayhteys = new Tietokantayhteys();
        tietokantayhteys.setTietokantayhteys();
        launch(args);
    }
    @Override  // ladataan kalenteri fxml tiedosto
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("kalenteri.fxml"));
        primaryStage.setTitle("Varauskalenteri");
        primaryStage.setScene(new Scene(root, 428, 309));
        primaryStage.show();
    }
}
