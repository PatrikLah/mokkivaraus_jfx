package Hallinta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


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


    @FXML
    public void initialize() {
        tietokantayhteys = new Tietokanta();

    }
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

    //lisätään napit mökkien hallintaan
    /**
     * @FXML private Button poistamokki;
     * @FXML private Button muokkaamokki;
     * @FXML private Button lisaamokki;
     */

    @FXML
    private Button tyhjennamokki;

    //lisätään napit palveluiden hallintaan

    /**
     * @FXML private Button poistapalvelu;
     * @FXML private Button muokkaapalvelu;
     * @FXML private Button lisaapalvelu;
     */
    @FXML
    private Button tyhjennapalvelu;

    //mökin talut
    @FXML
    TableView<Mokitjapalvelut> taulukko;

    @FXML
    private TableColumn <Mokitjapalvelut, String> mokkiColumn;

    @FXML
    private TableColumn <Mokitjapalvelut, String> katuosoiteColumn;

    @FXML
    private TableColumn <Mokitjapalvelut, String> toimintaalueColumn;

    @FXML
    private TableColumn <Mokitjapalvelut, String> kuvausColumn;

    //toiminta-alueen taulut

    @FXML
    private TableColumn <Mokitjapalvelut, String> palveluColumn;
    @FXML
    private TableColumn <Mokitjapalvelut, String> toimintaaluePColumn;

    @FXML
    private TableColumn <Mokitjapalvelut, Integer> hintaColumn;
    @FXML
    private TableColumn <Mokitjapalvelut, String> kuvausPColumn;

    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    ObservableList<Mokitjapalvelut> atribuutit = FXCollections.observableArrayList();

    @FXML
    private  void paivitaTaulukko(){
        try{
            atribuutit.clear();

            String nimi = nimikentta.getText();
            String qu = "SELECT * FROM mokki WHERE mokkinimi = '" + nimi + "'";

            while ((resultSet.next())) {
                atribuutit.add(new Mokitjapalvelut(
                        resultSet.getString("mokkinimi"),
                        resultSet.getString("katuosoite"),
                        resultSet.getString("toimintaalue"),
                        resultSet.getString("kuvaus")));
                taulukko.setItems(atribuutit);

            }

            if (tietokantayhteys.execAction(qu)) {
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

        } catch (Exception e) {
            System.out.println(e);
        }

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
        String pnimi = palvelunimikentta.getText();

        try {
            String qu = "DELETE FROM palvelu WHERE nimi = '" + pnimi + "'";

            if (tietokantayhteys.execAction(qu)) {
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
        int toimintaalueP = Integer.parseInt(aluekentta.getText());
        String kuvausP = kuvauskenttakaksi.getText();
        int hintaP = Integer.parseInt(hintakentta.getText());
        int alvP = Integer.parseInt(alvkentta.getText());

        try {
            String qu = "UPDATE palvelu SET nimi = '" + pnimi + "', kuvaus = '" + kuvausP + "', hinta = '" + hintaP + "', alv = '" + alvP + "' WHERE palvelu_id = " + toimintaalueP;

            if (tietokantayhteys.execAction(qu)) {
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
        int toimintaalueM = Integer.parseInt(toimintaaluekentta.getText());

        try {
            String qu = "UPDATE mokki SET mokkinimi = '" + nimi + "', katuosoite = '" + osoite + "', kuvaus = '" + kuvausM + "', henkilomaara = '" + henkilot + "' WHERE toimintaalue_id = " + toimintaalueM;

            if (tietokantayhteys.execAction(qu)) {
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
