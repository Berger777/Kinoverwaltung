package Entities;


import java.util.Objects;

public class Sitz {

  private String sitzId;
  private String reihe;
  private String nr;
  private Kategorie kategorie;
  private Saal saal;

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

  public Saal getSaal() {
    return saal;
  }

  public void setSaal(Saal saal) {
    this.saal = saal;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sitz sitz = (Sitz) o;
    return reihe.equals(sitz.reihe) && nr.equals(sitz.nr) && saal.getSaalId().equals(sitz.saal.getSaalId()) ;
  }

}
