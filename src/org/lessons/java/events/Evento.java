package org.lessons.java.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Evento {
    // ATTRIBUTI
    private String titolo;
    private String data;
    private int numero_posti_totale;
    private int numero_posti_prenotati;

    // COSTRUTTORI
    public Evento(String titolo, String data, int numero_posti_totale) throws IllegalArgumentException {
        this.titolo = titolo;

        // Controllo se la data è già passata
        LocalDate localDate = LocalDate.now();
        if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Inserire la data nel formato yyyy-MM-dd.");
        } else {
            LocalDate data_da_inserire = LocalDate.parse(data);
            try {
                if (data_da_inserire.isBefore(localDate)) {
                    throw new IllegalArgumentException("La data dell'evento è già passata.");
                }
                this.data = data;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Controllo se il numero di posti totali è positivo
        try {
            if (numero_posti_totale <= 0) {
                throw new IllegalArgumentException("Il numero di posti totali deve essere positivo/maggiore di zero.");
            }
            this.numero_posti_totale = numero_posti_totale;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        this.numero_posti_prenotati = 0;
    }

    // GETTER E SETTER
    // TITOLO
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    // DATA
    public String getData() {
        return data;
    }

    public void setData(String data) throws IllegalArgumentException {
        LocalDate localDate = LocalDate.now();
        if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Inserire la data nel formato yyyy-MM-dd.");
        } else {
            LocalDate data_da_inserire = LocalDate.parse(data);
            try {
                if (data_da_inserire.isBefore(localDate)) {
                    throw new IllegalArgumentException("La data dell'evento è già passata.");
                }
                this.data = data;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // NUMERO POSTI TOTALI
    public int getNumero_posti_totale() {
        return numero_posti_totale;
    }

    // NUMERO POSTI PRENOTATI
    public int getNumero_posti_prenotati() {
        return numero_posti_prenotati;
    }

    // METODI
    public void prenota(int posti_da_prenotare) throws IllegalArgumentException {
        LocalDate localDate = LocalDate.now();
        LocalDate data_inserita = LocalDate.parse(data);
        try {
            if (data_inserita.isBefore(localDate)) {
                throw new IllegalArgumentException("La data dell'evento è già passata.");
            }
            if (posti_da_prenotare > numero_posti_totale) {
                throw new IllegalArgumentException("Non puoi prenotare più posti di quelli disponibili.");
            }
            numero_posti_prenotati += posti_da_prenotare;
            numero_posti_totale -= posti_da_prenotare;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void disdici(int posti_da_rimuovere) throws IllegalArgumentException {
        LocalDate localDate = LocalDate.now();
        LocalDate data_inserita = LocalDate.parse(data);
        try {
            if (data_inserita.isBefore(localDate)) {
                throw new IllegalArgumentException("La data dell'evento è già passata.");
            }
            if (posti_da_rimuovere > numero_posti_prenotati) {
                throw new IllegalArgumentException("Non puoi disdire più posti di quelli prenotati.");
            }
            numero_posti_prenotati -= posti_da_rimuovere;
            numero_posti_totale += posti_da_rimuovere;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALIAN);
        String dataFormattata = LocalDate.parse(data).format(formatter);

        return dataFormattata + " - " + titolo;
    }
}
