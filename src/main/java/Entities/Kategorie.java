package Entities;


public class Kategorie {

  private long aufschlag;
  private String bezeichnung;
  private long kategorieId;


  public long getAufschlag() {
    return aufschlag;
  }

  public void setAufschlag(long aufschlag) {
    this.aufschlag = aufschlag;
  }


  public String getBezeichnung() {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }


  public long getKategorieId() {
    return kategorieId;
  }

  public void setKategorieId(long kategorieId) {
    this.kategorieId = kategorieId;
  }

}
