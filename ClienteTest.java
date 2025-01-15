import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import upo20050401.Cliente;
import upo20050401.Ordine;

import java.time.LocalDate;

/**
 * Classe di test per verificare il corretto funzionamento della classe Cliente.
 * Autori: 20050401 - 20057192
 */
public class ClienteTest {

    /**
     * Test per verificare la creazione di un oggetto Cliente con ID specificato.
     * Controlla che l'oggetto sia non nullo e che i valori dei campi siano correttamente inizializzati.
     */
    @Test
    void creaClienteConIdTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente("1001", 2003, LocalDate.of(2023, 4, 23));
            Assertions.assertNotNull(c);
            Assertions.assertEquals("1001", c.getId());
            Assertions.assertEquals(2003, c.getNascita());
            Assertions.assertEquals(LocalDate.of(2023, 4, 23), c.getRegistrazione());
        });
    }

    /**
     * Test per verificare la creazione di un oggetto Cliente senza specificare l'ID.
     * Controlla che l'oggetto sia non nullo e che i valori dei campi siano correttamente inizializzati.
     */
    @Test
    void creaClienteSenzaIdTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente(2003, LocalDate.of(2024, 12, 5));
            Assertions.assertNotNull(c);
            Assertions.assertEquals(2003, c.getNascita());
            Assertions.assertEquals(LocalDate.of(2024, 12, 5), c.getRegistrazione());
        });
    }

    /**
     * Test per verificare la generazione di un nuovo ID cliente tramite il metodo generaId.
     * Controlla che l'ID generato non sia nullo.
     */
    @Test
    void generaIdTest() {
        String newId = Cliente.generaId();
        Assertions.assertNotNull(newId);
        // Assertions.assertFalse(Cliente.listaIds.contains(newId));
    }

    /**
     * Test per verificare il numero totale di clienti.
     * Controlla che il numero aumenti dopo la creazione di un nuovo cliente.
     */
    @Test
    void getNumClientiTest() {
        Assertions.assertDoesNotThrow(() -> {
            int numClienti = Cliente.getNumClienti();
            Cliente c = new Cliente(2003, LocalDate.of(2017, 4, 19));
            Assertions.assertEquals(numClienti + 1, Cliente.getNumClienti());
        });
    }

    /**
     * Test per verificare l'aggiunta di un ordine a un cliente.
     * Controlla che il numero di ordini del cliente aumenti correttamente.
     */
    @Test
    void addOrdineTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente(2003, LocalDate.of(2023, 4, 17));
            c.aggiungiOrdine(1, "Pizza", LocalDate.of(2024, 5, 9));
            Assertions.assertEquals(1, c.getNumOrdini());
        });
    }

    /**
     * Test per verificare la presenza di un ordine tramite il metodo containsOrdine.
     * Controlla che il metodo restituisca true se l'ordine esiste e false altrimenti.
     */
    @Test
    void containsOrdineTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente(2003, LocalDate.of(2020, 12, 31));
            c.aggiungiOrdine(3, "Pizza", LocalDate.of(2024, 12, 5));
            Assertions.assertTrue(c.contieneOrdine(LocalDate.of(2024, 12, 5)));
            Assertions.assertFalse(c.contieneOrdine(LocalDate.of(2020, 12, 31)));
        });
    }

    /**
     * Test per verificare il recupero di un ordine tramite il metodo getOrdine.
     * Controlla che l'ordine restituito corrisponda a quello aggiunto.
     */
    @Test
    void getOrdineTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente(2003, LocalDate.of(2022, 6, 23));
            Ordine o = new Ordine(3, "Vegano", LocalDate.of(2024, 12, 5));
            c.aggiungiOrdine(3, "Vegano", LocalDate.of(2024, 12, 5));
            Assertions.assertEquals(o, c.getOrdine(LocalDate.of(2024, 12, 5)));
        });
    }

    /**
     * Test per verificare la cancellazione di un ordine tramite il metodo cancellaOrdine.
     * Controlla che l'ordine non sia più disponibile dopo la cancellazione.
     */
    @Test
    void deleteOrdineTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente(2003, LocalDate.of(2020, 2, 1));
            c.aggiungiOrdine(3, "Pizza", LocalDate.of(2024, 11, 4));
            c.cancellaOrdine(LocalDate.of(2024, 4, 7));
            Assertions.assertNull(c.getOrdine(LocalDate.of(2024, 4, 7)));
        });
    }

    /**
     * Test per verificare la rappresentazione testuale di un oggetto Cliente tramite il metodo toString.
     * Controlla che il formato restituito corrisponda al valore atteso.
     */
    @Test
    void toStringTest() {
        Assertions.assertDoesNotThrow(() -> {
            Cliente c = new Cliente("2001", 2003, LocalDate.of(2024, 11, 17));
            String expected = "Cliente [id=2001, nascita=2003, registrazione=2024-11-17]";
            Assertions.assertEquals(expected, c.toString());
        });
    }
}