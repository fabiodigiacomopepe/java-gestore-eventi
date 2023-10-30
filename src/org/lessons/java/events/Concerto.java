package org.lessons.java.events;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento {
    // ATTRIBUTI
    private LocalTime ora;
    private BigDecimal prezzo;

    // COSTRUTTORI
    public Concerto(String titolo, String data, int numero_posti_totale, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, numero_posti_totale);

        this.ora = ora;
        this.prezzo = prezzo;
    }

    // GETTER E SETTER
    // ORA
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    // PREZZO
    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // METODI
    public String DataOraFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALIAN);
        String dataFormattata = LocalDate.parse(getData()).format(formatter);
        return dataFormattata + " " + ora;
    }

    public String PrezzoFormattato() {
        DecimalFormat df = new DecimalFormat("##.00");
        String prezzoFormattato = df.format(new BigDecimal(String.valueOf(prezzo)));
        return prezzoFormattato;
    }

    @Override
    public String toString() {
        return DataOraFormattata() + " - " + getTitolo() + " - " + PrezzoFormattato() + "€";
    }
}
