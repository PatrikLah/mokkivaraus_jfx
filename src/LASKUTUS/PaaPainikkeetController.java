package LASKUTUS;

import LASKUTUS.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class PaaPainikkeetController {

    private Main main;

    @FXML
    private void goUusiLasku() throws IOException {
        main.showUusiLasku();
    }

    @FXML
    private void goLaskuArkisto() throws IOException {
        main.showLaskuArkisto();
    }

    //@FXML
    //private void goPaluu() throws IOException { //toiminto, jotta "paluu"-painikkeella pääsee aloitussivulle
    //    main.showPaaPainikkeet();
   // }
}
