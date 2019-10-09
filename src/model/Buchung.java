package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Buchung {

    private IntegerProperty stundenZahl = new SimpleIntegerProperty();

    public Buchung(int gesamtLohnTag, java.time.LocalDate dateValue, String stundenValue, String stundenAnzahl) {
    }

    public int getStundenAnzahl(){
        return stundenZahl.get();
    }

    public void setStundenZahl(int stunden){
        stundenZahl.set(stunden);
    }

    public IntegerProperty stundenZahlProperty(){
        return stundenZahl;
    }

    private IntegerProperty stundenLohn = new SimpleIntegerProperty();

    public int getStundenLohn(){
        return stundenLohn.get();
    }

    public void setStundenLohn(int stundenLohn){
        this.stundenLohn.set(stundenLohn);
    }

    public IntegerProperty stundenLohnProperty(){
        return stundenLohn;
    }

    private IntegerProperty gesamtLohnTag = new SimpleIntegerProperty();

    public int getGesamtLohnTag(){
        return gesamtLohnTag.get();
    }

    public void setGesamtLohnTag(int gesamtLohnTag){
        this.gesamtLohnTag.set(gesamtLohnTag);
    }

    public IntegerProperty gesamtLohnTagProperty(){
        return gesamtLohnTag;
    }

    private ObjectProperty<LocalDate> LocalDate = new SimpleObjectProperty();

    public LocalDate getLocalDate(){
        return LocalDate.get();
    }

    public void setLocalDate(LocalDate LocalDate){
        this.LocalDate.set(LocalDate);
    }

    public ObjectProperty<LocalDate> localDateObjectProperty(){
        return LocalDate;
    }
}
