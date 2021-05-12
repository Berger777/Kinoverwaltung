package Entities;


public class Reservierung {

  private String reservierungId;
  private Nutzer nutzer;
  private Vorfuehrung vorfuehrung;

  public Nutzer getNutzer() {
    return nutzer;
  }

  public void setNutzer(Nutzer nutzer) {
    this.nutzer = nutzer;
  }

  public Vorfuehrung getVorfuehrung() {
    return vorfuehrung;
  }

  public void setVorfuehrung(Vorfuehrung vorfuehrung) {
    this.vorfuehrung = vorfuehrung;
  }
}
