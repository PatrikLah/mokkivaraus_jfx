package Hallinta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mokitjapalvelut {
    private String mokkinimi;
    private String osoite;
    private String kuvausmokki;
    private int henkilomaara;

    private String palvelunimi;
    private String alue;
    private String kuvauspalvelu;
    private double hinta;
    private double ALV;

    //mökit
    public Mokitjapalvelut(String mokkinimi, String osoite, String kuvausmokki, int henkilomaara) {
        this.mokkinimi = mokkinimi;
        this.osoite = osoite;
        this.kuvausmokki = kuvausmokki;
        this.henkilomaara = henkilomaara;
    }
    //palvelut
    public Mokitjapalvelut(String palvelunimi, String alue, String kuvauspalvelu, double hinta, double ALV) {
        this.palvelunimi = palvelunimi;
        this.alue = alue;
        this.kuvauspalvelu = kuvauspalvelu;
        this.hinta = hinta;
        this.ALV = ALV;
    }

    public Mokitjapalvelut(String mokkinimi, String katuosoite, String toimintaalue, String kuvaus) {
    }
    //mökkien lisäys


    public String getMokkinimi() {
        return mokkinimi;
    }

    public void setMokkinimi(String mokkinimi) {
        this.mokkinimi = mokkinimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getKuvausmokki() {
        return kuvausmokki;
    }

    public void setKuvausmokki(String kuvausmokki) {
        this.kuvausmokki = kuvausmokki;
    }

    public int getHenkilomaara() {
        return henkilomaara;
    }

    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public String getPalvelunimi() {
        return palvelunimi;
    }

    public void setPalvelunimi(String palvelunimi) {
        this.palvelunimi = palvelunimi;
    }

    public String getAlue() {
        return alue;
    }

    public void setAlue(String alue) {
        this.alue = alue;
    }

    public String getKuvauspalvelu() {
        return kuvauspalvelu;
    }

    public void setKuvauspalvelu(String kuvauspalvelu) {
        this.kuvauspalvelu = kuvauspalvelu;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public double getALV() {
        return ALV;
    }

    public void setALV(double ALV) {
        this.ALV = ALV;
    }
}