package asiakashallinta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class AsiakaslistaController implements Initializable {

    private ObservableList<Asiakas> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Asiakas> asiakaslistaTableView;
    @FXML
    private TableColumn<Asiakas, String> etunimiTableColumn;
    @FXML
    private TableColumn<Asiakas, String> sukunimiTableColumn;
    @FXML
    private TextArea asiakastiedotTextArea;
    @FXML
    private Button poistaButton;
    @FXML
    private Button lisaaButton;
    @FXML
    private Button muokkaaButton;
    private Asiakas valittuAsiakas;




    public void tuoData(){
        Tietokantayhteys yhteys = new Tietokantayhteys();
        Connection yhdistaDB = yhteys.getYhteys();
        String haeAsiakkaat = "SELECT * FROM asiakas;";// AS a JOIN posti as p ON a.postinro = p.postinro";

        ResultSet hakutulos = null;
        try{
            data.clear();
            PreparedStatement prepStatement = yhdistaDB.prepareStatement(haeAsiakkaat);
            hakutulos = prepStatement.executeQuery(haeAsiakkaat);

            // Lisätään tietokannasta haetut tiedot Asiakas-olioihin ja lisätään ne listaan
            while (hakutulos.next()){
                data.add(new Asiakas(hakutulos.getInt("asiakas_id"),
                        hakutulos.getString("etunimi"),
                        hakutulos.getString("sukunimi"),
                        hakutulos.getString("lahiosoite"),
                        hakutulos.getString("postinro"),
                        hakutulos.getString("email"),
                        hakutulos.getString("puhelinnro")));
                asiakaslistaTableView.setItems(data);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        etunimiTableColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("etunimi"));
        sukunimiTableColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("sukunimi"));

        tuoData();

        asiakaslistaTableView.setRowFactory(tv ->{
            TableRow<Asiakas> rivi = new TableRow<>();
            rivi.setOnMouseClicked(mouseEvent -> {
                valittuAsiakas = rivi.getItem();
                asiakastiedotTextArea.setText(valittuAsiakas.etunimi + "\n" +
                        valittuAsiakas.sukunimi + "\n" +
                        valittuAsiakas.osoite + "\n" +
                        valittuAsiakas.postinumero + "\n" +
                        valittuAsiakas.email + "\n" +
                        valittuAsiakas.puhelin);
            });
            return rivi;
        });
        poistaButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (valittuAsiakas.asiakasID != null) {
                    // TODO: vahvistusikkuna: "Haluatko varmasti poistaa asiakkaan tiedot?"
                    String SQLpoisto = "DELETE FROM asiakas WHERE asiakas_id = " + valittuAsiakas.asiakasID + ";";
                    Tietokantayhteys yhteys = new Tietokantayhteys();
                    Connection yhdistaDB = yhteys.getYhteys();

                    try{
                        PreparedStatement statement = yhdistaDB.prepareStatement(SQLpoisto);
                        statement.executeUpdate(SQLpoisto);

                        asiakastiedotTextArea.clear();
                        tuoData();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        lisaaButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Asiakashallinta.fxml"));
                    Parent parent = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Lisää uusi asiakas");
                    stage.setScene(new Scene(parent));
                    // Päivittää parent-ikkunan
                    stage.setOnHidden(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            tuoData();
                        }
                    });
                    stage.show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        muokkaaButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event){
                if(valittuAsiakas.asiakasID != null){
                    try{
                        avaaAsiakashallinta(valittuAsiakas);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public Stage avaaAsiakashallinta(Asiakas asiakas) throws IOException{
        FXMLLoader lataaja = new FXMLLoader(getClass().getResource("asiakashallinta.fxml"));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Muokkaa asiakkaan tietoja");
        stage.setScene(new Scene(lataaja.load()));

        AsiakashallintaController kontrolleri = lataaja.getController();
        kontrolleri.alustaData(asiakas);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                tuoData();
            }
        });

        stage.show();

        return stage;
    }


}
