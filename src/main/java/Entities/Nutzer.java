package Entities;

public class Nutzer {

    private int id;
    private String passwort;
    private String vorname;
    private String name;

    public Nutzer(int id, String passwort, String vorname, String name){
        this.id = id;
        this.vorname = vorname;
        this.name = name;
        this.passwort = passwort;
    }

    public Nutzer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nutzer{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", name='" + name + '\'' +
                ", passwort='" + passwort + '\'' +
                '}';
    }
}
