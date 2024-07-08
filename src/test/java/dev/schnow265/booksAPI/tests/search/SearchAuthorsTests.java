package dev.schnow265.booksAPI.tests.search;

import dev.schnow265.booksAPI.comms.search.SearchAuthors;
import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@Transactional
@DisplayName("Searching Authors Tests")
class SearchAuthorsTests {

    @Autowired
    BookRepository bookRepository;

    @AfterEach
    public void setUp() {
        bookRepository.deleteAll();
    }

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
