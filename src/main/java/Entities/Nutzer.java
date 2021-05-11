package Entities;


public class Nutzer {

  private long nutzerId;
  private String passwort;
  private String vorname;
  private String name;
  private String benutzername;

  public String getBenutzername(){
    return this.benutzername;
  }

  public void setBenutzername(String benutzername){
    this.benutzername = benutzername;
  }

  public long getNutzerId() {
    return nutzerId;
  }

  public void setNutzerId(long nutzerId) {
    this.nutzerId = nutzerId;
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

}
