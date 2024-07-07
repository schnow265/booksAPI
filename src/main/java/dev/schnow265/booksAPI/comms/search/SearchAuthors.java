package dev.schnow265.booksAPI.comms.search;

import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchAuthors {
    private static final String API_URL = "https://openlibrary.org/search/authors.json?limit=999&q=";
    static Logger logger = LoggerFactory.getLogger(SearchAuthors.class);

    private final BookRepository bookRepository;

    public SearchAuthors(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> searchAuthor(String name, boolean refresh) {
        OpenLibraryRequests req = new OpenLibraryRequests(bookRepository);

        logger.info("Looking for Author '{}' in the Database", name);

        try {


            return req.sendRequest(name, API_URL);

        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
