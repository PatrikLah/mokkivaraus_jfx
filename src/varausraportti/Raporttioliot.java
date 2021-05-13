package varausraportti;

public class Raporttioliot {

    private String varauspvmO;
    private String varattuAlkupvmO;
    private String varattuLoppupvmO;
    private int mokkiIDO;
    private String mokkinimiO;
    private String etunimiO;
    private String sukunimiO;


    public Raporttioliot(String varauspvmO, String varattuAlkupvmO, String varattuLoppupvmO, int mokkiIDO, String mokkinimiO, String etunimiO, String sukunimiO) {
        this.varauspvmO = varauspvmO;
        this.varattuAlkupvmO = varattuAlkupvmO;
        this.varattuLoppupvmO = varattuLoppupvmO;
        this.mokkiIDO = mokkiIDO;
        this.mokkinimiO = mokkinimiO;
        this.etunimiO = etunimiO;
        this.sukunimiO = sukunimiO;

    }

    public int getMokkiIDO() {
        return mokkiIDO;
    }

    public void setMokkiIDO(int mokkiIDO) {
        this.mokkiIDO = mokkiIDO;
    }

    public String getMokkinimiO() {
        return mokkinimiO;
    }

    public void setMokkinimiO(String mokkinimiO) {
        this.mokkinimiO = mokkinimiO;
    }

    public String getEtunimiO() {
        return etunimiO;
    }

    public void setEtunimiO(String etunimiO) {
        this.etunimiO = etunimiO;
    }

    public String getSukunimiO() {
        return sukunimiO;
    }

    public void setSukunimiO(String sukunimiO) {
        this.sukunimiO = sukunimiO;
    }

    public String getVarauspvmO() {
        return varauspvmO;
    }

    public void setVarauspvmO(String varauspvmO) {
        this.varauspvmO = varauspvmO;
    }

    public String getVarattuAlkupvmO() {
        return varattuAlkupvmO;
    }

    public void setVarattuAlkupvmO(String varattuAlkupvmO) {
        this.varattuAlkupvmO = varattuAlkupvmO;
    }

    public String getVarattuLoppupvmO() {
        return varattuLoppupvmO;
    }

    public void setVarattuLoppupvmO(String varattuLoppupvmO) {
        this.varattuLoppupvmO = varattuLoppupvmO;
    }
}
