package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Buchung {

    private DoubleProperty stundenZahl = new SimpleDoubleProperty();
    private DoubleProperty stundenLohn = new SimpleDoubleProperty();
    private DoubleProperty gesamtLohnTag = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> localDate = new SimpleObjectProperty();

    public Buchung(Double gesamtLohnTag, java.time.LocalDate dateValue, Double stundenValue, Double stundenAnzahl) {
        stundenZahl.set(stundenAnzahl);
        stundenLohn.set(stundenValue);
        this.gesamtLohnTag.set(gesamtLohnTag);
        localDate.set(dateValue);
        
    }

    public Double getStundenAnzahl() {
        return stundenZahl.get();
    }

    public void setStundenZahl(Double stunden) {
        stundenZahl.set(stunden);
    }

    public DoubleProperty stundenZahlProperty() {
        return stundenZahl;
    }

    public Double getStundenLohn() {
        return stundenLohn.get();
    }

    public void setStundenLohn(Double stundenLohn) {
        this.stundenLohn.set(stundenLohn);
    }

    public DoubleProperty stundenLohnProperty() {
        return stundenLohn;
    }

    public Double getGesamtLohnTag() {
        return gesamtLohnTag.get();
    }

    public void setGesamtLohnTag(Double gesamtLohnTag) {
        this.gesamtLohnTag.set(gesamtLohnTag);
    }

    public DoubleProperty gesamtLohnTagProperty() {
        return gesamtLohnTag;
    }

    public LocalDate getLocalDate() {
        return localDate.get();
    }

    public void setLocalDate(LocalDate LocalDate) {
        this.localDate.set(LocalDate);
    }

    public ObjectProperty<LocalDate> localDateObjectProperty() {
        return localDate;
    }
}
