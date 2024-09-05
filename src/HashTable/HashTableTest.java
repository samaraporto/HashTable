package HashTable;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

    HashTable hashTable;

    @BeforeEach
    void setUp() {
        // Criando a HashTable com capacidade inicial e fator de carga adequado
        hashTable = new HashTable(10, 20, 2); // Capacidade 10, fator de carga 2
    }

    @Test
    void testInsertAndSearchSingleElement() {
        hashTable.insere("single element");
        assertTrue(hashTable.busca("single element"), "Elemento não encontrado: single element");
    }

    @Test
    void testInsertAndSearchMultipleElements() {
        String[] elements = {"abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"};
        for (String element : elements) {
            hashTable.insere(element);
        }
        for (String element : elements) {
            assertTrue(hashTable.busca(element), "Elemento não encontrado: " + element);
        }
    }

    @Test
    void testInsertCollisionHandling() {
        String[] elements = {
            "listen to the wind", "silent own the tide", 
            "dent to wise inlet", "tiles into end with", 
            "end title own it", "let it own the side", 
            "lend site within to", "this let into widen", 
            "twine end title so", "won’t heed title sin"
        };

        for (String element : elements) {
            hashTable.insere(element);
        }

        long startTime = System.nanoTime();
        for (String element : elements) {
            assertTrue(hashTable.busca(element), "Elemento não encontrado: " + element);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tempo de execução (com colisões): " + duration + " nanosegundos");
    }

    @Test
    void testInsertWithoutCollisions() {
        String[] elements = {
            "apple runs fast daily home", "bring light over dark night", 
            "create calm vibes under sky", "deliver hopes fast great time", 
            "enjoy each second with care", "focus hard every day bright", 
            "give best effort to the end", "hold steady path over calm", 
            "inspire dreams and reach high", "journey towards peace every day"
        };

        for (String element : elements) {
            hashTable.insere(element);
        }

        long startTime = System.nanoTime();
        for (String element : elements) {
            assertTrue(hashTable.busca(element), "Frase não encontrada: " + element);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tempo de execução (sem colisões): " + duration + " nanosegundos");
    }

    @Test
    void testSearchNonExistentElement() {
        hashTable.insere("abc");
        assertFalse(hashTable.busca("xyz"), "Elemento deveria não existir: xyz");
    }

    @Test
    void testRemoveElement() {
        hashTable.insere("element");
        hashTable.remove("element");
        assertFalse(hashTable.busca("element"), "Elemento ainda está presente após remoção: element");
    }

    @Test
    void testRemoveNonExistentElement() {
        hashTable.insere("exists");
        hashTable.remove("doesn't exist");
        assertFalse(hashTable.busca("doesn't exist"), "Elemento não deveria existir: doesn't exist");
    }

    @Test
    void testRehashingWithResize() {
        for (int i = 0; i < 15; i++) {
            hashTable.insere("Resize" + i);
        }

        // Garantir que elementos ainda estão acessíveis após rehash
        assertTrue(hashTable.busca("Resize0"), "Elemento não encontrado após resize: Resize0");
        assertTrue(hashTable.busca("Resize14"), "Elemento não encontrado após resize: Resize14");
    }
}
