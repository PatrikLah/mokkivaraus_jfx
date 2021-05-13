package varausraportti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class RaporttiController implements Initializable{

    @FXML
    private Button raporttiButton;
    @FXML
    private Button csvNappi;
    @FXML
    private Label varmaaraLabel;
    @FXML
    private DatePicker alkupvmTextField;
    @FXML
    private DatePicker loppupvmTextField;
    @FXML
    private TableView<Raporttioliot> taulukko;
    @FXML
    private TableColumn<Raporttioliot, String> varattu_pvmColumn;
    @FXML
    private TableColumn<Raporttioliot, String> varattu_alkupvmColumn;
    @FXML
    private TableColumn<Raporttioliot, String> varattu_loppupvmColumn;
    @FXML
    private TableColumn<Raporttioliot, Integer> mokkiIDColumn;
    @FXML
    private TableColumn<Raporttioliot, String> mokkiColumn;
    @FXML
    private TableColumn<Raporttioliot, String> etunimiColumn;
    @FXML
    private TableColumn<Raporttioliot, String> sukunimiColumn;


    Database connectNow = new Database();
    Connection connection = connectNow.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    //Tällä formatoidaan päivämäärät haluttuun muotoon
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    ObservableList<Raporttioliot> atribuutit = FXCollections.observableArrayList();

    @FXML
    private void paivitaTaulukko() {
       try {
           //Poistaa edellisen haun tulokset olioista
           atribuutit.clear();

           String query = "SELECT * FROM varaus AS v JOIN asiakas AS a ON v.asiakas_id = a.asiakas_id JOIN mokki m on v.mokki_mokki_id = m.mokki_id WHERE (varattu_alkupvm BETWEEN '" + alkupvmTextField.getValue() + "' AND '" + loppupvmTextField.getValue() +  "') OR (varattu_loppupvm BETWEEN '" + alkupvmTextField.getValue() + "' AND '" + loppupvmTextField.getValue() + "')";

           preparedStatement = connection.prepareStatement(query);
           resultSet = preparedStatement.executeQuery();

           //Siirtää tietokannan tiedot olioihin
           while (resultSet.next()) {
               atribuutit.add(new Raporttioliot(
                       format.format(resultSet.getDate("varattu_pvm")),
                       format.format(resultSet.getDate("varattu_alkupvm")),
                       format.format(resultSet.getDate("varattu_loppupvm")),
                       resultSet.getInt("mokki_id"),
                       resultSet.getString("mokkinimi"),
                       resultSet.getString("etunimi"),
                       resultSet.getString("sukunimi")));
               taulukko.setItems(atribuutit);
           }
       } catch (Exception e) {
               e.printStackTrace();
               e.getCause();
           }
       }

    @FXML
    private void paivitaMaara(){
        //laskee yläpalkkiin valittuna aikavälinä löytyneet varaukset
        try{
            String maaraQuery = "SELECT COUNT(varaus_id) FROM varaus WHERE (varattu_alkupvm BETWEEN '" + alkupvmTextField.getValue() + "' AND '" + loppupvmTextField.getValue() +  "') OR (varattu_loppupvm BETWEEN '" + alkupvmTextField.getValue() + "' AND '" + loppupvmTextField.getValue() + "')";

            String maara = "";
            preparedStatement = connection.prepareStatement(maaraQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maara = resultSet.getString(1);
            }
            System.out.println(maara);
            varmaaraLabel.setText(maara);

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        System.out.println(alkupvmTextField);
        System.out.println(loppupvmTextField);
    }



    @Override
    public void initialize (URL url, ResourceBundle rb) {
        varattu_pvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varauspvmO"));
        varattu_alkupvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varattuAlkupvmO"));
        varattu_loppupvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varattuLoppupvmO"));
        mokkiIDColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,Integer>("mokkiIDO"));
        mokkiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("mokkinimiO"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("EtunimiO"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("SukunimiO"));
    }


    public void writeExcel() {
        PrintWriter outwriter = null;
        try {
            File file = new File("C:\\Raportti.csv");
            outwriter = new PrintWriter(file);
            for (Raporttioliot raporttioliot : atribuutit) {
                outwriter.println(raporttioliot.getVarauspvmO() + ";" + raporttioliot.getVarattuAlkupvmO() + ";" + raporttioliot.getVarattuLoppupvmO() + ";" + raporttioliot.getMokkiIDO() + ";" + raporttioliot.getMokkinimiO() + ";" + raporttioliot.getEtunimiO() + ";" + raporttioliot.getSukunimiO());

            }
            outwriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void haeRaportti(ActionEvent event) {
        //tekee tietokantahaut -nappi
        Stage stage = (Stage) raporttiButton.getScene().getWindow();
        paivitaTaulukko();
        paivitaMaara();
    }
    public void kirjoitaCSVRaportti(ActionEvent event) {
        //tekee Excel raportin -nappi
        Stage stage = (Stage) csvNappi.getScene().getWindow();
        try {
            writeExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
