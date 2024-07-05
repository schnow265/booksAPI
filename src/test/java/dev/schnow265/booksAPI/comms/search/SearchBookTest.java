package dev.schnow265.booksAPI.comms.search;

import dev.schnow265.booksAPI.jpa.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchBookTest {

    @Test
    @DisplayName("Repeat Requests of the same book title should return the same list of books")
    void searchLOTR() {
        SearchBook sb = new SearchBook();

        List<Book> firstResult = sb.searchBooks("The Lord of the Rings", false);
        List<Book> secondResult = sb.searchBooks("The Lord of the Rings", false);

        assertEquals(firstResult, secondResult);
    }
}