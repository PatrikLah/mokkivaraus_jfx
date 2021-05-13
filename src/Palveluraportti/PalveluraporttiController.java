package Palveluraportti;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PalveluraporttiController {
    @FXML
    private TableView<Palvelut> raporttiTaulukko;
    @FXML
    private TableColumn<Palvelut, String> nimi;
    @FXML
    private TableColumn<Palvelut, Number> lkmColumn;
    @FXML
    private Label label;

    @FXML
    private DatePicker alkupvm;
    @FXML
    private DatePicker loppupvm;
    @FXML
    private Button okNappi;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == okNappi){
            etsi();
        }
    }

    @FXML
    public void initialize(){
        naytaPalvelut();
        System.out.println("onnistuiko");
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vn", "root","Terhi88");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Palvelut> getPalvelut(){
        ObservableList<Palvelut> palvelulista = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM palveluraportti";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Palvelut palvelut;
            while(rs.next()){
                palvelut = new Palvelut(rs.getString("nimi"), rs.getInt("lukumaara"), rs.getInt("hinta"));
                palvelulista.add(palvelut);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return palvelulista;
    }

    public void naytaPalvelut(){
        ObservableList<Palvelut> list = getPalvelut();
        nimi.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getpnimi()));
        lkmColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getplkm()));

        raporttiTaulukko.setItems(list);
    }
    public ObservableList<Palvelut> getVali(){
        ObservableList<Palvelut> palvelulista = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM palveluraportti WHERE varattu_pvm BETWEEN '" +alkupvm.getValue().toString() +"' AND '"
                + loppupvm +"'";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Palvelut palvelut;
            while(rs.next()){
                palvelut = new Palvelut(rs.getString("nimi"), rs.getInt("lukumaara"), rs.getInt("hinta"));
                palvelulista.add(palvelut);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return palvelulista;
    }

    private void etsi(){
        ObservableList<Palvelut> list = getVali();
        nimi.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getpnimi()));
        lkmColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getplkm()));

        raporttiTaulukko.setItems(list);
    }


    public class Palvelut {
        private SimpleStringProperty nimi;
        private SimpleIntegerProperty lkm;

        public Palvelut(String nimi, Integer lkm, Integer hinta){
            this.nimi = new SimpleStringProperty(nimi);
            this.lkm = new SimpleIntegerProperty(lkm);
        }
        public String getpnimi(){
            return nimi.get();
        }
        public Integer getplkm(){
            return lkm.get();
        }
    }
}
