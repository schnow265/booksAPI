package dev.schnow265.booksAPI.tests.search;

import dev.schnow265.booksAPI.comms.search.SearchBook;
import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class SearchBookTest {
    private static final Logger logging = LoggerFactory.getLogger(SearchBookTest.class);

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("Repeat Requests of the same book title should return the same list of books")
    void searchLOTR() {
        String search = "The Lord of the Rings";
        SearchBook sb = new SearchBook(bookRepository);

        List<Book> firstResult = sb.searchBooks(search, false);
        List<Book> secondResult = sb.searchBooks(search, false);

        assertEquals(firstResult, secondResult);
    }

    @Test
    @DisplayName("Force-Reload returns the same result")
    void forceReload() {
        String search = "The Lord of the Rings";
        SearchBook sb = new SearchBook(bookRepository);

        List<Book> firstResult = sb.searchBooks(search, true);
        List<Book> secondResult = sb.searchBooks(search, true);

        assertEquals(firstResult, secondResult);
    }

    @AfterEach
    void purgeBooks() {
        logging.info("Deleting all Entries!");
        bookRepository.deleteAll();
        logging.info("All Entries removed!");
    }
}