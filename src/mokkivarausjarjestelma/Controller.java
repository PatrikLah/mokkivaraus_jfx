package mokkivarausjarjestelma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Controller {
    @FXML
    private Button koti;
    @FXML
    private Button uusiVaraus;
    @FXML
    private Button mokkienHallinta;
    @FXML
    private Button asiakasHallinta;
    @FXML
    private Button laskutus;
    @FXML
    private Button raportit;

    @FXML // koti-napista pääsee takaisin koti näkymään
    public void goKoti(ActionEvent event) throws IOException {
        Main.showMainView();
        System.out.println("Onnistui");
    }
    @FXML // uusi varaus napista pääsee kirjaamaan uutta varausta
    public void goUusiVaraus(ActionEvent event) throws IOException {
        Main.naytaVarausKalenteri();
    }
    @FXML // mökkien hallinta napista pääsee hallitsemaan mökkejä
    public void goMokkienHallinta(ActionEvent event) throws IOException {
        Main.naytaMokkienHallinta();
    }
    @FXML // asiakashallinta napista pääsee asiakashallintaan
    public void goAsiakasHallinta(ActionEvent event) throws IOException {
        Main.naytaAsiakashallinta();
    }
    @FXML //laskutus napista laskutukseen
    public void goLaskutus(ActionEvent event) throws IOException {
        Main.naytaLaskutus();
    }
    @FXML // raportti napista pääsee raporttiin
    public void goRaportit(ActionEvent event) throws IOException {
        Main.naytaRaportit();
    }
}
