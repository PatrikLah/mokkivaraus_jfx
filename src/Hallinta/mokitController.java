package Hallinta;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class mokitController {

    //lisätään tekstikentät mökeille
    @FXML
    private TextField nimikentta;

    @FXML
    private TextField osoitekentta;

    @FXML
    private TextField kuvauskentta;

    @FXML
    private TextField henkilokentta;

    @FXML
    private TextField varustelukenttä;


    //lisätään tekstikentät palveluille

    @FXML
    private TextField palvelunimikentta;

    @FXML
    private TextField aluekentta;

    @FXML
    private TextField kuvauskenttakaksi;

    @FXML
    private TextField hintakentta;

    @FXML
    private TextField alvkentta;

    @FXML
    private TextField toimintaaluekentta;

    @FXML
    private Button tyhjennamokki;

    @FXML
    private Button tyhjennapalvelu;

    //mökin talut
    @FXML
    TableView<Mokitt> taulukko;

    @FXML
    private TableColumn <Mokitt, String> mokkiColumn;

    @FXML
    private TableColumn <Mokitt, String> katuosoiteColumn;

    @FXML
    private TableColumn <Mokitt, String> kuvausColumn;

    //toiminta-alueen taulut

    @FXML
    TableView<Palvelut> taulukko2;


    @FXML
    private TableColumn <Palvelut, String> palveluColumn;

    @FXML
    private TableColumn <Palvelut, Double> hintaColumn;
    @FXML
    private TableColumn <Palvelut, String> kuvausPColumn;



   ObservableList<Mokitt> atribuutit = FXCollections.observableArrayList();

    //@Override
    public void initialize() {
        mokkiColumn.setCellValueFactory(new PropertyValueFactory<Mokitt, String>("mokkinimi"));
        katuosoiteColumn.setCellValueFactory(new PropertyValueFactory<Mokitt, String>("osoite"));
        kuvausColumn.setCellValueFactory(new PropertyValueFactory<Mokitt, String>("kuvausmokki"));

        palveluColumn.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("palvelunimi"));
        hintaColumn.setCellValueFactory(new PropertyValueFactory<Palvelut, Double>("hinta"));
        kuvausPColumn.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("kuvauspalvelu"));

        haeTiedot();
        haeTiedot2();
        tietokantayhteys = new Tietokanta();

    }

    private void haeTiedot(){
        Tietokanta tietokantayhteys = new Tietokanta();
        String qu = "SELECT * FROM mokki";
        ResultSet set = tietokantayhteys.execQuery(qu); //käytetään tietokantayhteys-luokan metodia
        try{
            atribuutit.clear();
            while(set.next()){ //luetaan rivit ja tallennetaan uusiin muuttujiin tietokannan tiedot
                String mokkinimi = set.getString("mokkinimi");
                String osoite = set.getString("katuosoite");
                String kuvausmokki = set.getString("kuvaus");


                atribuutit.add(new Mokitt(mokkinimi,osoite,kuvausmokki));
                taulukko.setItems(atribuutit);

            }
        }catch (SQLException e){
            Logger.getLogger(mokitController.class.getName()).log(Level.SEVERE, null, "ex");
        }
        taulukko.setItems(atribuutit); //asetetaan taulukkoon lista, johon tiedot kerätty
    }

    ObservableList<Palvelut> atribuutit2 = FXCollections.observableArrayList();

    private void haeTiedot2(){
        Tietokanta tietokantayhteys = new Tietokanta();
        String qu = "SELECT * FROM palvelu";
        ResultSet set = tietokantayhteys.execQuery(qu); //käytetään tietokantayhteys-luokan metodia
        try{
            atribuutit2.clear();
            while(set.next()){ //luetaan rivit ja tallennetaan uusiin muuttujiin tietokannan tiedot
                int palveluid = set.getInt("palvelu_id");
                String palvelunimi = set.getString("nimi");
                String kuvauspalvelu = set.getString("kuvaus");
                double hinta = set.getDouble("hinta");


                atribuutit2.add(new Palvelut(palveluid, palvelunimi,hinta, kuvauspalvelu));
                //taulukko2.getItems().clear();
                taulukko2.setItems(atribuutit2);

            }
        }catch (SQLException e){
            Logger.getLogger(mokitController.class.getName()).log(Level.SEVERE, null, "ex");
        }
        taulukko2.setItems(atribuutit2); //asetetaan taulukkoon lista, johon tiedot kerätty
    }

    //yhdistetään tietokantaan
    Tietokanta tietokantayhteys;


    public void tyhjennamokkiOnAction(javafx.event.ActionEvent event) {
        tyhjennamokki.setOnAction(e -> {
            nimikentta.clear();
            osoitekentta.clear();
            kuvauskentta.clear();
            henkilokentta.clear();
            varustelukenttä.clear();
            toimintaaluekentta.clear();
        });

    }

    public void tyhjennapalveluOnAction(javafx.event.ActionEvent event) {
        tyhjennapalvelu.setOnAction(e -> {
            palvelunimikentta.clear();
            aluekentta.clear();
            kuvauskenttakaksi.clear();
            hintakentta.clear();
            alvkentta.clear();
        });
    }
