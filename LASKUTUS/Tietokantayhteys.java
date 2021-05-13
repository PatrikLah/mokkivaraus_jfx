package LASKUTUS;

import java.sql.*;
import javax.swing.*;

public class Tietokantayhteys {
    private static Tietokantayhteys tietokantayhteys;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vn";

    private static Connection conn = null;
    private static Statement stm = null;

    public Tietokantayhteys() {
        setTietokantayhteys();
    }

    //yhteys tietokantaan:
    public static Connection setTietokantayhteys() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, "root", "mansikka18");
            System.out.println("Yhteyden muodostus onnistui");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodi, jolla suoritetaan kyselyt:
    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stm = conn.createStatement();
            result = stm.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    //metodi, jolla suoritetaan toiminnot:
    public boolean execAction(String qu) {
        try {
            stm = conn.createStatement();
            stm.execute(qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Virhe: " + e.getMessage(), "Tapahtui virhe",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getLocalizedMessage());
            return false;
        } finally {
        }
    }

    //metodi,joka poistaa laskurivin laskuarkiston(?)-taulusta:
    public boolean poistaLasku(Lasku lasku) {
        try {
            String poistolause = "DELETE FROM lasku WHERE lasku_id = ?";
            PreparedStatement stm = conn.prepareStatement(poistolause);
            stm.setInt(1, lasku.getLaskuId());
            int res = stm.executeUpdate();
            if (res == 1) {
                return true;
            }
            System.out.println(res);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Virhe: " + e.getMessage(), "Tapahtui virhe",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getLocalizedMessage());
        }
        return false;
        }


}


