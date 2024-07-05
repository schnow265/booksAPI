package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.comms.search.SearchAuthors;
import dev.schnow265.booksAPI.jpa.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchAuthorsTests {
    @Test
    @DisplayName("Repeat requests into the DB return the same result.")
    void searchStephenKing() {
        String search = "Stephen King";
        SearchAuthors sa = new SearchAuthors();

        List<Book> firstResult = sa.searchAuthor(search, false);
        List<Book> secondResult = sa.searchAuthor(search, false);

        assertEquals(firstResult, secondResult);
    }
}
