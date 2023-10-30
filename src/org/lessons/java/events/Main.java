package org.lessons.java.events;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inizializzo lo scanner da tastiera
        Scanner scan = new Scanner(System.in);

        // Creo e istanzio un nuovo oggetto Concerto
        String orario = "21.10";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH.mm");
        LocalTime orarioConvertito = LocalTime.parse(orario, formato);
        String prezzoConcerto = "56";
        BigDecimal prezzoConvertito = new BigDecimal(prezzoConcerto);
        Concerto concerto1 = new Concerto("Concerto Metallica", "2024-08-20", 5000, orarioConvertito, prezzoConvertito);
        Evento eventoSagra = new Evento("Sagra Salsiccia", "2024-06-10", 200);
        Concerto concerto2 = new Concerto("Concerto Iron Maiden", "2024-07-02", 4300, orarioConvertito, prezzoConvertito);
        System.out.println(concerto1 + "\n");

        ProgrammEventi programmEventi = new ProgrammEventi("Programma Estivo");
        programmEventi.aggiungiEvento(concerto1);
        programmEventi.aggiungiEvento(eventoSagra);
        programmEventi.aggiungiEvento(concerto2);
        System.out.println(programmEventi);

        // Chiedo a utente dati dell'evento da inserire
        System.out.println("Inserisci un nuovo evento");
        System.out.print("Inserisci titolo: ");
        String titolo = scan.nextLine();
        System.out.print("Inserisci data nel formato yyyy-MM-dd: ");
        String data = scan.nextLine();
        System.out.print("Inserisci numero posti totale: ");
        String numeroPostiTotaleString = scan.nextLine();
        int numeroPostiTotale = Integer.parseInt(numeroPostiTotaleString);

        // Istanzio un nuovo evento con i dati forniti
        Evento evento = new Evento(titolo, data, numeroPostiTotale);
        System.out.println("Titolo evento: " + evento.getTitolo() + "\n" +
                "Data evento: " + evento.getData() + "\n" +
                "Numero posti totale: " + evento.getNumero_posti_totale());

        programmEventi.aggiungiEvento(evento);

        // Chiedo all'utente cosa vuole fare
        boolean exit = false;
        while (!exit) {
            System.out.println("Vuoi fare qualche operazione? S/N");
            System.out.print("Risposta: ");
            String sceltaOperazioni = scan.nextLine();
            switch (sceltaOperazioni) {
                case "S":
                    System.out.println("Scegli un'operazione");
                    System.out.println("1 - Prenota");
                    System.out.println("2 - Disdici");
                    System.out.print("Risposta: ");
                    String sceltaPrenotaDisdici = scan.nextLine();
                    switch (sceltaPrenotaDisdici) {
                        case "1":
                            System.out.print("Inserisci n. posti da prenotare: ");
                            String postiPrenotareString = scan.nextLine();
                            int postiPrenotare = Integer.parseInt(postiPrenotareString);
                            try {
                                if (postiPrenotare <= 0) {
                                    throw new IllegalArgumentException("Il numero di posti da prenotare non può essere negativo.");
                                }
                                evento.prenota(postiPrenotare);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Numero posti PRENOTATI: " + evento.getNumero_posti_prenotati() + "\n" +
                                    "Numero posti DISPONIBILI: " + evento.getNumero_posti_totale());
                            break;
                        case "2":
                            System.out.print("Inserisci n. posti da disdire: ");
                            String postiDisdireString = scan.nextLine();
                            int postiDisdire = Integer.parseInt(postiDisdireString);
                            try {
                                if (postiDisdire <= 0) {
                                    throw new IllegalArgumentException("Il numero di posti da disdire non può essere negativo.");
                                }
                                evento.disdici(postiDisdire);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Numero posti PRENOTATI: " + evento.getNumero_posti_prenotati() + "\n" +
                                    "Numero posti DISPONIBILI: " + evento.getNumero_posti_totale());
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            break;
                    }
                    break;
                case "N":
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

        System.out.println(programmEventi);

        // Chiudo lo scanner da tastiera
        scan.close();

    }
}
