package Services;

import Entities.*;
import Exceptions.NutzerNotFoundException;
import Exceptions.ResultSetNullException;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseService {

    public void speichereNutzerInDB(String vorname, String nachname, String benutzername,String passwort){
        Nutzer nutzer = new Nutzer();
        nutzer.setName(nachname);
        nutzer.setPasswort(passwort);
        nutzer.setVorname(vorname);
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "INSERT INTO Nutzer (Vorname, Name, Benutzername, Passwort, NutzerID) VALUES(?,?,?,?,?);";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,vorname);
            prepareStatement.setString(2,nachname);
            prepareStatement.setString(3,benutzername);
            prepareStatement.setString(4,passwort);
            prepareStatement.setString(5, UUID.randomUUID().toString());
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Nutzer getBenutzerAusDB(String benutzername) throws NutzerNotFoundException {
        Nutzer nutzer = new Nutzer();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Nutzer WHERE Benutzername = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,benutzername);
            ResultSet rs = prepareStatement.executeQuery();

            if(!rs.next()){
                rs.close();
                conn.close();
                throw new NutzerNotFoundException();
            }
            do {
                nutzer.setNutzerId(rs.getString("NutzerID"));
                nutzer.setVorname(rs.getString("Vorname"));
                nutzer.setName(rs.getString("Name"));
                nutzer.setBenutzername(rs.getString("Benutzername"));
                nutzer.setPasswort(rs.getString("Passwort"));
                System.out.println("Nutzer aus DB geholt: " +nutzer.getNutzerId() + " " + nutzer.getBenutzername()+ " " + nutzer.getVorname() + " " + nutzer.getName());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler beim hohlen des Nutzers aus der DB: "+e);
        }
        return nutzer;
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
                nutzer.setNutzerId(rs.getString("NutzerID"));
                nutzer.setVorname(rs.getString("Vorname"));
                nutzer.setName(rs.getString("Name"));
                nutzer.setPasswort(rs.getString("Passwort"));
                System.out.println("Nutzer aus DB geholt: " +nutzer.getNutzerId() + " " + nutzer.getBenutzername()+ " " + nutzer.getVorname() + " " + nutzer.getName());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return nutzer;
    }

    public ArrayList<Reservierung> getReservierungenAsList(Nutzer nutzer) {
        ArrayList<Reservierung> res = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Reservierung JOIN Vorfuehrung JOIN Saal JOIN Film WHERE Reservierung.VorfuehrungID = Vorfuehrung.VorfuehrungID AND Vorfuehrung.FilmID = Film.FilmID AND Vorfuehrung.SaalID = Saal.SaalID AND Reservierung.NutzerID = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,nutzer.getNutzerId());
            ResultSet rs = prepareStatement.executeQuery();

            do {
                Film film = new Film();
                film.setFilmId(rs.getLong("FilmID"));
                film.setBeschreibung(rs.getString("Beschreibung"));
                film.setTitel(rs.getString("Titel"));
                film.setLaenge(rs.getString("Laenge"));
                film.setPreis(rs.getLong("Preis"));

                Saal saal = new Saal();
                saal.setSaalId(rs.getLong("SaalID"));

                Vorfuehrung vorfuehrung = new Vorfuehrung();
                vorfuehrung.setVorfuehrungId(rs.getString("VorfuehrungID"));
                vorfuehrung.setFilm(film);
                vorfuehrung.setSaal(saal);
                vorfuehrung.setDatum(rs.getString("Datum"));
                vorfuehrung.setZeit(rs.getLong("Zeit"));
                vorfuehrung.setAufschlag(rs.getLong("Aufschlag"));

                Reservierung reservierung = new Reservierung();
                reservierung.setReservierungId(rs.getLong("ReservierungID"));
                reservierung.setVorfuehrung(vorfuehrung);
                reservierung.setNutzer(nutzer);

                res.add(reservierung);
                System.out.println(reservierung);
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler bei den Reservierungen: "+e);
        }
        return res;
    }

    public void speichereFilmInDB(String Filmtitel, String Filmbeschreibung, String Filmlaenge, String Filmregisseur, String Filmpreis){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "INSERT INTO Film (Titel, Laenge, Preis, Beschreibung, FilmID, Regisseur) VALUES(?,?,?,?,?,?);";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1, Filmtitel);
            prepareStatement.setString(2, Filmlaenge);
            prepareStatement.setString(3, Filmpreis);
            prepareStatement.setString(4, Filmbeschreibung);
            prepareStatement.setString(5, UUID.randomUUID().toString());
            prepareStatement.setString(6, Filmregisseur);
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void speichereVorfuehrungInDB(String FilmID, String Aufschlag, String Zeit, String Datum, String SaalID) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "INSERT INTO Film (FilmID, Aufschlag, Zeit, Datum, VorfuehrungID, SaalID) VALUES(?,?,?,?,?,?);";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1, FilmID);
            prepareStatement.setString(2, Aufschlag);
            prepareStatement.setString(3, Zeit);
            prepareStatement.setString(4, Datum);
            prepareStatement.setString(5, UUID.randomUUID().toString());
            prepareStatement.setString(6, SaalID);
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
