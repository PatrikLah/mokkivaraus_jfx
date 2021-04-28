package Mokkivaraus;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

//pääluokan (Mökkivarauksen) kontrolleri, jossa on tapahtumat
public class Controller {

    //tapahtuma painikkeelle, jolla pääsee varauskalenteri-ikkunaan
    @FXML
    public void goVarausKalenteri(ActionEvent event) throws IOException{
        Main.naytaVarausKalenteri();
    }

    //tapahtuma painikkeelle, jolla pääsee takaisin alkunäkymään
    @FXML
    public void goHome(ActionEvent event) throws IOException{
        Main.showMainItems();
    }

    //tapahtuma painikkeelle, jolla pääsee uusivaraus-ikkunaan
    @FXML
    public void naytaUusiVaraus(ActionEvent event) throws IOException {
        Main.naytaUusiVaraus();
    }
}
