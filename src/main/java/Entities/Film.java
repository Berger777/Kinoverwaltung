package Entities;


public class Film {

  private String filmId;
  private String titel;
  private String laenge;
  private String regisseur;
  private String beschreibung;
  private String preis;


  public String getFilmId() {
    return filmId;
  }

  public void setFilmId(String filmId) {
    this.filmId = filmId;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getLaenge() {
    return laenge;
  }

  public void setLaenge(String laenge) {
    this.laenge = laenge;
  }

  public String getRegisseur() {
    return regisseur;
  }

  public void setRegisseur(String regisseur) {
    this.regisseur = regisseur;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public String getPreis() {
    return preis;
  }

  public void setPreis(String preis) {
    this.preis = preis;
  }
}
