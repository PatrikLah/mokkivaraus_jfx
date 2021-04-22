package uusivaraus;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;


public class Controller {
    private Mokkivaraus.Main main;

    @FXML
    private void goVarausKalenteri() throws Exception{
        main.naytaUusiVaraus();
    }

    @FXML
    private ComboBox AlueComboBox;
    @FXML
    private ComboBox MökkiComboBox;
    @FXML
    private TextArea tiedotTextArea;
    @FXML
    private TextField nimiTextField;
    @FXML
    private String nimitieto =" ";
    @FXML
    private String aluetieto= " ";
    @FXML
    private String mokkitieto= " ";
    @FXML
    private DatePicker alkuDatePicker;
    @FXML
    private DatePicker loppuDatePicker;
    @FXML
    private String alkutieto="";
    @FXML
    private String lopputieto ="";



    // alasvetolaatikkoon listat, jotta niissä näkyy vaihtoehdot
    ObservableList<String> AlueComboBoxLista = FXCollections.observableArrayList("Levi", "Ruka", "Ylläs");
    ObservableList<String> MokkiComboBoxLista = FXCollections.observableArrayList("Mökki 1", "Mökki 2", "Mökki 3");

    public void pressButton(ActionEvent event){
        System.out.println("Onnistui");
    }

    //metodi joka näyttää syötetyt tiedot tekstikentässä, jokaisella täytettävällä laatikolla oma kuuntelija
    @FXML
    private void naytaTiedot(){
        nimiTextField.textProperty().addListener( (option, vanha, uusi) -> {
            nimitieto = uusi;
        });
        AlueComboBox.getSelectionModel().selectedItemProperty().addListener( (option, vanha, uusi) -> {
            aluetieto = uusi.toString();
        });
        MökkiComboBox.getSelectionModel().selectedItemProperty().addListener( (option, vanha, uusi) -> {
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
        tiedotTextArea.setText("Nimi: \n" + nimitieto + "\nAlue: \n" + aluetieto + "\nMökki: \n" + mokkitieto
        + "\nValitut päivämäärät:\n" +alkutieto + " - " + lopputieto);

    }

    @FXML
    public void initialize(){
        //asetetaan alasveto laatikkoon alkuarvo ja lista jossa on vaihdoehdot
       AlueComboBox.setValue("Levi");
       AlueComboBox.setItems(AlueComboBoxLista);

       MökkiComboBox.setValue("Mökki 1");
       MökkiComboBox.setItems(MokkiComboBoxLista);

    }


}
