import org.example.Book;
import org.example.Library;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addBook_success() {
        Library lib = new Library();
        Book book = new Book("Above the sky indigo", "Mike Robertson");

        lib.addBook(book);

        assertEquals(1, lib.getBookCount());
        assertTrue(lib.getBooks().contains(book));
    }

    @Test
    void addBook_null_throws() {
        Library lib = new Library();
        assertThrows(IllegalArgumentException.class, () -> lib.addBook(null));
    }

    @Test
    void removeBook_success() {
        Library lib = new Library();
        Book book = new Book("Dune", "Frank Herbert");
        lib.addBook(book);

        boolean removed = lib.removeBook(book);

        assertTrue(removed);
        assertEquals(0, lib.getBookCount());
    }

    @Test
    void removeBook_notPresent_returnsFalse() {
        Library lib = new Library();
        Book book = new Book("La Fortune des Rougon", "Emil Zola");

        boolean removed = lib.removeBook(book);

        assertFalse(removed);
    }

    @Test
    void removeBook_null_throws() {
        Library lib = new Library();
        assertThrows(IllegalArgumentException.class, () -> lib.removeBook(null));
    }

    @Test
    void getBooks_unmodifiable() {
        Library lib = new Library();
        Book book = new Book("Gobseck", "Honore De Balzac");
        lib.addBook(book);

        List<Book> books = lib.getBooks();
        assertThrows(UnsupportedOperationException.class, () -> books.add(new Book("Test1", "Test2")),
                "List should be unmodifiable");
    }

    @Test
    void getBookCount_emptyLibrary_isZero() {
        Library lib = new Library();
        assertEquals(0, lib.getBookCount());
    }

    @Test
    void getBooks_emptyLibrary_returnsEmptyList() {
        Library lib = new Library();
        assertTrue(lib.getBooks().isEmpty(),
                "Empty library should return an empty list");
    }
}