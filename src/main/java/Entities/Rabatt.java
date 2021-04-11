package Entities;


public class Rabatt {

  private long rabatt;
  private long sitzId;
  private long reservierungId;


  public long getRabatt() {
    return rabatt;
  }

  public void setRabatt(long rabatt) {
    this.rabatt = rabatt;
  }


  public long getSitzId() {
    return sitzId;
  }

  public void setSitzId(long sitzId) {
    this.sitzId = sitzId;
  }


  public long getReservierungId() {
    return reservierungId;
  }

  public void setReservierungId(long reservierungId) {
    this.reservierungId = reservierungId;
  }

}
