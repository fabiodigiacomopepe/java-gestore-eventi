package org.lessons.java.events;

import java.util.ArrayList;
import java.util.List;

public class ProgrammEventi {
    // ATTRIBUTI
    private String titolo;
    private List<Evento> eventi;

    // COSTRUTTORI
    public ProgrammEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public String getTitolo() {
        return titolo;
    }

    // METODI
    public void aggiungiEvento(Evento evento_da_aggiungere) {
        eventi.add(evento_da_aggiungere);
    }

    public List<Evento> eventiPresentiInData(String data) {
        List<Evento> eventiData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiData.add(evento);
            }
        }
        return eventiData;
    }

    public int eventiInProgramma() {
        return eventi.size();
    }

    public void svuotaLista() {
        eventi.clear();
    }

    @Override
    public String toString() {
        // Creo oggetto StrinBuilder per costruire la stringa
        StringBuilder stringaFinale = new StringBuilder();
        // Aooendo il titolo e vado a capo
        stringaFinale.append(getTitolo()).append("\n");
        // Duplico la lista di eventi esistente
        List<Evento> eventiOrdinati = new ArrayList<>(eventi);
        // Riordino gli eventi in base alla data con metodo sort utilizzando lambda/compareTo() che ritorna:
        // valore negativo se la data di evento1 è < della data di evento2
        // valore positivo se la data di evento1 è > della data di evento2
        // zero se le date sono uguali
        eventiOrdinati.sort((evento1, evento2) -> evento1.getData().compareTo(evento2.getData()));
        // Ciclo sull'ArrayList
        for (Evento evento : eventiOrdinati) {
            // Aggiungo alla stringa finale data - titolo di ogni evento
            stringaFinale.append(evento.getData()).append(" - ").append(evento.getTitolo()).append("\n");
        }
        // Ritorno la stringa
        return stringaFinale.toString();
    }
}
