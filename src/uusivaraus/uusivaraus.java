package uusivaraus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class uusivaraus extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("uusivaraus.fxml"));
        primaryStage.setTitle("UUSI VARAUS");
        primaryStage.setScene(new Scene(root, 428, 309));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{
        Tietokantayhteys tietokantayhteys = new Tietokantayhteys();
        tietokantayhteys.setTietokantayhteys();
        //Tietokantayhteys.lisaa();

        launch(args);
    }
}
