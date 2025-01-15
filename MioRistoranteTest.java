import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import upo20050401.Cliente;
import upo20050401.MioRistorante;
import upo20050401.Ordine;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe di test per verificare il corretto funzionamento delle principali funzionalità
 * della classe MioRistorante.
 * Autori: 20050401 - 20057192
 */
public class MioRistoranteTest {
    /**
     * Funzione di utilità per verificare se un cliente ha i valori attesi per ID, anno di nascita e data di registrazione.
     *
     * @param c      il cliente da verificare
     * @param id     l'ID atteso del cliente
     * @param nascita l'anno di nascita atteso del cliente
     * @param reg    la data di registrazione attesa del cliente
     * @return true se i valori del cliente corrispondono a quelli attesi, false altrimenti
     */
    private boolean clienteEquals(Cliente c, String id, int nascita, LocalDate reg) {
        return c.getId().equals(id) && c.getNascita() == nascita && c.getRegistrazione().equals(reg);
    }

    /**
     * Test per verificare l'aggiunta di un cliente con un ID specifico.
     * Controlla che il numero totale di clienti venga incrementato correttamente.
     */
    @Test
    void aggiungiClienteConIdTest() {
        String id = "2001";
        int nascita = 2004;
        LocalDate reg = LocalDate.of(2024, 10, 5);
        int numClienti = Cliente.getNumClienti();
        Assertions.assertDoesNotThrow(() -> MioRistorante.aggiungiClienteA(id, nascita, reg));
        Assertions.assertEquals(numClienti+1, Cliente.getNumClienti());
    }

    /**
     * Test per verificare la ricerca di un cliente tramite il suo ID.
     * Controlla che il cliente trovato abbia i valori attesi.
     */
    @Test
    void cercaClienteTest() {
        String id = "2007";
        int nascita = 2003;
        LocalDate reg = LocalDate.of(2023, 11, 5);
        Assertions.assertDoesNotThrow(() -> {
            MioRistorante.aggiungiClienteA("", 2003, LocalDate.of(2024, 12, 05));
            MioRistorante.aggiungiClienteA(id, nascita, reg);
            MioRistorante.aggiungiClienteA("", 2000, LocalDate.now());
        });
        Cliente c = MioRistorante.cercaClienteA(id);
        Assertions.assertNotNull(c);
        Assertions.assertTrue(clienteEquals(c, id, nascita, reg));
    }

    /**
     * Test per verificare la ricerca di clienti in un intervallo di età.
     * Controlla che i clienti trovati siano quelli attesi e che nessun cliente al di fuori
     * del range venga incluso.
     */
    @Test
    void cercaClienteConEtaTest() {
        String[] id = { "1001", "2002", "3003" };
        int[] nascita = { 1990, 1991, 1999 };
        LocalDate[] reg = { LocalDate.of(2020, 3, 7), LocalDate.of(2021, 12, 8), LocalDate.of(2022, 10, 20) };

        Assertions.assertDoesNotThrow(() -> {
            MioRistorante.aggiungiClienteA(id[0], nascita[0], reg[0]);
            MioRistorante.aggiungiClienteA(id[1], nascita[1], reg[1]);
            MioRistorante.aggiungiClienteA(id[2], nascita[2], reg[2]);

        });

        ArrayList<Cliente> clist = MioRistorante.cercaClienteEtaA(18, 21);

        // Log risultati per debug
        for (Cliente c : clist) {
            System.out.println("Cliente trovato: " + c);
        }

        // Verifica dei risultati
        Assertions.assertFalse(clist.isEmpty(), "La lista di clienti trovati è vuota.");
        Assertions.assertEquals(1, clist.size(), "Il numero di clienti trovati non corrisponde a 1.");

    }



    /**
     * Test per verificare l'aggiunta di un ordine a un cliente.
     * Controlla che l'ordine sia correttamente associato al cliente e che i dettagli
     * dell'ordine siano corretti.
     */
    @Test
    void aggiungiOrdineTest() {
        String id = "4007";
        Ordine o = new Ordine(2, "Vegano", LocalDate.now());
        Assertions.assertDoesNotThrow(() -> MioRistorante.aggiungiClienteA(id, 2003, LocalDate.of(2024, 12, 2)));
        Cliente c = MioRistorante.cercaClienteA(id);
        Assertions.assertNotNull(c);
        MioRistorante.aggiungiOrdineA(id, o.getNumPiatti(), o.getTipoMenu());
        Assertions.assertTrue(c.contieneOrdine(LocalDate.now()));
        Assertions.assertNotNull(c.getOrdine(LocalDate.now()));
        Assertions.assertEquals(o, c.getOrdine(LocalDate.now()));
    }
}