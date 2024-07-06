package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.comms.search.SearchBook;
import dev.schnow265.booksAPI.jpa.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
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