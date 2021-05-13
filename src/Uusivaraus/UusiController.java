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
import java.time.LocalDateTime;

public class UusiController {

        @FXML
        private TableView<Asiakas> table;
        @FXML
        private ComboBox asiakasid;
        @FXML
        private ComboBox<String> alue;
        @FXML
        private ComboBox mokki;
        @FXML
        private DatePicker alku;
        @FXML
        private DatePicker loppu;
        @FXML
        private ComboBox<String> palvelut;
        @FXML
        private DatePicker palvelupvm;

        @FXML
        private Button muokkaa;
        @FXML
        private Button poista;
        @FXML
        private Button lisaa;
        @FXML
        private TableColumn<Asiakas, Number> varausidColumn;
        @FXML
        private TableColumn<Asiakas, Number> asiakasidColumn;
        @FXML
        private TableColumn<Asiakas, String> alueColumn;
        @FXML
        private TableColumn<Asiakas, String> mokkiColumn;
        @FXML
        private TableColumn<Asiakas, String> alkuColumn;
        @FXML
        private TableColumn<Asiakas, String> loppuColumn;

        @FXML
        private void handleButtonAction(ActionEvent event) {

            if(event.getSource() == lisaa){
                insertRecord();
            }else if (event.getSource() == muokkaa){
                updateRecord();
            }else if(event.getSource() == poista){
                deleteButton();
            }
        }

        @FXML
        public void initialize() {
            // TODO
            mokki.setItems(getMokki());
            palvelut.setItems(getPalvelut());
            asiakasid.setItems(getAsiakas());

            naytaHenkilot();
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
        public ObservableList<Asiakas> getHenkilot(){
            ObservableList<Asiakas> henkilolista = FXCollections.observableArrayList();
            Connection conn = getConnection();
            String query = "SELECT * FROM varaustiedot";
            Statement st;
            ResultSet rs;
            try{
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Asiakas asiakas;
                while(rs.next()){
                    asiakas = new Asiakas(rs.getInt("varaus_id"), rs.getInt("asiakas_id"),
                            rs.getString("nimi"),rs.getString("mokkinimi"), rs.getString("varattu_alkupvm"),
                            rs.getString("varattu_loppupvm"));
                    henkilolista.add(asiakas);
                }

            }catch(Exception ex){
                ex.printStackTrace();
            }
            return henkilolista;
        }
        public ObservableList<Integer> getAsiakas(){
            ObservableList<Integer> asiakaslista = FXCollections.observableArrayList();
            Connection conn = getConnection();
            String sql = "SELECT asiakas_id FROM asiakas";
            Statement st;
            ResultSet rs;
            try{
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Integer asiakas = rs.getInt("asiakas_id");
                    asiakaslista.add(asiakas);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return asiakaslista;
        }
        public ObservableList<String> getMokki(){
            ObservableList<String> mokkilista = FXCollections.observableArrayList();
            Connection conn = getConnection();
            String sql = "SELECT mokki_id FROM mokki";
            Statement st;
            ResultSet rs;
            try{
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    String mokki = rs.getString("mokki_id");
                    mokkilista.add(mokki);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return mokkilista;
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

        public void naytaHenkilot(){
            ObservableList<Asiakas> list = getHenkilot();
            varausidColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getVarausId()));
            asiakasidColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAsiakasId()));
            alueColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAlue()));
            mokkiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMokki()));
            alkuColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAlkupvm()));
            loppuColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLoppupvm()));

            table.setItems(list);
        }

        private void insertRecord(){
            LocalDateTime paiva = LocalDateTime.now();
            String query = "INSERT INTO varaus(asiakas_id, mokki_mokki_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm)" +
                    " VALUES ('" + asiakasid.getValue().toString() + "','" + mokki.getValue() + "','" + alku.getValue().toString() +
                    "','"+ paiva.toString() + "','"+ alku.getValue().toString() + "','" + loppu.getValue().toString() + "')";
            executeQuery(query);
            naytaHenkilot();
        }
        private void updateRecord(){
            String query = "UPDATE  varaus SET mokki_mokki_id  = '" + mokki.getValue() + "', varattu_pvm = '" + alku.getValue().toString() + "', varattu_alkupvm = '" +
                    alku.getValue().toString() + "', varattu_loppupvm = '" + loppu.getValue().toString() + "' WHERE asiakas_id = '"
                    + asiakasid.getValue().toString()+ "'";
            executeQuery(query);
            naytaHenkilot();
        }
        private void deleteButton(){
            String query = "DELETE FROM varaus WHERE asiakas_id =" + asiakasid.getValue().toString() + "";
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
