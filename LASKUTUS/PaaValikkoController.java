package LASKUTUS;

import LASKUTUS.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class PaaValikkoController {

    private Main main;

    @FXML
    private void goPaluu() throws IOException { //toiminto, jotta "paluu"-painikkeella pääsee laskutuksen aloitussivulle
        main.showPaaPainikkeet();
    }

    /**@FXML
    private void lisaaNappi() throws IOException {
        main.showLisaaUusiStage();
    }*/

}