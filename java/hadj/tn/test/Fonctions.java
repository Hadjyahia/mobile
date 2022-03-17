package hadj.tn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fonctions {

    public String url = "jdbc:mysql://localhost:3306/pfe";
    public static final String user = "root";
    public static final String pass = "oumayma1";

    public Statement connexionBDDSQL(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement st = conn.createStatement();
            return st;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
}
