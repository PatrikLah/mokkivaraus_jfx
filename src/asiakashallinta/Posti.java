package asiakashallinta;

public class Posti {
    String postinro;
    String toimipaikka;

    public Posti(String postinro, String toimipaikka) {
        this.postinro = postinro;
        this.toimipaikka = toimipaikka;
    }

    public String getPostinro() {
        return postinro;
    }

    public void setPostinro(String postinro) {
        this.postinro = postinro;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }
}
