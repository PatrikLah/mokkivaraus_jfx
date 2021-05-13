package asiakashallinta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;


public class AsiakashallintaController {

    @FXML
    private Button tallennaButton;
    @FXML
    private Button peruutaButton;
    @FXML
    private TextField etunimiTextField;
    @FXML
    private TextField sukunimiTextField;
    @FXML
    private TextField osoiteTextField;
    @FXML
    private TextField postinumeroTextField;
    @FXML
    private TextField postitoimipaikkaTextField;
    @FXML
    private TextField sahkopostiTextField;
    @FXML
    private TextField puhelinnumeroTextField;
    private Integer asiakasID;

    // Tallenna-painikkeen toiminta:
    // Tarkistaa jokaisen tekstikentän ja heittää ilmoituksen,
    // jos kenttiä on täytetty väärin.
    // Muutoin yrittää tallentaa tiedot tietokantaan
    public void tallennaButtonOnAction(ActionEvent event) {
        Alert virheilmoitus = new Alert(Alert.AlertType.ERROR);
        virheilmoitus.setHeaderText("Seuraavat kentät on täytetty virheellisesti:");
        // Alustetaan virheilmoitus
        virheilmoitus.setContentText("");

        // Tarkistetaan kentät
        if (etunimiTextField.getText().isBlank() || etunimiTextField.getText().length() > 20) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Etunimi\n");
        }
        if (sukunimiTextField.getText().isBlank() || sukunimiTextField.getText().length() > 40) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Sukunimi\n");
        }
        if (osoiteTextField.getText().isBlank() || osoiteTextField.getText().length() > 40) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Osoite\n");
        }
        if (postinumeroTextField.getText().isBlank() || postinumeroTextField.getText().length() != 5) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Postinumero\n");
        }
        if (postitoimipaikkaTextField.getText().isBlank() || postitoimipaikkaTextField.getText().length() > 45) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Postitoimipaikka\n");
        }
        if (sahkopostiTextField.getText().isBlank() || sahkopostiTextField.getText().length() > 50) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Sähköposti\n");
        }
        if (puhelinnumeroTextField.getText().isBlank() || puhelinnumeroTextField.getText().length() > 15) {
            virheilmoitus.setContentText(virheilmoitus.getContentText() + "Puhelinnumero\n");
        }

        // Virheilmoitus tai tietojen tallentaminen tietokantaan
        if (!virheilmoitus.getContentText().isBlank()) {
            virheilmoitus.showAndWait();
        }
        else {
            String SQLkomento;

            Tietokantayhteys yhteys = new Tietokantayhteys();
            Connection yhdistaDB = yhteys.getYhteys();
            // Testataan updatea kun asiakasID = 11
            asiakasID = 11;
            if (asiakasID == null) {
                SQLkomento = "INSERT INTO `vn`.`asiakas` (`postinro`, `etunimi`, `sukunimi`, `lahiosoite`, `email`, `puhelinnro`) VALUES " +
                        "('" + postinumeroTextField.getText() +
                        "', '" + etunimiTextField.getText() +
                        "', '" + sukunimiTextField.getText() +
                        "', '" + osoiteTextField.getText() +
                        "', '" + sahkopostiTextField.getText() +
                        "', '" + puhelinnumeroTextField.getText() +
                        "')";

                try {
                    Statement statement = yhdistaDB.createStatement();
                    statement.executeUpdate(SQLkomento);
                    // Tähän oikea asiakasID. Nyt vain estää syöttämästä samoja tietoja uudestaan
                    asiakasID = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {


                SQLkomento = "UPDATE `asiakas`" +
                        " SET `postinro` = '" + postinumeroTextField.getText() +
                        "', `etunimi` = '" + etunimiTextField.getText() +
                        "', `sukunimi` = '" + sukunimiTextField.getText() +
                        "', `lahiosoite` = '" + osoiteTextField.getText() +
                        "', `email` = '" + sahkopostiTextField.getText() +
                        "', `puhelinnro` = '" + puhelinnumeroTextField.getText() +
                        "' WHERE `asiakas_id` = " + asiakasID;


                try {
                    Statement statement = yhdistaDB.createStatement();
                    statement.executeUpdate(SQLkomento);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void peruutaButtonOnAction(ActionEvent event) {
        // Sulkee ikkunan
        Stage stage = (Stage) peruutaButton.getScene().getWindow();
        stage.close();
        //Kokeilen tässä noutaa asiakkaan tiedot kannasta ennen kuin
        // tähän tulee oikeasti peruutustoiminto
        /*
        Tietokantayhteys yhteys = new Tietokantayhteys();
        Connection yhdistaDB = yhteys.getYhteys();


        asiakasID = 1;
        String kantahaku = "SELECT etunimi " + //", sukunimi, lahiosoite, email, puhelinnro " +
                "FROM asiakas WHERE asiakas_id = " + asiakasID + ";";


        try {
            Statement statement = yhdistaDB.createStatement();
            ResultSet hakutulos = statement.executeQuery(kantahaku);

            while (hakutulos.next()) {
                etunimiTextField.setText(hakutulos.getString("etunimi"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }


}
