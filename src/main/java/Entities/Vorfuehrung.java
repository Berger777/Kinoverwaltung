package Entities;


public class Vorfuehrung {

  private String vorfuehrungId;
  private String datum;
  private String zeit;
  private String aufschlag;
  private Film film;
  private Saal saal;

  public String getVorfuehrungId() {
    return vorfuehrungId;
  }

  public void setVorfuehrungId(String vorfuehrungId) {
    this.vorfuehrungId = vorfuehrungId;
  }

  public String getDatum() {
    return datum;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }

  public String getZeit() {
    return zeit;
  }

  public void setZeit(String zeit) {
    this.zeit = zeit;
  }

  public String getAufschlag() {
    return aufschlag;
  }

  public void setAufschlag(String aufschlag) {
    this.aufschlag = aufschlag;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public Saal getSaal() {
    return saal;
  }

  public void setSaal(Saal saal) {
    this.saal = saal;
  }
}
