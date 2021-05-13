package asiakashallinta;

import java.sql.Connection;
import java.sql.DriverManager;


public class Tietokantayhteys {
    public Connection tietokantalinkki;

    public Connection getYhteys(){

        String dbUser = "root";
        String dbPassword = "Rootti21";
        String dburl = "jdbc:mysql://localhost:3306/vn?serverTimezone=UTC";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            tietokantalinkki = DriverManager.getConnection(dburl, dbUser, dbPassword);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return tietokantalinkki;
    }



}
