package upo20050401;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe principale per la gestione di un ristorante.
 * Include funzionalità per gestire clienti, ordini e statistiche.
 * Autori: 20050401 - 20057192
 */

public class MioRistorante {
    private static final ArrayList<Cliente> clienti;
    private static final Scanner tastiera;
    private static final int[][] numeroPiatti = new int[100][4]; // Esempio di inizializzazione
    private static final String[][] menu = new String[100][4];   // Esempio di inizializzazione
    private static final HashMap<String, Integer> menuCount = new HashMap<>();
    private static int numRecord = 0;

    static {
        clienti = new ArrayList<>();
        tastiera = new Scanner(System.in);
    }
    /**
     * Metodo principale che avvia il programma.
     * Mostra un menu e gestisce le scelte dell'utente.
     *
     * @param args argomenti della riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        int scelta;

        do {
            menu();
            scelta = tastiera.nextInt();
            esegui(scelta);
        } while (scelta != 100);
    }
    /**
     * Stampa il menu delle operazioni disponibili per l'utente.
     */
    public static void menu() {
        System.out.println("Scegli cosa vuoi fare: ");
        System.out.println("1   - Aggiungi un nuovo cliente");
        System.out.println("2   - Cerca un cliente per id");
        System.out.println("3   - Cerca un cliente per età");
        System.out.println("4   - Aggiungi un'ordinazione");
        System.out.println("5   - Stampa le statistiche sul numero di piatti ordinati");
        System.out.println("6   - Stampa le statistiche sul tipo di menù ordinato");
        System.out.println("100 - Esci dall'applicazione");
        System.out.println(">");
    }

    /**
     * Esegue l'operazione scelta dall'utente.
     *
     * @param scelta il numero corrispondente all'operazione selezionata
     */
    public static void esegui(int scelta) {
        switch (scelta) {
            case 1 -> aggiungiCliente();
            case 2 -> cercaCliente();
            case 3 -> cercaClienteEta();
            case 4 -> aggiungiOrdine();
            case 5 -> statisticheNumeroPiatti();
            case 6 -> statisticheMenu();
            case 100 -> System.out.println("L'applicazione si sta chiudendo");
            default -> System.out.println("Scelta non valida");
        }
    }

    /**
     * Calcola e stampa statistiche sui numeri di piatti ordinati.
     * Le statistiche includono il massimo, il minimo e la media.
     */
    public static void statisticheNumeroPiatti() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int totale = 0;
        int count = 0;

        for (int i = 0; i < numRecord; i++) {
            for (int j = 0; j < numeroPiatti[i].length; j++) {
                int piatti = numeroPiatti[i][j];
                if (piatti > 0) {
                    totale += piatti;
                    count++;
                    max = Math.max(max, piatti);
                    min = Math.min(min, piatti);
                }
            }
        }

        double media = count > 0 ? (double) totale / count : 0;

