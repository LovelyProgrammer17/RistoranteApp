import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import upo20050401.Ordine;

import java.time.LocalDate;

/**
 * Classe di test per verificare il corretto funzionamento della classe Ordine.
 * Autori: 20050401 - 20057192
 */
public class OrdineTest {

    /**
     * Test per verificare la creazione di un oggetto Ordine.
     * Controlla che l'oggetto sia non nullo e che i valori dei campi
     * siano correttamente inizializzati.
     */
    @Test
    public void creaOrdineTest() {
        Ordine o = new Ordine(3, "Pizza", LocalDate.of(2024, 12, 16));
        Assertions.assertNotNull(o);
        Assertions.assertEquals(3, o.getNumPiatti());
        Assertions.assertEquals("Pizza", o.getTipoMenu());
        Assertions.assertEquals(LocalDate.of(2024, 12, 16), o.getData());
    }

    /**
     * Test per verificare la corretta modifica del numero di piatti ordinati
     * tramite il metodo setNumPiatti.
     */
    @Test
    public void setNumPiattiTest() {
        Ordine o = new Ordine(3, "Pizza", LocalDate.of(2024, 12, 16));
        o.setNumPiatti(10);
        Assertions.assertEquals(10, o.getNumPiatti());
    }

    /**
     * Test per verificare la corretta modifica del tipo di menù ordinato
     * tramite il metodo setTipoMenu.
     */
    @Test
    public void setTipoMenuTest() {
        Ordine o = new Ordine(3, "Pizza", LocalDate.of(2024, 12, 5));
        o.setTipoMenu("Pizza");
        Assertions.assertEquals("Pizza", o.getTipoMenu());
    }

    /**
     * Test per verificare la rappresentazione testuale di un oggetto Ordine
     * tramite il metodo toString.
     * Controlla che il formato restituito corrisponda al valore atteso.
     */
    @Test
    public void toStringTest() {
        Ordine o = new Ordine(2, "Carne", LocalDate.of(2024, 12, 17));
        String expected = "2 Carne 2024-12-17";
        Assertions.assertEquals(expected, o.toString());
    }

    /**
     * Test per verificare il corretto funzionamento del metodo equals
     * della classe Ordine.
     * Controlla che due oggetti con gli stessi valori siano considerati uguali.
     */
    @Test
    public void equalsTest() {
        Ordine o = new Ordine(1, "Pasta", LocalDate.of(2024, 12, 17));
        Ordine p = new Ordine(1, "Pasta", LocalDate.of(2024, 12, 17));
        Assertions.assertEquals(o, p);
    }
}