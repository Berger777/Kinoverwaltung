package Entities;


public class Reservierung {

  private long reservierungId;
  private long nutzerId;
  private long vorfuehrungId;


  public long getReservierungId() {
    return reservierungId;
  }

  public void setReservierungId(long reservierungId) {
    this.reservierungId = reservierungId;
  }


  public long getNutzerId() {
    return nutzerId;
  }

  public void setNutzerId(long nutzerId) {
    this.nutzerId = nutzerId;
  }


  public long getVorfuehrungId() {
    return vorfuehrungId;
  }

  public void setVorfuehrungId(long vorfuehrungId) {
    this.vorfuehrungId = vorfuehrungId;
  }

}