        System.out.println("Statistiche Numero Piatti:");
        System.out.println("Max: " + max);
        System.out.println("Min: " + (count > 0 ? min : "Nessun ordine"));
        System.out.println("Media: " + media);
    }

    /**
     * Calcola e stampa statistiche sui tipi di menu ordinati.
     * Include il conteggio di ogni tipo di menu.
     */
    public static void statisticheMenu() {
        menuCount.clear();

        for (int i = 0; i < numRecord; i++) {
            for (int j = 0; j < menu[i].length; j++) {
                String tipoMenu = menu[i][j];
                if (tipoMenu != null) {
                    menuCount.put(tipoMenu, menuCount.getOrDefault(tipoMenu, 0) + 1);
                }
            }
        }

        System.out.println("Statistiche Menù:");
        for (var entry : menuCount.entrySet()) {
            System.out.println("Tipo di menù: " + entry.getKey() + ", Numero di ordini: " + entry.getValue());
        }
    }

    /**
     * Aggiunge un nuovo cliente interattivamente.
     * L'utente deve fornire ID, anno di nascita e data di registrazione.
     */
    public static void aggiungiCliente() {
        System.out.println("Id del nuovo cliente: ");
        String id = tastiera.next();
        System.out.println("Anno di nascita del cliente: ");
        int anno = tastiera.nextInt();
        LocalDate registrazione = LocalDate.now();

        try {
            aggiungiClienteA(id, anno, registrazione);
            System.out.println("Cliente aggiunto correttamente!");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    /**
     * Aggiunge un cliente alla lista.
     *
     * @param id identificativo univoco del cliente
     * @param nascita anno di nascita del cliente
     * @param reg data di registrazione del cliente
     * @throws IllegalArgumentException se l'id è già usato
     */
    public static void aggiungiClienteA(String id, int nascita, LocalDate reg) throws IllegalArgumentException {
        for (Cliente c : clienti) {
            if (c.getId().equals(id)) {
                throw new IllegalArgumentException("ID cliente già in uso: " + id);
            }
        }

        Cliente c;
        if (id.isEmpty()) {
            c = new Cliente(nascita, reg);
        } else {
            c = new Cliente(id, nascita, reg);
        }
        clienti.add(c);
    }


    /**
     * Cerca un cliente per ID e lo stampa se trovato.
     */
    public static void cercaCliente() {
        System.out.println("Inserisci ID da cercare: ");
        String id = tastiera.next();

        Cliente c = cercaClienteA(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Cliente non trovato!");
        }
    }

    /**
     * Cerca un cliente per ID.
     *
     * @param id l'id del cliente da cercare
     * @return il cliente trovato oppure null se non esiste
     */
    public static Cliente cercaClienteA(String id) {
        for (Cliente c : clienti) {
            if (c.getId().equals(id)) {
                System.out.println("Cliente trovato: " + c);
                return c;
            }
        }
        System.out.println("Cliente non trovato con ID: " + id);
        return null;
    }


    /**
     * Cerca un cliente per età e lo stampa se trovato.
     */
    public static void cercaClienteEta() {
        System.out.println("Inserisci età minima dei clienti: ");
        int min = tastiera.nextInt();
        System.out.println("Inserisci età massima dei clienti: ");
        int max = tastiera.nextInt();

        ArrayList<Cliente> ricerca = cercaClienteEtaA(min, max);
        if (ricerca.isEmpty()) {
            System.out.println("Non vi sono clienti nel range di età inserito!");
        } else {
            for (Cliente c : ricerca) {
                System.out.println(c);
            }
        }
    }

    /**
     * Cerca un cliente per età.
     * @param minEta range minore<etò
     * @param maxEta range maggiore<etò
     * @return il cliente trovato oppure null se non esiste
     */
    public static ArrayList<Cliente> cercaClienteEtaA(int minEta, int maxEta) {
        ArrayList<Cliente> clientiTrovati = new ArrayList<>();
        for (Cliente cliente : clienti) {
            int eta = LocalDate.now().getYear() - cliente.getNascita();
            if (eta >= minEta && eta <= maxEta) {
                clientiTrovati.add(cliente);
            }
        }
        return clientiTrovati;
    }




    /**
     * Aggiunge un ordine.
     */
    public static void aggiungiOrdine() {
        System.out.println("Id del cliente che sta ordinando: ");
        String id = tastiera.next();
        System.out.println("Numero di piatti ordinati: ");
        int numPiatti = tastiera.nextInt();
        System.out.println("Tipo di menù scelto: ");
        String tipo = tastiera.next();

        aggiungiOrdineA(id, numPiatti, tipo);
    }

    /**
     * Aggiunge un ordine.
     * @param id identificativo cliente
     * @param numPiatti numero piatti
     * @param tipoMenu tipo di menu
     */
    public static void aggiungiOrdineA(String id, int numPiatti, String tipoMenu) {
        Cliente c = cercaClienteA(id);
        if (c == null) {
            System.out.println("Errore: Cliente non trovato. L'inserimento dell'ordine non è possibile!");
            return;
        }

        c.aggiungiOrdine(numPiatti, tipoMenu, LocalDate.now());
        System.out.println("Ordine aggiunto con successo per il cliente: " + id);
    }

}
