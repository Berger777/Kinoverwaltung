package Util;

import Entities.Film;
import Entities.Sitz;
import Entities.Vorfuehrung;
import Services.DatabaseService;

import java.util.ArrayList;
import java.util.Optional;

public class Buchung {
    boolean rabatt = false;
    int anz = 0;
    DatabaseService databaseService = new DatabaseService();
    Vorfuehrung vorfuehrung = new Vorfuehrung();
    ArrayList<Sitz> sitzBuchungen = new ArrayList<>();
    double preis = 0;

    public boolean isRabatt() {
        return rabatt;
    }

    public void setRabatt(boolean rabatt) {
        this.rabatt = rabatt;
    }

    public Vorfuehrung getVorfuehrung() {
        return vorfuehrung;
    }

    public void setVorfuehrung(Vorfuehrung vorfuehrung) {
        this.sitzBuchungen = new ArrayList<>();
        this.vorfuehrung = vorfuehrung;
    }

    public ArrayList<Sitz> getSitzBuchungen() {
        return sitzBuchungen;
    }

    public void setSitzBuchungen(ArrayList<Sitz> sitzBuchungen) {
        this.sitzBuchungen = sitzBuchungen;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void errechneUndSetzePreis(){
        preis = 0;
        anz = 0;
        double filmPreis = 0;
        Optional<Film> opt = databaseService.getFilmeAusDB().stream().filter(film -> vorfuehrung.getFilm().getFilmId().equals(film.getFilmId())).findFirst();
        if (opt.isPresent()) filmPreis = Double.parseDouble(opt.get().getPreis());

        for (Sitz s:sitzBuchungen) {
            switch (s.getKategorie().getBezeichnung()) {
                case "BonzenSessel":
                    preis += 5;
                    break;
                case "RoyalSofa":
                    preis += 2.5;
                    break;
                case "Standard":
                    preis += 1;
                    break;
            }
            anz++;
            preis += filmPreis;
        }

        if (rabatt){
            preis = preis*0.9;
        }
    }

    @Override
    public String toString() {
        errechneUndSetzePreis();
        return "Buchung für Vorführung:\n"+ vorfuehrung.getFilm().getTitel() + " , " + vorfuehrung.getDatum() + " , " + vorfuehrung.getZeit() + " , " + vorfuehrung.getAufschlag() + " , " + vorfuehrung.getSaal().getSaalname()+ "\n"+
                "Preis: " + Math.round(preis) + "€\n" + "Anzahl-Plätze: " + anz + "\n";
    }
}
