package asiakashallinta;

public class Asiakas {
    Integer asiakasID; //Integer voi olla null, int ei
    String etunimi;
    String sukunimi;
    String osoite;
    String postinumero;
    String email;
    String puhelin;

    public Asiakas(Integer asiakasID, String etunimi, String sukunimi, String osoite, String postinumero, String email, String puhelin) {
        this.asiakasID = asiakasID;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.osoite = osoite;
        this.postinumero = postinumero;
        this.email = email;
        this.puhelin = puhelin;
    }

    public Integer getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasID(Integer asiakasID) {
        this.asiakasID = asiakasID;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuhelin() {
        return puhelin;
    }

    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }
}
