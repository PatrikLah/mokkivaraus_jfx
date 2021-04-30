package uusivaraus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class uusivarausController {
    private Mokkivaraus.Main main;

    @FXML
    private void goVarausKalenteri() throws Exception{
        main.naytaUusiVaraus();
    }

    @FXML
    private ComboBox AlueComboBox;
    @FXML
    private ComboBox MokkiComboBox;
    @FXML
    private TextArea tiedotTextArea;
    @FXML
    private TextField nimiTextField;
    @FXML
    private static String nimitieto;
    @FXML
    private static String aluetieto;
    @FXML
    private static String mokkitieto;
    @FXML
    private DatePicker alkuDatePicker;
    @FXML
    private DatePicker loppuDatePicker;
    @FXML
    private static String alkutieto;
    @FXML
    private static String lopputieto;
    @FXML
    private static String palvelutieto;
    @FXML
    private ComboBox palvelutComboBox;
    @FXML
    private DatePicker palveluDatePicker;
    @FXML
    private static String palvelutietopaiva;

    Tietokantayhteys tietokantayhteys;

    // alasvetolaatikkoon listat, jotta niissä näkyy vaihtoehdot
    ObservableList<String> AlueComboBoxLista = FXCollections.observableArrayList("Levi", "Ruka", "Ylläs","Korvatunturi");
    ObservableList<String> MokkiComboBoxLista = FXCollections.observableArrayList("Kota-mökki", "Kettu", "Lintu", "Revontuli-mökki");
    ObservableList<String> PalvelutComboBoxLista = FXCollections.observableArrayList("Poroajelu", "Shamaanikokemus", "Avantouinti","Moottorikelkkailu", "Palju", "Joulupukkikokemus","Tanssija", "Nuotiokokkailu","Mutahoito");


    //metodi joka näyttää syötetyt tiedot tekstikentässä, jokaisella täytettävällä laatikolla oma kuuntelija
    @FXML
    private void naytaTiedot(){
        nimiTextField.textProperty().addListener( (option, vanha, uusi) -> {
            nimitieto = uusi;
        });
       AlueComboBox.getSelectionModel().selectedItemProperty().addListener( (option, vanha, uusi) -> {
            aluetieto = uusi.toString();
        });
       MokkiComboBox.getSelectionModel().selectedItemProperty().addListener( (option, vanha, uusi) -> {
            mokkitieto = uusi.toString();
        });
        alkuDatePicker.valueProperty().addListener((option, vanha, uusi) -> {
            DateTimeFormatter muokattu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            alkutieto = uusi.format(muokattu).toString();
        });
        loppuDatePicker.valueProperty().addListener( (option, vanha, uusi) -> {
            DateTimeFormatter muokattu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            lopputieto = uusi.format(muokattu).toString();
        });
        palvelutComboBox.getSelectionModel().selectedItemProperty().addListener((option, vanha, uusi) ->{
            palvelutieto = uusi.toString();
        });
        palveluDatePicker.valueProperty().addListener( (option, vanha, uusi) -> {
            DateTimeFormatter muokattu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            palvelutietopaiva = uusi.format(muokattu).toString();
        });
        tiedotTextArea.setText("Nimi: \n" + nimitieto + "\nAlue: \n" + aluetieto + "\nMökki: \n" + mokkitieto
        + "\nValitut päivämäärät:\n" +alkutieto + " - " + lopputieto+"\nValitut palvelut: \n"+palvelutieto
        +"\nPalvelulle valittu päivämäärä:\n"+ palvelutietopaiva);

    }

    @FXML
    public void initialize(){
        //asetetaan alasveto laatikkoon alkuarvo ja lista jossa on vaihdoehdot
       AlueComboBox.setValue(" ");
       AlueComboBox.setItems(AlueComboBoxLista);

       MokkiComboBox.setValue(" ");
       MokkiComboBox.setItems(MokkiComboBoxLista);

       palvelutComboBox.setValue(" ");
       palvelutComboBox.setItems(PalvelutComboBoxLista);

       tietokantayhteys = new Tietokantayhteys();

    }

    @FXML //metodi jolla lisätään tiedot tietokantaan
    public void lisaaTiedot(ActionEvent event){
        //tallennetaan syöttö-kentistä tiedot uusiin muuttujiin
        String tiedotNimi = nimiTextField.getText();  //ei käytössä toistaiseksi
        String tiedotAlue = AlueComboBox.getValue().toString(); //ei käytössä toistaiseksi
        String tiedotMokki = MokkiComboBox.getValue().toString();
        int mokki = 0; // saadaan mökkien oikea tunnus vastaamaan valittua, jotta tietokannassa näkyisi oikea
        if(tiedotMokki == "Kota-mökki"){
            mokki=1;
        } else if(tiedotMokki == "Kettu"){
            mokki =2;
        } else if(tiedotMokki == "Lintu"){
            mokki=3;
        } else if(tiedotMokki == "Revontuli-mökki"){
            mokki=4;
        }
        String tiedotAlku = alkuDatePicker.getValue().toString();
        String tiedotLoppu = loppuDatePicker.getValue().toString();
        String palvelut = palvelutComboBox.getValue().toString(); // ei käytössä toistaiseksi

        //syötetään tiedot tietokannan varaus-tauluun
        String qu = "INSERT INTO varaus(asiakas_id, mokki_mokki_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) VALUES("
               // +"'"+ 1 + "',"
                +"'"+ 1 + "'," //muokattava vielä asiakas_id tieto vastaamaan oikeaa asiakasta!!!!!
                +"'" + mokki + "',"
                +"'" + tiedotAlku + "'," //varattu_pvm > tämä voisi olla kenttä palvelun päivämäärälle
                +"'" + tiedotAlku + "'," //vahvistu_pvm > voisi muokata ottamaan nykyisen päivämäärän!!!
                +"'" + tiedotAlku + "'," //alku_pvm
                +"'" + tiedotLoppu + "'" //loppu_pvm
                +")";
        //lisätään varauksen_palvelut-taulukkoon tietoja
        String qu2 = "INSERT INTO varauksen_palvelut(varaus_id,palvelu_id, lkm) VALUES( "
                +"'" + 21 +"'," // varaus-id täytyy vielä korjata!!
                +"'" + 2+"',"   //palvelu_id täytyy korjata!!
                +"'"+1+"'"
                +")";
        try { //viedään tieto tietokantaan
            PreparedStatement statement = tietokantayhteys.setTietokantayhteys().prepareStatement(qu2);
            statement.executeUpdate();
        }catch (SQLException e){ //jos ei onnistu > virhe ilmoitus
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Virhe");
            alert.showAndWait();
        }
        if(tietokantayhteys.execAction(qu) ) { //tietokantayhteys-luokan metodi, jolla suoritetaan toiminto, jos metodista tulee true > toimii
            //näytetään ilmoitus käyttäjälle, että onnistui
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Onnistui");
            alert.showAndWait();

        }else{ //jos metodi palauttaa false > ilmoitus käyttäjälle ettei onnistunut
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Virhe");
            alert.showAndWait();
        }
    }
}
