package upo20050401;

import java.time.LocalDate;

/**
 * Classe che rappresenta un ordine effettuato da un cliente.
 * Ogni ordine include il numero di piatti ordinati, il tipo di menu scelto e la data dell'ordine.
 * Autori: 20050401 - 20057192
 */
public class Ordine {
    private int numPiatti;
    private String tipoMenu;
    private final LocalDate data;

    /**
     * Costruttore per creare un ordine.
     *
     * @param numPiatti il numero di piatti ordinati
     * @param tipoMenu il tipo di menu ordinato
     * @param data la data in cui è stato effettuato l'ordine
     */
    public Ordine(int numPiatti, String tipoMenu, LocalDate data) {
        this.numPiatti = numPiatti;
        this.tipoMenu = tipoMenu;
        this.data = data;
    }

    /**
     * Restituisce il numero di piatti inclusi nell'ordine.
     *
     * @return il numero di piatti nell'ordine
     */
    public int getNumPiatti() {
        return numPiatti;
    }

    /**
     * Modifica il numero di piatti inclusi nell'ordine.
     *
     * @param numPiatti il nuovo numero di piatti dell'ordine
     */
    public void setNumPiatti(int numPiatti) {
        this.numPiatti = numPiatti;
    }

    /**
     * Restituisce il tipo di menu ordinato.
     *
     * @return una stringa che descrive il tipo di menu ordinato
     */
    public String getTipoMenu() {
        return tipoMenu;
    }

    /**
     * Modifica il tipo di menu ordinato.
     *
     * @param tipoMenu il nuovo tipo di menu dell'ordine
     */
    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    /**
     * Restituisce la data in cui è stato effettuato l'ordine.
     *
     * @return la data dell'ordine
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Restituisce una rappresentazione testuale dell'ordine.
     * Il formato include il numero di piatti, il tipo di menu e la data dell'ordine.
     *
     * @return una stringa che rappresenta l'ordine
     */
    @Override
    public String toString() {
        return numPiatti + " " + tipoMenu + " " + data;
    }


    /**
     * Confronta questo ordine con un altro oggetto per verificare l'uguaglianza.
     * Due ordini sono considerati uguali se hanno lo stesso numero di piatti,
     * lo stesso tipo di menu e la stessa data.
     *
     * @param obj l'oggetto da confrontare con questo ordine
     * @return true se i due ordini sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ordine) {
            Ordine ordine = (Ordine) obj;
            return this.data.equals(ordine.data) && this.numPiatti == ordine.numPiatti && this.tipoMenu.equals(ordine.tipoMenu);
        } else {
            return false;
        }
    }
}