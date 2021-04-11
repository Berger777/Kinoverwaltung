package Entities;


public class Sitz {

  private long sitzId;
  private long reihe;
  private long nr;
  private long kategorieId;
  private long saalId;
  private long reservierungId;


  public long getSitzId() {
    return sitzId;
  }

  public void setSitzId(long sitzId) {
    this.sitzId = sitzId;
  }


  public long getReihe() {
    return reihe;
  }

  public void setReihe(long reihe) {
    this.reihe = reihe;
  }


  public long getNr() {
    return nr;
  }

  public void setNr(long nr) {
    this.nr = nr;
  }


  public long getKategorieId() {
    return kategorieId;
  }

  public void setKategorieId(long kategorieId) {
    this.kategorieId = kategorieId;
  }


  public long getSaalId() {
    return saalId;
  }

  public void setSaalId(long saalId) {
    this.saalId = saalId;
  }


  public long getReservierungId() {
    return reservierungId;
  }

  public void setReservierungId(long reservierungId) {
    this.reservierungId = reservierungId;
  }

}
