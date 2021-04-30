package uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VarauskalenteriController {

    Tietokantayhteys tietokantayhteys;
    private Mokkivaraus.Main main;
    private uusivaraus uusivaraus;

    @FXML
    private TableView<Henkilo> tableView;
    @FXML
    private TableColumn<Henkilo, Number> varausIDColumn;
    @FXML
    private TableColumn<Henkilo, String> etuNimiColumn;
    @FXML
    private TableColumn<Henkilo, String> sukuNimiColumn;
    @FXML
    private TableColumn<Henkilo, String> aNimiColumn;
    @FXML
    private TableColumn<Henkilo, String> mokkiColumn;
    @FXML
    private TableColumn<Henkilo, String> alkuColumn;
    @FXML
    private TableColumn<Henkilo, String> loppuColumn;
    @FXML
    private MenuItem poistaMenuItem;

    //toisen välilehden palvelu-kalenteri
    @FXML
    private TableView<Henkilo> tableViewPalvelut;
    @FXML
    private TableColumn<Henkilo, Number> varaus2Column;
    @FXML
    private TableColumn<Henkilo, String> eNimi2Column;
    @FXML
    private TableColumn<Henkilo, String> sNimi2Column;
    @FXML
    private TableColumn<Henkilo, String> palvelutColumn;
    @FXML
    private TableColumn<Henkilo, String> paivamaaraColumn;

    // lisätään tietokannasta haetut tiedot tähän, jotta voidaan myöhemmin muokata niitä
    ObservableList<Henkilo> lista = FXCollections.observableArrayList();
    ObservableList<Henkilo> lista2 = FXCollections.observableArrayList();


    //lisätään listan tiedot taulukkoon
    public void initialize(){
        initCol();
        tietokantayhteys = new Tietokantayhteys();
        lataaTiedot();
        lataaPalvelut();
    }
    // jokaisen sarakkeen tiedot
    private void initCol(){
        varausIDColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getVarausId()));
        etuNimiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEnimi()));
        sukuNimiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSnimi()));
        aNimiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAlue()));
        mokkiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMokki()));
        alkuColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAlkupvm()));
        loppuColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLoppupvm()));

        varaus2Column.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getVarausId2()));
        eNimi2Column.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEnimi2()));
        sNimi2Column.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSnimi2()));
        palvelutColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPalvelu()));
        paivamaaraColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPvm()));


    }
    //metodi tietojen lataamiseen tietokannasta
    private void lataaTiedot(){
        Tietokantayhteys tietokantayhteys = new Tietokantayhteys(); //käytetään luokkaan tietokantayhteys
        String qu = "SELECT * FROM varaustiedot"; //sql komento
        ResultSet set = tietokantayhteys.execQuery(qu); //käytetään tietokantayhteys-luokan metodia
        try{
            while(set.next()){ //luetaan rivit ja tallennetaan uusiin muuttujiin tietokannan tiedot
                Integer varausID = set.getInt("varaus_id");
                String etunimi = set.getString("Etunimi");
                String sukunimi = set.getString("sukunimi");
                String aluenimi = set.getString("nimi");
                String mokkinimi = set.getString("mokkinimi");
                String varattuAlku = set.getString("varattu_alkupvm");
                String varattuLoppu = set.getString("varattu_loppupvm");
                //lisätään obsevablelistaan tiedot tietokannasta haetut tiedot
                lista.add(new Henkilo(varausID,etunimi,sukunimi,aluenimi,mokkinimi, varattuAlku, varattuLoppu));

            }
        }catch (SQLException e){
            Logger.getLogger(uusivarausController.class.getName()).log(Level.SEVERE, null, "ex");
        }
        tableView.setItems(lista); //asetetaan taulukkoon lista, johon tiedot kerätty
    }
    private void lataaPalvelut(){
        Tietokantayhteys tietokantayhteys = new Tietokantayhteys(); //käytetään luokkaan tietokantayhteys
        String qu = "SELECT * FROM palvelutiedot"; //sql komento
        ResultSet set = tietokantayhteys.execQuery(qu); //käytetään tietokantayhteys-luokan metodia
        try{
            while(set.next()){ //luetaan rivit ja tallennetaan uusiin muuttujiin tietokannan tiedot
                Integer varausID = set.getInt("varaus_id");
                String etunimi = set.getString("Etunimi");
                String sukunimi = set.getString("sukunimi");
                String palvelunimi = set.getString("nimi");
                String varattu = set.getString("varattu_pvm");

                //lisätään obsevablelistaan tiedot tietokannasta haetut tiedot
                lista2.add(new Henkilo(varausID, etunimi, sukunimi,palvelunimi,varattu));

            }
        }catch (SQLException e){
            Logger.getLogger(uusivarausController.class.getName()).log(Level.SEVERE, null, "ex");
        }
        tableViewPalvelut.setItems(lista2); //asetetaan taulukkoon lista, johon tiedot kerätty
    }

    // pääikkunasta pääsee varauskalenteriin
    @FXML
    private void goVarausKalenteri() throws Exception{
        main.showMainItems();
    }

    //metodi jolla poisteaan varauskalenterista varaus
    public void poistaVaraus(ActionEvent event){
        //haetaan valittu rivi
        Henkilo valittu = tableView.getSelectionModel().getSelectedItem();
        if(valittu == null){
            //ilmoitus jos yhtäkään riviä ei ole valittu ja yritetään poistaa
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Mitään ei ole valittuna. Valitse jonkin rivi");
            alert.showAndWait();
            return;
        }
        // varmistus ilmoitus
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Poista");
        alert.setContentText("Oletko varma että haluat poistaa rivin?");
        Optional<ButtonType> vastaus = alert.showAndWait();
        //käydään läpi vaihtoehdot kummatkin painike vaihtoehdot
        if(vastaus.get() == ButtonType.OK){
            Boolean tulos = tietokantayhteys.poistaRivi(valittu);
            if(tulos){ //jos painetaan ok, vielä erillinen vahvistus poistosta
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("Rivi on poistettu");
                alert2.showAndWait();
                lista.remove(valittu);
            }
        } else { //jos painetaan "cancel", ilmoitus että perutaan
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Poistaminen peruutettu");
            alert1.showAndWait();
        }
    }
    public void muokkaaVarausta(ActionEvent event){


    }


}
