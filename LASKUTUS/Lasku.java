package LASKUTUS;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Lasku {
    private SimpleIntegerProperty laskuID;
    private SimpleIntegerProperty varausID;
    //private SimpleStringProperty sukunimi;
    //private SimpleStringProperty etunimi;
    private SimpleDoubleProperty summa;
    private SimpleDoubleProperty alv;
    //private SimpleStringProperty alkupvm;
    //private SimpleStringProperty loppupvm;

    //laskut taulukkoon
    public Lasku(Integer laskuID, Integer varausID, double summa, double alv) {
        this.laskuID = new SimpleIntegerProperty(laskuID);
        this.varausID = new SimpleIntegerProperty(varausID);
        //this.sukunimi = new SimpleStringProperty(sukunimi);
        //this.etunimi = new SimpleStringProperty(etunimi);
        this.summa = new SimpleDoubleProperty(summa);
        this.alv = new SimpleDoubleProperty(alv);
        //this.alkupvm = new SimpleStringProperty(alkupvm);
        //this.loppupvm = new SimpleStringProperty(loppupvm);
    }



    public Integer getLaskuId() {

        return laskuID.get();
    }
    public Integer getVarausId() {
        return varausID.get();
    }



    public double getSumma() {
        return summa.get();
    }

    public double getAlv() {
        return alv.get();
    }


}
