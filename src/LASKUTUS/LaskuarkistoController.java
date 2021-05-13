package LASKUTUS;

 import javafx.beans.property.SimpleDoubleProperty;
 import javafx.beans.property.SimpleIntegerProperty;
 import javafx.beans.property.SimpleStringProperty;
 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.*;
 import javafx.scene.control.Button;


 import java.sql.*;
 import java.util.Optional;
 import java.util.logging.Level;
 import java.util.logging.Logger;


public class LaskuarkistoController {

    Tietokantayhteys tietokantayhteys;
    private Main main;


 //buttonit:
 @FXML
 private Button poistalaskuButton;

 //comboboxit:
 //@FXML
 //private ComboBox varausIDcombo;
 //@FXML
 //private ComboBox laskuIDcombo;
 @FXML
 private ComboBox hakucombo;

 //comboboxien listat:
 ObservableList<String> hakucombolista = FXCollections.observableArrayList("Lasku-ID:n mukaan", "Varaus-ID:n mukaan", "Päivämäärän mukaan");


 @FXML
 public void initializecombo(){
  hakucombo.setValue(" ");
  hakucombo.setItems(hakucombolista);

  tietokantayhteys = new Tietokantayhteys();
 }

 @FXML
 private TableView<Lasku> tableView;
 @FXML
 private TableColumn<Lasku, Number> laskuIDColumn;
 @FXML
 private TableColumn<Lasku, Number> varausIDColumn;
 /**@FXML
 private TableColumn<Lasku, String> sukunimiColumn;
 @FXML
 private TableColumn<Lasku, String> etunimiColumn;*/
 @FXML
 private TableColumn<Lasku, Number> summaColumn;
 @FXML
 private TableColumn<Lasku, Number> alvColumn;
 /**@FXML
 private TableColumn<Lasku, String> alkupvmColumn;
 @FXML
 private TableColumn<Lasku, String> loppupvmColumn;
 */


 // lisätään tietokannasta haetut tiedot tähän, jotta voidaan myöhemmin muokata niitä
 ObservableList<Lasku> lista = FXCollections.observableArrayList();



 //lisätään listan tiedot taulukkoon
 public void initialize(){
 initCol();
 haeTiedot();
 tietokantayhteys = new Tietokantayhteys();

 }
 // jokaisen sarakkeen tiedot
 private void initCol(){
 laskuIDColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getLaskuId()));
 varausIDColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getVarausId()));
 //sukunimiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSukunimi()));
 //etunimiColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEtunimi()));
 summaColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getSumma()));
 alvColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getAlv()));
 //alkupvmColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAlkupvm()));
 //loppupvmColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLoppupvm()));

 }
 //metodi tietojen hakemiseen tietokannasta
 private void haeTiedot(){
 Tietokantayhteys tietokantayhteys = new Tietokantayhteys();
 String qu = "SELECT * FROM lasku";
 ResultSet set = tietokantayhteys.execQuery(qu); //käytetään tietokantayhteys-luokan metodia
 try{
 while(set.next()){ //luetaan rivit ja tallennetaan uusiin muuttujiin tietokannan tiedot
 Integer laskuID = set.getInt("lasku_id");
 Integer varausID = set.getInt("varaus_id");
 //String etunimi = set.getString("etunimi");
 //String sukunimi = set.getString("sukunimi");
 double summa = set.getDouble("summa");
 double alv = set.getDouble("alv");
 //String varattuAlku = set.getString("varattu_alkupvm");
 //String varattuLoppu = set.getString("varattu_loppupvm");

 lista.add(new Lasku(laskuID,varausID,summa,alv));

 }
 }catch (SQLException e){
 Logger.getLogger(uusilaskuController.class.getName()).log(Level.SEVERE, null, "ex");
 }
 tableView.setItems(lista); //asetetaan taulukkoon lista, johon tiedot kerätty
 }

 /**public Connection conn;

 public Connection getConnection(){
  try{
   Class.forName("com.mysql.cj.jdbc.Driver");
   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vn", "root", "mansikka18");
   if (conn!=null) {
    System.out.println("Yhteys muodostettu!");
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return conn;
 }


 public ObservableList<Integer> getVarausIDComboLista(){
  ObservableList<Integer> varausIDpudotusvalikko = FXCollections.observableArrayList();
  Connection conn = getConnection(); //getConnection();?
  String sqlhakulausevarausID = "SELECT varaus_id FROM varaus";
  Statement st;
  ResultSet rs;
  try{
   st = conn.createStatement();
   rs = st.executeQuery(sqlhakulausevarausID);
   while(rs.next()){
    Integer varausIDalkio = rs.getInt("varaus_id");
    varausIDpudotusvalikko.add(varausIDalkio);
   }
  }catch(Exception ex){
   ex.printStackTrace();
  }
  return varausIDpudotusvalikko;
 }
 public ObservableList<Integer> getLaskuIDComboLista(){
  ObservableList<Integer> laskuIDpudotusvalikko = FXCollections.observableArrayList();
  Connection conn = getConnection(); //getConnection();?
  String sqlhakulauselaskuID = "SELECT lasku_id FROM lasku";
  Statement st;
  ResultSet rs;
  try{
   st = conn.createStatement();
   rs = st.executeQuery(sqlhakulauselaskuID);
   while(rs.next()){
    Integer laskuIDalkio = rs.getInt("lasku_id");
    laskuIDpudotusvalikko.add(laskuIDalkio);
   }
  }catch(Exception ex){
   ex.printStackTrace();
  }
  return laskuIDpudotusvalikko;
 }



 @FXML
 public void initialize() {
  varausIDcombo.setItems(getVarausIDComboLista());
  laskuIDcombo.setItems(getLaskuIDComboLista());
 }*/


 /**@FXML
 private void showLaskuArkisto() throws Exception{
 main.showPaaPainikkeet();
 }*/

 //metodi jolla poistetaan laskuarkistosta lasku
 public void poistaLasku(ActionEvent event){
 //haetaan valittu rivi
 Lasku valittu = tableView.getSelectionModel().getSelectedItem();
 if(valittu == null){
 //ilmoitus jos yhtäkään riviä ei ole valittu ja yritetään poistaa
 Alert alert = new Alert(Alert.AlertType.ERROR);
 alert.setContentText("Mitään ei ole valittuna. Valitse jonkin rivi");
 alert.showAndWait();
 return;
 }
 // varmistus
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 alert.setTitle("Poista");
 alert.setContentText("Oletko varma että haluat poistaa rivin?");
 Optional<ButtonType> vastaus = alert.showAndWait();
 //käydään läpi vaihtoehdot kummatkin painike vaihtoehdot
 if(vastaus.get() == ButtonType.OK){
 Boolean tulos = tietokantayhteys.poistaLasku(valittu);
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

 }



