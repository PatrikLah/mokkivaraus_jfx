package LASKUTUS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uusilasku.entinenuusilaskuController;

import java.io.IOException;


public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane PaaValikko;

    @Override
    public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Mökkivaraus - Laskutus");

    showPaaValikkoLaskutus(); //paluu-painike, vastaa Youtuben MainLayout-näkymää
    showPaaPainikkeet(); // uusi lasku- ja näytä laskut -painikkeet, vastaa Youtuben MainItems-näkymää

    }

    //tässä kehyksessä näkyy Laskutus-titlepane yläreunassa ja alareunassa "Palaa"-painike
    public static void showPaaValikkoLaskutus() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PaaValikkoLaskutus.fxml"));
        PaaValikko = loader.load();
        Scene scene = new Scene(PaaValikko);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //tässä paneelissa näkyy aloituspainikkeet "Uusi lasku" ja "Laskuarkisto":
    public static void showPaaPainikkeet() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PaaPainikkeet.fxml"));
        AnchorPane paapainikkeetPane = loader.load();
        PaaValikko.setCenter(paapainikkeetPane);
    }

    //metodi, josta pääsee painiketta "Uusi lasku" painamalla uusilasku-tilaan:
    public static void showUusiLasku() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("uusilasku.fxml"));
        BorderPane uusilasku = loader.load();
        PaaValikko.setCenter(uusilasku);
    }
    //metodi, josta pääsee painiketta "Laskuarkisto" painamalla laskuarkistoon:
    public static void showLaskuArkisto() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("laskuarkisto.fxml"));
        AnchorPane laskuarkisto = loader.load();
        PaaValikko.setCenter(laskuarkisto);
    }
    //metodi, jolla lisätään/tallennetaan uuden laskun tiedot, uusi stage:
    public static void showLisaaUusiStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/uusilasku/entinenuusilasku.fxml"));
        BorderPane LisaaUusiLasku = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Lisää uusi lasku");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(LisaaUusiLasku);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

    }

    public static void main(String[] args) throws Exception {

        launch(args);
    }
}
