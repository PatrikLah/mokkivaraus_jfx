package Hallinta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private static AnchorPane mokit;

    @Override
    //näkymä mökkien ja palveluiden hallinnalle
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mökkien ja palveluiden hallinta");

        showMokit();
    }
    //saadaan auki näkymä
    public static void showMokit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("mokit.fxml"));
        mokit = loader.load();
        Scene scene = new Scene(mokit);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
