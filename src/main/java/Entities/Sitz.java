package Entities;


public class Sitz {

  private String sitzId;
  private String reihe;
  private String nr;
  private Kategorie kategorie;
  private String saalId;
  private String reservierungId;

  public String getSitzId() {
    return sitzId;
  }

  public void setSitzId(String sitzId) {
    this.sitzId = sitzId;
  }

  public String getReihe() {
    return reihe;
  }

  public void setReihe(String reihe) {
    this.reihe = reihe;
  }

  public String getNr() {
    return nr;
  }

  public void setNr(String nr) {
    this.nr = nr;
  }

  public Kategorie getKategorie() {
    return kategorie;
  }

  public void setKategorie(Kategorie kategorie) {
    this.kategorie = kategorie;
  }

  public String getSaalId() {
    return saalId;
  }

  public void setSaalId(String saalId) {
    this.saalId = saalId;
  }

  public String getReservierungId() {
    return reservierungId;
  }

  public void setReservierungId(String reservierungId) {
    this.reservierungId = reservierungId;
  }
}
