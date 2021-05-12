package Mokkivaraus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


//pääikkuna = aloitusnäyttö josta pääsee eteenpäin ja johon voi palata
public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mökkivaraukset");
        showMainView();
        showMainItems();

    }
    //alkuikkuna, tässä näkyy alapaneeli, jossa takaisin painike ja tämän päälle tulee "näkymä"- ikkunat
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("YlaAlaNakyma.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //tässä näkyy aloitusikkunan painikkeet, joista voi siirtyä eteenpäin, astettu keskelle
    public static void showMainItems() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PaaNakyma.fxml"));
        AnchorPane anchorPane = loader.load();
        mainLayout.setCenter(anchorPane);
    }
    //metodi josta pääsee painiketta painamalla varauskalenteri tilaan
    public static void naytaVarausKalenteri() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Uusivaraus.varauskalenteri.class.getResource("/Uusivaraus/kalenteri.fxml"));
        AnchorPane varauskalenteri = loader.load();
        mainLayout.setCenter(varauskalenteri);
    }
    // metodi josta pääsee painiketta painamalla uusivaraus tilaan
    public static void naytaUusiVaraus() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Uusivaraus.MainKok.class.getResource("/Uusivaraus/kokeilu.fxml")); //TESTI

        AnchorPane uusivaraus = loader.load();

        Stage addDialogStage  = new Stage();
        addDialogStage.setTitle("uusi varaus");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);

        Scene scene = new Scene(uusivaraus);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
        //mainLayout.setCenter(uusivaraus);
    }
  

    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
