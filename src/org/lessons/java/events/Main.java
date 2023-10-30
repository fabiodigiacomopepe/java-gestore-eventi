package org.lessons.java.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
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

        String data = null;
        boolean exitData = false;
        while (!exitData) {
            System.out.print("Inserisci data nel formato yyyy-MM-dd: ");
            data = scan.nextLine();
            if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Inserire la data nel formato yyyy-MM-dd.");
            } else {
                LocalDate localDate = LocalDate.now();
                LocalDate data_da_inserire = LocalDate.parse(data);
                if (data_da_inserire.isBefore(localDate)) {
                    System.out.println("La data inserita è già passata.");
                } else {
                    exitData = true;
                }
            }
        }

        int numeroPostiTotale = 0;
        boolean exitPosti = false;
        while (!exitPosti) {
            System.out.print("Inserisci numero posti totale: ");
            String numeroPostiTotaleString = scan.nextLine();
            try {
                numeroPostiTotale = Integer.parseInt(numeroPostiTotaleString);
                if (numeroPostiTotale <= 0) {
                    System.out.println("Inserire un numero positivo.");
                } else {
                    exitPosti = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero.");
            }
        }

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
            String sceltaOperazioni = scan.nextLine().toUpperCase();
            switch (sceltaOperazioni) {
                case "S":
                    System.out.println("Scegli un'operazione");
                    System.out.println("1 - Prenota");
                    System.out.println("2 - Disdici");
                    System.out.print("Risposta: ");
                    String sceltaPrenotaDisdici = scan.nextLine();
                    switch (sceltaPrenotaDisdici) {
                        case "1":
                            boolean exitPostiPrenotare = false;
                            while (!exitPostiPrenotare) {
                                System.out.print("Inserisci n. posti da prenotare: ");
                                String postiPrenotareString = scan.nextLine();
                                try {
                                    int postiPrenotare = Integer.parseInt(postiPrenotareString);
                                    if (postiPrenotare <= 0) {
                                        System.out.println("Il numero di posti da prenotare non può essere negativo.");
                                    } else {
                                        evento.prenota(postiPrenotare);
                                        exitPostiPrenotare = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Inserire un numero.");
                                }
                            }
                            System.out.println("Numero posti PRENOTATI: " + evento.getNumero_posti_prenotati() + "\n" +
                                    "Numero posti DISPONIBILI: " + evento.getNumero_posti_totale());
                            break;
                        case "2":
                            boolean exitPostiDisdire = false;
                            while (!exitPostiDisdire) {
                                System.out.print("Inserisci n. posti da disdire: ");
                                String postiDisdireString = scan.nextLine();
                                try {
                                    int postiDisdire = Integer.parseInt(postiDisdireString);
                                    if (postiDisdire <= 0) {
                                        System.out.println("Il numero di posti da disdire non può essere negativo.");
                                    } else {
                                        evento.disdici(postiDisdire);
                                        exitPostiDisdire = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Inserire un numero.");
                                }
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
