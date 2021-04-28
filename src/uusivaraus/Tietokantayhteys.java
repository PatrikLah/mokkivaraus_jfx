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
    }*/
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
        //jos aikaisemmassa return lausessa on virhe ja mitään ei tulostu
        return null;
    }*/
   /* public static void lisaa() throws Exception {
    // tieto joka lisätään
        final String eka = "Levi";
        try{
        //yhteys tietokantaan
            conn = setTietokantayhteys();
        //lisäys lause
            PreparedStatement lisatty = conn.prepareStatement("INSERT INTO toimintaalue(nimi) VALUES('"+eka+"')");
            lisatty.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Lisätty tietokantaan");
        }
    }
*/
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
            stm.setInt(1, henkilo.getVaraus());
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
    public boolean paivitaRivi(Henkilo henkilo){
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

}
