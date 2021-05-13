package Uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Asiakas {
    private SimpleIntegerProperty asiakas;
    private int postinumero;
    private String etunimi;
    private String sukunimi;

    private SimpleIntegerProperty varausid;
    private SimpleIntegerProperty asiakasid;
    private SimpleStringProperty enimi;
    private SimpleStringProperty snimi;
    private SimpleStringProperty alue;
    private SimpleStringProperty mokki;
    private SimpleStringProperty alkupvm;
    private SimpleStringProperty loppupvm;

    public Asiakas(Integer asiakas_id) {
        this.asiakas = new SimpleIntegerProperty(asiakas_id);

    }

    public Asiakas(Integer varaus_id, Integer asiakas_id, String alue, String mokki, String alkupvm, String loppupvm) {
        this.varausid = new SimpleIntegerProperty(varaus_id);
        this.asiakasid = new SimpleIntegerProperty(asiakas_id);
        this.alue = new SimpleStringProperty(alue);
        this.mokki = new SimpleStringProperty(mokki);
        this.alkupvm = new SimpleStringProperty(alkupvm);
        this.loppupvm = new SimpleStringProperty(loppupvm);
    }

    public Integer getAsiakas_id() {
        return asiakas.get();
    }


    public Integer getVarausId(){
        return varausid.get();
    }
    public Integer getAsiakasId(){
        return asiakasid.get();
    }
    public String getEnimi() {
        return enimi.get();
    }
    public String getSnimi() {
        return snimi.get();
    }
    public String getAlue() {
        return alue.get();
    }
    public String getMokki() {
        return mokki.get();
    }
    public String getAlkupvm() {
        return alkupvm.get();
    }
    public String getLoppupvm() {
        return loppupvm.get();
    }
}


