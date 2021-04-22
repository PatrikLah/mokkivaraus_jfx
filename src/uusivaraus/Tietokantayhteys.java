package uusivaraus;
//tietokantayhtys

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Tietokantayhteys {
// yhteys tietokantaan
    public static Connection getConnection() throws Exception{
        try{
            //tietokanta yhteys
            String driver = "com.mysql.jdbc.Driver";
            //tietokannan "osoite", portti ja oikea schema
            String url = "jdbc:mysql://localhost:3306/vn";

            // käyttäjän tiedot
            String user = "root";
            String pass = "";

            //yhteys tietokantaan käyttäen Connection luokkaa
            Connection conn = DriverManager.getConnection(url,user,pass);
            //tulostetaan kommentti yhdistymisestä tietokantaan
            System.out.println("yhdistetty");
            return conn;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //metodi joka lisää tietokannan tauluun tietoa
    public static void lisaa() throws Exception {
        // tieto joka lisätään
        final String eka = "Levi";
        try{
            //yhteys tietokantaan
            Connection con = getConnection();
            //lisäys lause
            PreparedStatement lisatty = con.prepareStatement("INSERT INTO toimintaalue(nimi) VALUES('"+eka+"')");
            lisatty.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Lisätty tietokantaan");
        }

    }
    //metodi tietokannan select-lauseelle, tehdään lista/taulukko johon tiedot tulostetaan
    public static ArrayList<String> hae() throws Exception{
        try{
            //yhteys tietokantaan
            Connection con = getConnection();
            // haku lause tietokantaan
            PreparedStatement statement = con.prepareStatement("SELECT nimi FROM toimintaalue");

            ResultSet tulos =statement.executeQuery();

            //luodaan uusi lista ja tulosteaan siihen tietokannasta haetut tiedot
            ArrayList<String> lista = new ArrayList<String>();
            while(tulos.next()){
                System.out.println(tulos.getString("nimi"));
                System.out.println("  ");

                lista.add(tulos.getString("nimi"));
            }
            System.out.println("Tiedot tulostettu");
            return lista;
        }catch (Exception e){
            e.printStackTrace();
        }
        //tämä, mikäli aikaisemmassa return lausessa on virhe ja mitään ei tulostu
        return null;
    }

}
