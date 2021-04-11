package Entities;


public class Vorfuehrung {

  private long vorfuehrungId;
  private String datum;
  private long zeit;
  private long aufschlag;
  private long filmId;
  private long saalId;


  public long getVorfuehrungId() {
    return vorfuehrungId;
  }

  public void setVorfuehrungId(long vorfuehrungId) {
    this.vorfuehrungId = vorfuehrungId;
  }


  public String getDatum() {
    return datum;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }


  public long getZeit() {
    return zeit;
  }

  public void setZeit(long zeit) {
    this.zeit = zeit;
  }


  public long getAufschlag() {
    return aufschlag;
  }

  public void setAufschlag(long aufschlag) {
    this.aufschlag = aufschlag;
  }


  public long getFilmId() {
    return filmId;
  }

  public void setFilmId(long filmId) {
    this.filmId = filmId;
  }


  public long getSaalId() {
    return saalId;
  }

  public void setSaalId(long saalId) {
    this.saalId = saalId;
  }

}
