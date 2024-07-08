package dev.schnow265.booksAPI.comms.search;

import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchBook {

    private static final String API_URL = "https://openlibrary.org/search.json?limit=999&q=";
    static Logger logger = LoggerFactory.getLogger(SearchBook.class);

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public List<Book> searchBooks(String query, boolean forceReload) {
        OpenLibraryRequests req = new OpenLibraryRequests();

        req.forceRefresh(forceReload, query);

        logger.info("Searching for '{}' in the database...", query);
        // Check the database first
        List<Book> books;

        try {
            books = bookRepository.findByTitle(query);

            if (!books.isEmpty()) {
                // Books found, process each book
                for (Book book : books) {
                    logger.info("Found Book '{}' by {} in local cache.", book.getTitle(), book.getAuthorName());
                }
                return books;
            } else {
                try {
                    // No books found
                    logger.info("No books with title '{}' found in local cache, resorting to External API", query);

                    // If not found in database, call the API

                    req.sendRequest(query, API_URL);
                } catch (IOException e) {
                    logger.error(Arrays.toString(e.getStackTrace()));
                    return null;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
