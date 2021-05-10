package mokkivarausjarjestelma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RaporttiController implements Initializable{

    @FXML
    private Button raporttiButton;
    @FXML
    private Label varmaaraLabel;
    @FXML
    private TextField alkupvmTextField;
    @FXML
    private TextField loppupvmTextField;
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
    String query = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<Raporttioliot> atribuutit = FXCollections.observableArrayList();

    @FXML
    private void paivitaTaulukko() {
       try {
           atribuutit.clear();
           String alkupvm = alkupvmTextField.getText();
           String loppupvm = loppupvmTextField.getText();
           String query = "SELECT * FROM varaus AS v JOIN asiakas AS a ON v.asiakas_id = a.asiakas_id JOIN mokki m on v.mokki_mokki_id = m.mokki_id WHERE (varattu_alkupvm BETWEEN '" + alkupvm + "' AND '" + loppupvm +  "') OR (varattu_loppupvm BETWEEN '" + alkupvm + "' AND '" + loppupvm + "')";

           preparedStatement = connection.prepareStatement(query);
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               atribuutit.add(new Raporttioliot(
                       resultSet.getString("varattu_pvm"),
                       resultSet.getString("varattu_alkupvm"),
                       resultSet.getString("varattu_loppupvm"),
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

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        varattu_pvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varauspvmO"));
        varattu_alkupvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varattuAlkupvmO"));
        varattu_loppupvmColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot, String>("varattuLoppupvmO"));
        mokkiIDColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,Integer>("mokkiIDO"));
        mokkiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("mokkinimiO"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("EtunimiO"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<Raporttioliot,String>("SukunimiO"));


        String alkupvm = alkupvmTextField.getText();
        String loppupvm = loppupvmTextField.getText();



    }

    public void haeRaportti(ActionEvent event) {
        //tee tietokantahaku
        Stage stage = (Stage) raporttiButton.getScene().getWindow();
        paivitaTaulukko();
        System.out.println("Nappi toimii");
    }


}
