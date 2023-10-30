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
        String listaProgrammi = "";
        for (Evento evento : eventi) {
            listaProgrammi += evento.getData() + " - " + evento.getTitolo() + "\n";
        }
        return listaProgrammi;
    }
}
