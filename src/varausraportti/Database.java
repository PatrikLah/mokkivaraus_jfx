package varausraportti;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public Connection databaseLink;

    public Connection getConnection() {
        String dbName = "vn";
        String dbUser = "root";
        String dbPassword = "R00ibosse";
        String url = "jdbc:mysql://localhost/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
