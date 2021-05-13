package Hallinta;

import java.sql.*;
import javax.swing.*;

public class Tietokanta {
    private static Tietokanta tietokantayhteys;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/vn";

    private static Connection conn = null;
    private static Statement stm = null;

    public Tietokanta() {
        Tietokantayhteys();
    }

    //yhteys tietokantaan:
    public static Connection Tietokantayhteys() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, "root", "root");
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

    public boolean execAction(String qu){
        try {
            stm = conn.createStatement();
            stm.execute(qu);
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Virhe: " + e.getMessage(), "Tapahtui virhe", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getLocalizedMessage());
            return false;
        } finally{

        }
    }
}