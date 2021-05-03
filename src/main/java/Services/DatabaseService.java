package Services;

import Entities.Nutzer;
import Exceptions.NutzerNotFoundException;

import java.sql.*;
import java.util.UUID;

public class DatabaseService {

    public void speichereNutzerInDB(String vorname, String nachname, String passwort){
        Nutzer nutzer = new Nutzer();
        nutzer.setName(nachname);
        nutzer.setPasswort(passwort);
        nutzer.setVorname(vorname);
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "INSERT INTO Nutzer (Vorname, Name, Passwort, NutzerID) VALUES(?,?,?,?);";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,vorname);
            prepareStatement.setString(2,nachname);
            prepareStatement.setString(3,passwort);
            prepareStatement.setString(4, UUID.randomUUID().toString());
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Nutzer getNutzerAusDB(String vorname, String nachname) throws NutzerNotFoundException {
            Nutzer nutzer = new Nutzer();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Nutzer WHERE Vorname = ? AND Name = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,vorname);
            prepareStatement.setString(2,nachname);
            ResultSet rs = prepareStatement.executeQuery();

            if(!rs.next()){
                rs.close();
                conn.close();
                throw new NutzerNotFoundException();
            }
            do {
                nutzer.setNutzerId(rs.getInt("NutzerID"));
                nutzer.setVorname(rs.getString("Vorname"));
                nutzer.setName(rs.getString("Name"));
                nutzer.setPasswort(rs.getString("Passwort"));
                System.out.println(nutzer);
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return nutzer;
    }

}
