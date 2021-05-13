package LASKUTUS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class uusilaskuController {

    @FXML
    private static String varausIDtieto;
    @FXML
    private static String laskuIDtieto;
    @FXML
    private static String alkupvmtieto;
    @FXML
    private static String loppupvmtieto;
    @FXML
    private static String summatieto;
    @FXML
    private static String alvtieto;

    //tekstikentät eli textfield:
    @FXML
    private TextField varausIDField;
    @FXML
    private TextField laskuIDField;
    @FXML
    private TextField summaField;
    @FXML
    private TextField alvField;


    //tekstialue eli text area:
    @FXML
    private TextArea tekstialue;



    //datepickerit:
    @FXML
    private DatePicker varauksenalkupvmDate;
    @FXML
    private DatePicker varauksenloppupvmDate;

    //checkboxit:
    @FXML
    private CheckBox maksettuBox;
    @FXML
    private CheckBox eimaksettuBox;

    //comboboxit:
    /**@FXML
    private ComboBox varausIDcombo;*/

    Tietokantayhteys tietokantayhteys;


    //METODIT-->



     //metodi checkboxien valintaan vain yksi kerrallaan:
    @FXML
    private void handleMaksettuBox() {
        if(maksettuBox.isSelected()) {
            eimaksettuBox.setSelected(false);
        }
    }
    @FXML
    private void handleEiMaksettuBox() {
        if(eimaksettuBox.isSelected()) {
            maksettuBox.setSelected(false);
        }
    }
    //comboboxien listat:
    //ObservableList<Integer> VarausIDComboLista = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        //varausIDcombo.setValue(" ");
        //varausIDcombo.setItems(VarausIDComboLista);

        tietokantayhteys = new Tietokantayhteys();
    }


    //painikkeet/buttonit:
    @FXML
    private Button tallennalaskuButton;
    @FXML
    private Button luoPDFButton;
    @FXML
    private Button tyhjennaButton;

   //@Override
    public void initialize(URL url, ResourceBundle rb) {
        tietokantayhteys = new Tietokantayhteys();
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
    @FXML
    public void initializecombo() {
        varausIDcombo.setItems(getVarausIDComboLista());

    }*/

    //toiminnot (OnAction) buttoneille:
    //1. uuden laskun tietojen lisäys tietokantaan
    //2. luo PDF-tiedosto laskusta (tekstinäkymästä?)
    //3. peru laskun lisays (??)
    @FXML
    public void tallennalasku(ActionEvent event) { //1.
        //tallennetaan syöttökentistä tiedot uusiin muuttujiin:

        Integer tiedotVarausID = Integer.parseInt(varausIDField.getText());
        Integer tiedotLaskuID = Integer.parseInt(laskuIDField.getText());
        //Integer tiedotVarausID = Integer.parseInt(varausIDField.getText());
        double tiedotSumma = Double.parseDouble(summaField.getText());
        double tiedotAlv = Double.parseDouble(alvField.getText());


        /**String tiedotAlku = varauksenalkupvmDate.getValue().toString();
        String tiedotLoppu = varauksenloppupvmDate.getValue().toString();
        String tiedotLisapalvelut = lisapalvelutField.getValue().toString();
         }*/


        LocalDateTime paiva = LocalDateTime.now();

        //lisätään tiedot tietokannan lasku-tauluun:
        String qu = "INSERT INTO lasku(lasku_id, varaus_id, summa, alv) VALUES("
                + "" + tiedotLaskuID + "," //+ "" + 1007 + ","
                + "" + tiedotVarausID + ","  //+ "" + 3 + ","
                + "" + tiedotSumma + ","
                + "" + tiedotAlv + ""
                + ")";

        //lisätään tiedot tietokannan asiakas-tauluun:
        /**String qu2 = "INSERT INTO asiakas(asiakas_id, postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro) VALUES("
                + "'" + tiedotPostinro + "',"
                + "'" + tiedotEtunimi + "',"
                + "'" + tiedotSukunimi + "',"
                + "'" + tiedotOsoite + "',"
                + "'" + tiedotSposti + "',"
                + "'" + tiedotPuhnro + "'"
                + ")";*/

        System.out.println(qu);
        //System.out.println(qu2);
        /**try {
         PreparedStatement statement = tietokantayhteys.setTietokantayhteys().prepareStatement(qu2);
         statement.executeUpdate();
         }catch (SQLException e){
         e.printStackTrace();
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("Virhe tapahtui");
         alert.showAndWait();
         }*/
        if (tietokantayhteys.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Onnistui");
            alert.showAndWait();

        } else { //jos metodi palauttaa false:
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Virhe");
            alert.showAndWait();
        }
    }

    @FXML
    private void luoPDFtiedosto(ActionEvent event) { //3.

    }

    @FXML
    private void tyhjennaOnAction(ActionEvent event) { //2.
        tyhjennaButton.setOnAction(e -> {
            varausIDField.clear();
            laskuIDField.clear();
            summaField.clear();
            alvField.clear();
        });

    }

    //metodi joka näyttää syötetyt tiedot text areassa, jokaisella text fieldilla oma kuuntelija
    @FXML
    private void naytaTiedotTextareassa(){
        varausIDField.textProperty().addListener( (option, vanha, uusi) -> {
           varausIDtieto = uusi;
        });
        laskuIDField.textProperty().addListener( (option, vanha, uusi) -> {
            laskuIDtieto = uusi;
        });
        varauksenalkupvmDate.valueProperty().addListener((option, vanha, uusi) -> {
            DateTimeFormatter muokattu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            alkupvmtieto = uusi.format(muokattu).toString();
        });
        varauksenloppupvmDate.valueProperty().addListener( (option, vanha, uusi) -> {
            DateTimeFormatter muokattu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            loppupvmtieto = uusi.format(muokattu).toString();
        });
        summaField.textProperty().addListener( (option, vanha, uusi) -> {
            summatieto = uusi;
        });
        alvField.textProperty().addListener( (option, vanha, uusi) -> {
            alvtieto = uusi;
        });
        tekstialue.setText("\nLASKUN TIEDOT: \nVaraus-ID: \n" + varausIDtieto + "\nLasku-ID: \n" + laskuIDtieto + "\nVarauksen alkamisajankohta: \n" + alkupvmtieto
                + "\nVarauksen loppuajankohta:\n" + loppupvmtieto+ "\nLaskun loppusumma: \n"+ summatieto
                +"\nAlv.:\n"+ alvtieto);

    }



    //painikkeiden metodit:
    /**@FXML
    private void goPaluu() throws IOException { //toiminto, jotta "paluu"-painikkeella pääsee aloitussivulle
    main.showPaaPainikkeet();
    }*/

    }


