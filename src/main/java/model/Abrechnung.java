package model;

import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Abrechnung {
    private ObservableList<Buchung> buchungsList;

    public List<Buchung> getBuchungsList() {
        return buchungsList;
    }

    public void setBuchungsList(ObservableList<Buchung> buchungsList) {
        this.buchungsList = buchungsList;
    }

    public void sortList() {
        buchungsList.sort(Comparator.comparing(Buchung::getLocalDate));
    }


    public void deleteSelectedEntry(Buchung selectedItem) {
        buchungsList.remove(selectedItem);
    }

    public void safeBooking(Double gesamtLohnTag, LocalDate dateValue, Double stundenValue, Double stundenAnzahl) {
        Buchung buchung = new Buchung(gesamtLohnTag, dateValue, stundenValue, stundenAnzahl);

        Buchung inUsage = hasUsage(buchung);
        if (inUsage != null) {
            overrideBooking(inUsage, buchung);
        } else {
            buchungsList.add(buchung);

        }
        sortList();
    }

    private Buchung hasUsage(Buchung buchung) {
        Buchung returnBuchung = null;
        for (Buchung buchungEntry : getBuchungsList()) {
            if (buchungEntry.getLocalDate().equals(buchung.getLocalDate())) {
                returnBuchung = buchungEntry;
            }
        }
        return returnBuchung;
    }

    public void overrideBooking(Buchung inUsage, Buchung buchung) {
        inUsage.setLocalDate(buchung.getLocalDate());
        inUsage.setStundenZahl(buchung.getStundenAnzahl());
        inUsage.setGesamtLohnTag(buchung.getGesamtLohnTag());
        inUsage.setStundenLohn(buchung.getStundenLohn());
    }
}
