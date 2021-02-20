package sample;

import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    public void irgendwie(ActionEvent event){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            Statement stat = conn.createStatement();
            String sql = "select * from Nutzer;";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ArtikelNr: " + rs.getString("ArtikelNr") +
                        ", Bezeichnung: " + rs.getString("Artikelname"));
            }

            rs.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