//lisätään toiminto jolla lisätään tietokantaan tietoa
    @FXML
    public void lisaapalvelu(ActionEvent e) {
        String pnimi = palvelunimikentta.getText();
        String pkuvaus = kuvauskenttakaksi.getText();
        int hinta = Integer.parseInt(hintakentta.getText());
        int alv = Integer.parseInt(alvkentta.getText());


        String qu = "INSERT INTO palvelu(palvelu_id, toimintaalue_id, nimi, tyyppi, kuvaus, hinta, alv) VALUES("
                + "'" + 7 + "',"
                + "'" + 1 + "',"
                + "'" + pnimi + "',"
                + "'" + 5 + "',"
                + "'" + pkuvaus + "',"
                + "'" + hinta + "',"
                + "'" + alv + "'"
                + ")";

        System.out.println(qu);

        if (tietokantayhteys.execAction(qu)) {
            haeTiedot2();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Onnistui!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("virhe");
            alert.showAndWait();
        }

    }

    @FXML
    public void lisaamokki(ActionEvent e) {
        String nimi = nimikentta.getText();
        String osoite = osoitekentta.getText();
        String kuvaus = kuvauskentta.getText();
        int henkilot = Integer.parseInt(henkilokentta.getText());
        String varustelu = varustelukenttä.getText();


        String qu = "INSERT INTO mokki(toimintaalue_id, postinro, mokkinimi, katuosoite, kuvaus, henkilomaara, varustelu) VALUES("
                + "'" + 1 + "',"
                + "'" + 10050 + "',"
                + "'" + nimi + "',"
                + "'" + osoite + "',"
                + "'" + kuvaus + "',"
                + "'" + henkilot + "',"
                + "'" + varustelu + "'"
                + ")";


        if (tietokantayhteys.execAction(qu)) {
            haeTiedot();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Onnistui!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("virhe");
            alert.showAndWait();
        }
    }

    @FXML
    public void poistapalvelu(ActionEvent e) {
        Palvelut valittu = taulukko2.getSelectionModel().getSelectedItem();


        try {
            String qu = "DELETE FROM palvelu WHERE palvelu_id = '" + valittu.getPalveluid() + "'";

            if (tietokantayhteys.execAction(qu)) {
                haeTiedot2();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Onnistui!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("virhe");
                alert.showAndWait();
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    @FXML
    public void poistamokki(ActionEvent e) {
        String nimi = nimikentta.getText();

        try {
            String qu = "DELETE FROM mokki WHERE mokkinimi = '" + nimi + "'";

            if (tietokantayhteys.execAction(qu)) {
                haeTiedot();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Onnistui!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("virhe");
                alert.showAndWait();
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    @FXML
    public void muokkaapalvelu(ActionEvent e) {
        String pnimi = palvelunimikentta.getText();
        String kuvausP = kuvauskenttakaksi.getText();
        int hintaP = Integer.parseInt(hintakentta.getText());
        int alvP = Integer.parseInt(alvkentta.getText());

        try {
            atribuutit2.clear();
            String qu = "UPDATE palvelu SET kuvaus = '" + kuvausP + "', hinta = '" + hintaP + "', alv = '" + alvP + "' WHERE nimi = '" + pnimi + "'";

            if (tietokantayhteys.execAction(qu)) {
                haeTiedot2();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Onnistui!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("virhe");
                alert.showAndWait();
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    @FXML
    public void muokkaamokki(ActionEvent e) {
        String nimi = nimikentta.getText();
        String osoite = osoitekentta.getText();
        String kuvausM = kuvauskentta.getText();
        int henkilot = Integer.parseInt(henkilokentta.getText());


        try {
            atribuutit.clear();
            String qu = "UPDATE mokki SET katuosoite = '" + osoite + "', kuvaus = '" + kuvausM + "', henkilomaara = '" + henkilot + "' WHERE mokkinimi = '" + nimi + "'";

            if (tietokantayhteys.execAction(qu)) {
                haeTiedot();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Onnistui!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("virhe");
                alert.showAndWait();
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

}