package Mokkivaraus;

import Uusivaraus.Henkilo;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//pääluokan (Mökkivarauksen) kontrolleri, jossa on tapahtumat
public class Controller {
    @FXML
    private TextField mvarausID;
    @FXML
    private TextField Malue;
    @FXML
    private TextField Mmokki;
    @FXML
    private TextField Malkupvm;
    @FXML
    private TextField Mloppupvm;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vn";
    private static Connection conn = null;


    public void muokkaus(Henkilo henkilo) throws SQLException {
        String paivita= "UPDATE varaus SET varaus_id = "+mvarausID.getText()+
        ", mokki_mokki_id= "+ mvarausID.getText()+" , varattu_alkupvm="+ Malkupvm.getText() +
        ", varattu_loppupvm=" +Mloppupvm.getText();
        conn = DriverManager.getConnection(DB_URL, "root", "Terhi88");
        PreparedStatement stmt = conn.prepareStatement(paivita);
        stmt.executeUpdate();
    }

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
