package uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.DatePicker;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Henkilo {
   /* private SimpleIntegerProperty nimi;
    private SimpleIntegerProperty alue;
 //   private SimpleStringProperty mokki;
    private SimpleStringProperty alkupvm;
    private SimpleStringProperty loppupvm; */

    final private SimpleIntegerProperty varaus;
    final private SimpleIntegerProperty asiakas;
    final private SimpleIntegerProperty mokki;

    final private SimpleStringProperty varattu;
   // final private SimpleStringProperty  vahvistettu;
    final private SimpleStringProperty  varAlku;
    final private SimpleStringProperty  varLoppu;




    /*  public Henkilo(String nimi, String alue, String mokki, String alkupvm, String loppupvm) {
        this.nimi = new SimpleStringProperty(nimi);
        this.alue = new SimpleStringProperty(alue);
        this.mokki = new SimpleStringProperty(mokki);
        this.alkupvm = new SimpleStringProperty(alkupvm);
        this.loppupvm = new SimpleStringProperty(loppupvm);
    }*/
    public Henkilo(Integer varaus, Integer asiakas, Integer mokki, String varattu, String varAlku, String varLoppu) {
        this.varaus = new SimpleIntegerProperty(varaus);
        this.asiakas = new SimpleIntegerProperty(asiakas);
        this.mokki = new SimpleIntegerProperty(mokki);
        this.varattu = new SimpleStringProperty(varattu);
        //this.vahvistettu = new SimpleStringProperty(varattu);
        this.varAlku = new SimpleStringProperty (varAlku);
        this.varLoppu = new SimpleStringProperty(varLoppu);

    }
    public Integer getVaraus(){
        return varaus.get();
    }
    public Integer getAsiakas(){
        return asiakas.get();
    }
    public Integer getMokki(){
        return mokki.get();
    }
    public String getVarattu(){
        return varattu.get();
    }
    public String getVarAlku(){
        return varAlku.get();
    }
    public String getVarLoppu(){
        return varLoppu.get();
    }
  /*  public  getAlue() {
        return alue.get();
    }

    public void setAlue(String alue) {
    }

    public String getMokki() {
        return mokki.get();
    }

    public void setMokki(String mokki) {
    }

    public String getAlkupvm() {
        return alkupvm.get();
    }

    public void setAlkupvm(String alkupvm) {
    }

    public String getLoppupvm() {
        return loppupvm.get();
    }

    public void setLoppupvm(String loppupvm) {
    }

    public String getNimi() {
        return nimi.get();
    }

    public void setNimi(String nimi) {
    } */
}
