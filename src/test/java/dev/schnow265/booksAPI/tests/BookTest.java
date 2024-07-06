package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.BooksApiApplication;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BooksApiApplication.class)
class BookTest {

    @Autowired
    BookRepository bookRepo;

    @Test
    void searchInRepo() {

    }
}