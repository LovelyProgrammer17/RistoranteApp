package upo20050401;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe rappresentante un cliente. Ogni cliente ha un ID univoco, un anno di nascita,
 * una data di registrazione e una lista di ordini associati.
 * La classe permette di gestire clienti e i loro ordini in maniera strutturata.
 * Autori: 20050401 - 20057192
 */

Ciao come va
public class Cliente {
    private final String id;
    private int nascita;
    private LocalDate registrazione;
    private List<Ordine> ordini;
    static private List<String> ids;

    static { ids = new ArrayList<>(); }

    /**
     * Costruttore per creare un cliente fornendo un ID specifico.
     * Lancia un'eccezione se l'ID è già stato utilizzato.
     *
     * @param id l'ID scelto per il cliente
     * @param nascita l'anno di nascita del cliente
     * @param registrazione la data di registrazione del cliente
     * @throws IllegalArgumentException se l'ID fornito è già stato usato
     */
    public Cliente(String id, int nascita, LocalDate registrazione) throws IllegalArgumentException {
        if (ids.contains(id))
            throw new IllegalArgumentException("Id già utilizzato");
        this.id = id;
        this.nascita = nascita;
        this.registrazione = registrazione;

        this.ordini = new ArrayList<>();
        ids.add(id);
    }

    /**
     * Costruttore per creare un cliente generando automaticamente un ID univoco casuale.
     *
     * @param nascita l'anno di nascita del cliente
     * @param registrazione la data di registrazione del cliente
     * @throws IllegalArgumentException se non è possibile generare un ID univoco
     */
    public Cliente(int nascita, LocalDate registrazione) throws IllegalArgumentException {
        this(Cliente.generaId(), nascita, registrazione);
    }

    /**
     * Restituisce l'ID univoco del cliente.
     *
     * @return l'ID del cliente
     */

    public String getId() {
        return id;
    }

    /**
     * Restituisce l'anno di nascita del cliente.
     *
     * @return l'anno di nascita del cliente
     */
    public int getNascita() {
        return nascita;
    }

    /**
     * Modifica l'anno di nascita del cliente.
     *
     * @param nascita il nuovo anno di nascita del cliente
     */
    public void setNascita(int nascita) {
        this.nascita = nascita;
    }

    /**
     * Restituisce la data di registrazione del cliente.
     *
     * @return la data di registrazione del cliente
     */
    public LocalDate getRegistrazione() {
        return registrazione;
    }

    /**
     * Modifica la data di registrazione del cliente.
     *
     * @param registrazione la nuova data di registrazione
     */
    public void setRegistrazione(LocalDate registrazione) {
        this.registrazione = registrazione;
    }

    /**
     * Genera un ID univoco casuale nel formato "Id####" (dove #### è un numero casuale).
     * Assicura che l'ID generato non sia già utilizzato.
     *
     * @return un ID casuale univoco
     */
    public static String generaId() {
        SecureRandom rand = new SecureRandom();
        int n;

        // Genera un ID unico (numero a 4 cifre)
        do {
            n = rand.nextInt(9000) + 1000; // Numero tra 1000 e 9999
        } while (ids.contains(String.valueOf(n)));

        return String.valueOf(n);
    }


    /**
     * Restituisce il numero totale di clienti creati.
     *
     * @return il numero di clienti creati
     */
    public static int getNumClienti() {
        return ids.size();
    }

    /**
     * Aggiunge un ordine alla lista di ordini del cliente.
     *
     * @param numPiatti il numero di piatti inclusi nell'ordine
     * @param tipoMenu il tipo di menù scelto (ad esempio, carne, pesce, vegetariano)
     * @param data la data in cui è stato effettuato l'ordine
     */
    public void aggiungiOrdine(int numPiatti, String tipoMenu, LocalDate data) {
        Ordine o = new Ordine(numPiatti, tipoMenu, data);
        ordini.add(o);
    }

    /**
     * Restituisce il numero di ordini effettuati dal cliente.
     *
     * @return il numero di ordini del cliente
     */
    public int getNumOrdini() {
        return ordini.size();
    }

    /**
     * Verifica se esiste un ordine effettuato in una data specifica.
     *
     * @param data la data da verificare
     * @return true se esiste un ordine nella data specificata, false altrimenti
     */
    public boolean contieneOrdine(LocalDate data) {
        for (Ordine o : ordini) {
            if (o.getData().equals(data))
                return true;
        }
        return false;
    }

    /**
     * Restituisce un ordine effettuato in una data specifica, se esiste.
     *
     * @param data la data dell'ordine
     * @return l'ordine trovato, oppure null se non esiste un ordine per quella data
     */
    public Ordine getOrdine(LocalDate data) {
        for (Ordine o : ordini) {
            if (o.getData().equals(data))
                return o;
        }

        return null;
    }

    /**
     * Cancella un ordine effettuato in una data specifica, se esiste.
     *
     * @param data la data dell'ordine da cancellare
     */
    public void cancellaOrdine(LocalDate data) {
        if (this.contieneOrdine(data)) {
            ordini.remove(getOrdine(data));
        }
    }

    /**
     * Ritorna una rappresentazione testuale del cliente, includendo l'ID, l'anno di nascita
     * e la data di registrazione.
     *
     * @return una stringa che rappresenta il cliente
     */

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nascita=" + nascita + ", registrazione=" + registrazione + "]";
    }
}