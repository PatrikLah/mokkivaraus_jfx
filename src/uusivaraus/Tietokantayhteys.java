package uusivaraus;
//tietokantayhtys

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Tietokantayhteys {
    private static Tietokantayhteys tietokantayhteys;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vn";

    private static Connection conn = null;
    private static Statement stm = null;

    public Tietokantayhteys(){
        setTietokantayhteys();
    }

    //yhteys tietokantaan
    public static Connection setTietokantayhteys(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, "root", "Terhi88");
            System.out.println("yhteys onnistui");
            return conn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //metodi jolla suoritetaan kyselyt
    public ResultSet execQuery(String query){
        ResultSet result;
        try{
            stm = conn.createStatement();
            result = stm.executeQuery(query);
        } catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
    //metodi jolla suoritetaan toiminnot
    public boolean execAction(String  qu){
        try{
            stm = conn.createStatement();
            stm.execute(qu);
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Virhe:" +e.getMessage(), "Tapahtui virhe",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getLocalizedMessage());
            return false;
        }finally {
        }

    }
    //metodi joka poistaa rivin kalenterin taulusta
    public boolean poistaRivi(Henkilo henkilo){
        try {
            String poisto = "DELETE FROM varaus WHERE varaus_id = ?"; //sql lause jolla poistaan
            PreparedStatement stm = conn.prepareStatement(poisto);
            stm.setInt(1, henkilo.getVarausId());
            int res = stm.executeUpdate();
            if(res ==1){
                return true;
            }
            System.out.println(res);
            return true;
        }catch (SQLException e){

        }
        return false;
    }
 /*   public boolean paivitaRivi(Henkilo henkilo){
        try{
            String paivita= "UPDATE varaus SET asiakas_id = ?, mokki_mokki_id =?, varattu_pvm=?, varattu_alkupvm=?, varattu_loppupvm=?";
            PreparedStatement stmt = conn.prepareStatement(paivita);
            stmt.setInt(2, henkilo.getAsiakas());
            stmt.setInt(3, henkilo.getMokki());
            stmt.setString(4, henkilo.getVarattu());
            stmt.setString(5, henkilo.getVarAlku());
            stmt.setString(6, henkilo.getVarLoppu());
            int res = stmt.executeUpdate();
            return (res >0);
        }catch (SQLException e){

        }
       return false;
    }
*/
}
