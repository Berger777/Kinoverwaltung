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
            rs.next();
            do {
                Film film = new Film();
                film.setFilmId(rs.getString("FilmID"));
                film.setBeschreibung(rs.getString("Beschreibung"));
                film.setTitel(rs.getString("Titel"));
                film.setLaenge(rs.getString("Laenge"));
                film.setPreis(rs.getString("Preis"));

                Saal saal = new Saal();
                saal.setSaalId(rs.getString("SaalID"));

                Vorfuehrung vorfuehrung = new Vorfuehrung();
                vorfuehrung.setVorfuehrungId(rs.getString("VorfuehrungID"));
                vorfuehrung.setFilm(film);
                vorfuehrung.setSaal(saal);
                vorfuehrung.setDatum(rs.getString("Datum"));
                vorfuehrung.setZeit(rs.getString("Zeit"));
                vorfuehrung.setAufschlag(rs.getString("Aufschlag"));

                Reservierung reservierung = new Reservierung();
                reservierung.setReservierungId(rs.getString("ReservierungID"));
                reservierung.setVorfuehrung(vorfuehrung);
                reservierung.setNutzer(nutzer);

                res.add(reservierung);
                System.out.println("Reservierung aus DB gehohlt: "+reservierung.getReservierungId()+ " " + reservierung.getNutzer().getBenutzername() + " "+ reservierung.getVorfuehrung().getVorfuehrungId());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler bei den Reservierungen: "+e);
        }
        return res;
    }

    public ArrayList<Sitz> getSitzeAsList() {
        ArrayList<Sitz> res = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Sitz JOIN Kategorie WHERE Sitz.KategorieID = Kategorie.KategorieID;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            do {
                Sitz sitz = new Sitz();
                sitz.setSitzId(rs.getString("SitzID"));
                sitz.setSaalId(rs.getString("SaalID"));
                sitz.setReservierungId(rs.getString("ReservierungID"));
                sitz.setNr(rs.getString("Nr"));
                sitz.setReihe(rs.getString("Reihe"));

                Kategorie kategorie = new Kategorie();
                kategorie.setKategorieId(rs.getString("KategorieID"));
                kategorie.setBezeichnung(rs.getString("Bezeichnung"));
                kategorie.setAufschlag(rs.getString("Aufschlag"));
                sitz.setKategorie(kategorie);

                res.add(sitz);
                System.out.println("Sitz aus DB gehohlt: Reihe: "+sitz.getReihe()+ " Sitz-Nummer" + sitz.getNr() + " "+ sitz.getKategorie().getBezeichnung());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler bei den Sitz: "+e);
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
            String sql = "INSERT INTO Vorfuehrung (FilmID, Aufschlag, Zeit, Datum, VorfuehrungID, SaalID) VALUES(?,?,?,?,?,?);";
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

    public ArrayList<Film> getFilmeAusDB(){
        ArrayList<Film> filme = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Film;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();

            do {
                Film film = new Film();
                film.setFilmId(rs.getString("FilmID"));
                film.setTitel(rs.getString("Titel"));
                film.setLaenge(rs.getString("Laenge"));
                film.setBeschreibung(rs.getString("Beschreibung"));
                film.setPreis(rs.getString("Preis"));
                film.setRegisseur(rs.getString("Regisseur"));
                filme.add(film);
                System.out.println("Film aus DB geholt: " +film.getFilmId() + " " + film.getTitel()+ " " + film.getPreis() + " " + film.getBeschreibung());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return filme;
    }

    public ArrayList<Vorfuehrung> getVorfuehrungenAusDBsimple(){
        ArrayList<Vorfuehrung> vorf = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Vorfuehrung;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();

            do {
                Vorfuehrung v = new Vorfuehrung();
                v.setVorfuehrungId(rs.getString("VorfuehrungID"));
                v.setDatum(rs.getString("Datum"));
                v.setZeit(rs.getString("Zeit"));
                v.setAufschlag(rs.getString("Aufschlag"));

                vorf.add(v);
                System.out.println("Vorfuehrungen aus DB geholt: " + v.getVorfuehrungId()+ " "+ v.getDatum() + " "+ v.getZeit());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return vorf;
    }

    public ArrayList<Vorfuehrung> getVorfuehrungenAusDB(){
        ArrayList<Vorfuehrung> vorf = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Vorfuehrung JOIN Saal JOIN Film WHERE Vorfuehrung.FilmID = Film.FilmID AND Vorfuehrung.SaalID = Saal.SaalID;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();

            do {
                Film film = new Film();
                film.setFilmId(rs.getString("FilmID"));
                film.setBeschreibung(rs.getString("Beschreibung"));
                film.setTitel(rs.getString("Titel"));
                film.setLaenge(rs.getString("Laenge"));
                film.setPreis(rs.getString("Preis"));

                Saal saal = new Saal();
                saal.setSaalId(rs.getString("SaalID"));

                Vorfuehrung vorfuehrung = new Vorfuehrung();
                vorfuehrung.setVorfuehrungId(rs.getString("VorfuehrungID"));
                vorfuehrung.setFilm(film);
                vorfuehrung.setSaal(saal);
                vorfuehrung.setDatum(rs.getString("Datum"));
                vorfuehrung.setZeit(rs.getString("Zeit"));
                vorfuehrung.setAufschlag(rs.getString("Aufschlag"));
                vorf.add(vorfuehrung);
                System.out.println("Vorfuehrungen aus DB geholt: " + vorfuehrung.getVorfuehrungId()+ " "+ vorfuehrung.getDatum() + " "+ vorfuehrung.getZeit());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return vorf;
    }

    public ArrayList<Saal> getSaaleAusDB() {
        ArrayList<Saal> saale = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "select * from Saal;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            do {
                Saal saal = new Saal();
                saal.setSaalId(rs.getString("SaalID"));
                saal.setSaalname(rs.getString("Name"));
                saale.add(saal);
                System.out.println("Saal aus DB geholt: " + saal.getSaalname()+ " " + saal.getSaalId());
            }while(rs.next());
            rs.close();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return saale;
    }

    public void deleteFilm(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "delete from Film WHERE FilmID = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,id);
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void deleteVorfuehrung(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "delete from Vorfuehrung WHERE VorfuehrungID = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,id);
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler beim Löschen der Vorführung"+e);
        }
    }

    public void deleteReservierung(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:kino.sqlite");
            String sql = "delete from Reservierung WHERE ReservierungID = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1,id);
            prepareStatement.execute();
            conn.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fehler beim Löschen der Reservierung"+e);
        }
    }
}
