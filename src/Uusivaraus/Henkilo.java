package Uusivaraus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Henkilo {
    private SimpleIntegerProperty varaus;
    private SimpleIntegerProperty palvelu;
    private SimpleStringProperty pvm;


    //mökkien varaus-taulukkoon
    public Henkilo(Integer varaus, Integer palvelu, String pvm) {
        this.varaus = new SimpleIntegerProperty(varaus);
        this.palvelu = new SimpleIntegerProperty(palvelu);
        this.pvm = new SimpleStringProperty(pvm);

    }

    //mökkien varaukset taulukkoon
    public Integer getVaraus(){
        return varaus.get();
    }
    public Integer getPalvelu() {
        return palvelu.get();
    }
    public String getPvm() {
        return pvm.get();
    }

}
