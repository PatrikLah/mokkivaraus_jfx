package uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Henkilo {
    private SimpleIntegerProperty varaus;
    private SimpleStringProperty enimi;
    private SimpleStringProperty snimi;
    private SimpleStringProperty alue;
    private SimpleStringProperty mokki;
    private SimpleStringProperty alkupvm;
    private SimpleStringProperty loppupvm;

    private SimpleIntegerProperty varaus2;
    private SimpleStringProperty enimi2;
    private SimpleStringProperty snimi2;
    private SimpleStringProperty palvelu;
    private SimpleStringProperty pvm;

    //mökkien varaus-taulukkoon
    public Henkilo(Integer varaus, String enimi,String snimi, String alue, String mokki, String alkupvm, String loppupvm) {
          this.varaus = new SimpleIntegerProperty(varaus);
          this.enimi = new SimpleStringProperty(enimi);
          this.snimi = new SimpleStringProperty(snimi);
          this.alue = new SimpleStringProperty(alue);
          this.mokki = new SimpleStringProperty(mokki);
          this.alkupvm = new SimpleStringProperty(alkupvm);
          this.loppupvm = new SimpleStringProperty(loppupvm);
    }
    //palvelut-taulukkoon
     public Henkilo(Integer varaus, String enimi, String snimi, String palvelu, String pvm) {
        this.varaus2 = new SimpleIntegerProperty(varaus);
        this.enimi2 = new SimpleStringProperty(enimi);
        this.snimi2 = new SimpleStringProperty(snimi);
        this.palvelu = new SimpleStringProperty(palvelu);
        this.pvm = new SimpleStringProperty(pvm);
    }
    //mökkien varaukset taulukkoon
    public Integer getVarausId(){
        return varaus.get();
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

    //palvelut taulukkoon
    public Integer getVarausId2(){
        return varaus2.get();
    }
    public String getEnimi2() {
        return enimi2.get();
    }
    public String getSnimi2() {
        return snimi2.get();
    }
    public String getPalvelu() {
        return palvelu.get();
    }
    public String getPvm() {
        return pvm.get();
    }

}
