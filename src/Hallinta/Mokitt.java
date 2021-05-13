package Hallinta;

public class Mokitt {
    private String mokkinimi;
    private String osoite;
    private String kuvausmokki;



    public Mokitt(String mokkinimi, String osoite, String kuvausmokki) {
        this.mokkinimi = mokkinimi;
        this.osoite = osoite;
        this.kuvausmokki = kuvausmokki;
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

}
