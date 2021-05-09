package mokkivarausjarjestelma;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static BorderPane mainLayout;
    private static Stage primaryStage;

    //pää näkymän "alustus"
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mökkivaraukset");
        showMainView();
    }
    // tällä metodilla näytetään pää-/alkunäkymän sivu-valinta palkki josta pääsee eteenpäin
    public static void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("gui.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //metodi uuden varauksen näyttämiseen
    public static void naytaVarausKalenteri() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(uusivaraus.class.getResource("/uusivaraus/kalenteri.fxml"));
        AnchorPane varauskalenteri = loader.load();
        mainLayout.setCenter(varauskalenteri);
    }
    //metodi mökkien hallintaan
    public static void naytaMokkienHallinta(){
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(---fxml tiedosto tähän----);
        //lisää myös oikea "pane"
        //mainLayout.setCenter(--pane---);
    }
    //metodi asiakashallintaan
    public static void naytaAsiakashallinta(){
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(---fxml tiedosto tähän----);
        //lisää myös oikea "pane"
        //mainLayout.setCenter(--pane---);
    }
    // metodi laskutus osioon
    public static void naytaLaskutus(){
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(---fxml tiedosto tähän----);
        //lisää myös oikea "pane"
        //mainLayout.setCenter(--pane---);
    }
    //metodi raportti osioon
    public static void naytaRaportit(){
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(---fxml tiedosto tähän----);
        //lisää myös oikea "pane"
        //mainLayout.setCenter(--pane---);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
// valerijan kommentti