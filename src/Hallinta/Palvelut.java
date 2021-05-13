package Hallinta;

import javafx.application.Application;
import javafx.stage.Stage;

public class Palvelut extends Application {
    private int palveluid;
    private String palvelunimi;
    private String kuvauspalvelu;
    private double hinta;


     public Palvelut(int palveluid, String palvelunimi, double hinta, String kuvauspalvelu) {
         this.palveluid = palveluid;
     this.palvelunimi = palvelunimi;
     this.hinta = hinta;
     this.kuvauspalvelu = kuvauspalvelu;
      }

      public int getPalveluid() {
         return palveluid;
      }
    public String getPalvelunimi() {
         return palvelunimi;
    }

    public void setPalvelunimi(String palvelunimi) {
        this.palvelunimi = palvelunimi;
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


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
