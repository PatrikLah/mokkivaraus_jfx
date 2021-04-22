package Varauskalenteri;

import javafx.fxml.FXML;
import uusivaraus.uusivaraus;

public class Controller {

    private Mokkivaraus.Main main;

    @FXML
    private void goVarausKalenteri() throws Exception{
       main.naytaVarausKalenteri();
    }
}
