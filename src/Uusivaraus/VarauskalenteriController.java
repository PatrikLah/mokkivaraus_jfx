package Uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class VarauskalenteriController {


    @FXML
    private TableView<Henkilo> tableView;
    @FXML
            private TableColumn<Henkilo, Number> varausC;
    @FXML
            private TableColumn<Henkilo, Number> palveluC;
    @FXML
    private TableColumn<Henkilo, String> pvmC;
    @FXML
            private ComboBox varaus_id;
    @FXML
            private ComboBox palvelu_id;
    @FXML
            private DatePicker palvelupvm;
    @FXML
    private Button lisaa;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == lisaa){
            insertRecord();
        }
    }
    //lisätään listan tiedot taulukkoon
    public void initialize(){
        System.out.println("onnistuuuuu");
        palvelu_id.setItems(getPalvelut());
        varaus_id.setItems(getVaraus());
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
    public ObservableList<Henkilo> getVaraukset(){
        ObservableList<Henkilo> henkilolista = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM varauspalveluntiedot";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Henkilo hlo;
            while(rs.next()){
                hlo = new Henkilo(rs.getInt("varaus_id"), rs.getInt("palvelu_id"), rs.getString("varattu_pvm"));
                henkilolista.add(hlo);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return henkilolista;
    }
    public void naytaHenkilot(){
        ObservableList<Henkilo> list = getVaraukset();
        varausC.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getVaraus()));
        palveluC.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getPalvelu()));
        pvmC.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPvm()));

        tableView.setItems(list);
    }

    public ObservableList<String> getPalvelut(){
        ObservableList<String> palvelulista = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String sql = "SELECT palvelu_id FROM palvelu";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String palvelu = rs.getString("palvelu_id");
                palvelulista.add(palvelu);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return palvelulista;
    }

    public ObservableList<String> getVaraus(){
        ObservableList<String> palvelulista = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String sql = "SELECT varaus_id FROM varaus";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String palvelu = rs.getString("varaus_id");
                palvelulista.add(palvelu);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return palvelulista;
    }

    private void insertRecord(){
        String query = "INSERT INTO varauksen_palvelut(varaus_id, palvelu_id, lkm)" +
                " VALUES ('" + varaus_id.getValue().toString() + "','" + palvelu_id.getValue().toString() + "', 1 )";
        executeQuery(query);
        naytaHenkilot();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
