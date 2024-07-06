package dev.schnow265.booksAPI.tests.jpa;

import dev.schnow265.booksAPI.BooksApiApplication;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BooksApiApplication.class)
class BookTest {
    private static final Logger logging = LoggerFactory.getLogger(BookTest.class);

    @Autowired
    BookRepository bookRepo;

    @Test
    @DisplayName("Verify that a search doesn't throw an error")
    void searchInRepoWithoutError() {
        boolean passing;

        try {
            bookRepo.findByTitle("");
            passing = true;
        } catch (Exception e) {
            passing = false;
            logging.error("{}\n\n{}", e.getCause().getMessage(), Arrays.toString(e.getStackTrace()));

        }

        assertTrue(passing, "Something went horribly wrong. Scroll up to see the logged error.");
    }
}