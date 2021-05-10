package mokkivarausjarjestelma;

import java.sql.Connection;

public class Controller {

    public void dbLink(){
        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();

    }
}
