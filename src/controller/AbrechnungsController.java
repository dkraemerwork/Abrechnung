/**
 * Sample Skeleton for 'AbrechnungsView.fxml' Controller Class
 */

package controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Abrechnung;
import model.Buchung;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class AbrechnungsController {

    Abrechnung abrechnung;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="buchungsTabelle"
    private TableView<Buchung> buchungsTabelle; // Value injected by FXMLLoader
    @FXML // fx:id="datumColumn"
    private TableColumn<Buchung, LocalDate> datumColumn; // Value injected by FXMLLoader
    @FXML // fx:id="stundenColumn"
    private TableColumn<Buchung, Double> stundenColumn; // Value injected by FXMLLoader
    @FXML // fx:id="stundenLohnColumn"
    private TableColumn<Buchung, Double> stundenLohnColumn; // Value injected by FXMLLoader
    @FXML // fx:id="lohnColumn"
    private TableColumn<Buchung, Double> lohnColumn; // Value injected by FXMLLoader
    @FXML // fx:id="summeLabel"
    private Label summeLabel; // Value injected by FXMLLoader
    @FXML // fx:id="dateSelectFeld"
    private DatePicker dateSelectFeld; // Value injected by FXMLLoader
    @FXML // fx:id="stundenAnzahlFeld"
    private TextField stundenAnzahlFeld; // Value injected by FXMLLoader
    @FXML // fx:id="stundenLohnFeld"
    private TextField stundenLohnFeld; // Value injected by FXMLLoader
    @FXML // fx:id="speichernButton"
    private Button speichernButton; // Value injected by FXMLLoader
    @FXML // fx:id="aendernButton"
    private Button aendernButton; // Value injected by FXMLLoader
    @FXML // fx:id="loeschenButton"
    private Button loeschenButton; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        abrechnung = new Abrechnung();
        createBookingTable();

        addTableItemChangeListener();

        addEntryFormatChangeListener();

        assert buchungsTabelle != null : "fx:id=\"buchungsTabelle\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert datumColumn != null : "fx:id=\"datumColumn\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert stundenColumn != null : "fx:id=\"stundenColumn\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert stundenLohnColumn != null : "fx:id=\"stundenLohnColumn\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert lohnColumn != null : "fx:id=\"lohnColumn\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert summeLabel != null : "fx:id=\"summeLabel\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert dateSelectFeld != null : "fx:id=\"dateSelectFeld\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert stundenAnzahlFeld != null : "fx:id=\"stundenAnzahlFeld\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert stundenLohnFeld != null : "fx:id=\"stundenLohnFeld\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert speichernButton != null : "fx:id=\"speichernButton\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert aendernButton != null : "fx:id=\"aendernButton\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";
        assert loeschenButton != null : "fx:id=\"loeschenButton\" was not injected: check your FXML file 'AbrechnungsView.fxml'.";

    }

    @FXML
    void deleteSelectedEntry(MouseEvent event) {
        abrechnung.deleteSelectedEntry(buchungsTabelle.getSelectionModel().getSelectedItem());
    }

    @FXML
    void loadEntry(MouseEvent event) {
        if (isSelected()) {
            Buchung selected = buchungsTabelle.getSelectionModel().getSelectedItem();
            dateSelectFeld.setValue(selected.getLocalDate());
            stundenAnzahlFeld.setText(Double.toString(selected.getStundenAnzahl()));
            stundenLohnFeld.setText(Double.toString(selected.getStundenLohn()));
        }
    }

    @FXML
    void safeEntry(MouseEvent event) {
        ObservableList<Buchung> buchungsList = buchungsTabelle.getItems();
        if (fieldsAreEmpty()) {
            abrechnung.setBuchungsList(buchungsList);
            abrechnung.safeBooking(getGesamtLohnTag(), dateSelectFeld.getValue(),
                    Double.parseDouble(stundenLohnFeld.getText()), Double.parseDouble(stundenAnzahlFeld.getText()));
        }
    }

    private Double getGesamtLohnTag() {
        return Double.parseDouble(stundenLohnFeld.getText()) * Double.parseDouble(stundenAnzahlFeld.getText());
    }

    private void createBookingTable() {

        datumColumn.setCellValueFactory(cellDataFeatures
                -> cellDataFeatures.getValue().localDateObjectProperty());

        stundenColumn.setCellValueFactory(cellDataFeatures
                -> cellDataFeatures.getValue().stundenZahlProperty().asObject());

        stundenLohnColumn.setCellValueFactory(cellDataFeatures
                -> cellDataFeatures.getValue().stundenLohnProperty().asObject());

        lohnColumn.setCellValueFactory(cellDataFeatures
                -> cellDataFeatures.getValue().gesamtLohnTagProperty().asObject());

    }


    private void addEntryFormatChangeListener() {
        DecimalFormat format = new DecimalFormat("#.0");
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.UK));

        setDecimalTextFormatter(format, stundenAnzahlFeld);

        setDecimalTextFormatter(format, stundenLohnFeld);
    }

    private void setDecimalTextFormatter(DecimalFormat format, TextField stundenAnzahlFeld) {
        stundenAnzahlFeld.setTextFormatter(new TextFormatter<>(c ->
        {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                return c;
            }
        }));
    }

    private void addTableItemChangeListener() {
        buchungsTabelle.getItems().addListener((ListChangeListener) change -> {
            Double summe = 0.0;
            for (Buchung item : buchungsTabelle.getItems()) {
                summe = summe + item.getGesamtLohnTag();
            }
            summeLabel.setText(String.valueOf(summe));
        });
    }

    private boolean fieldsAreEmpty() {
        return dateSelectFeld.getValue() != null
                && !stundenLohnFeld.getText().isEmpty()
                && !stundenAnzahlFeld.getText().isEmpty();
    }

    private boolean isSelected() {
        return buchungsTabelle.getSelectionModel().getSelectedItem() != null;
    }

}
