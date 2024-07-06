package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.comms.search.SearchAuthors;
import dev.schnow265.booksAPI.jpa.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
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
