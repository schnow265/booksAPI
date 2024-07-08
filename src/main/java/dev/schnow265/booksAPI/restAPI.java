package dev.schnow265.booksAPI;

import dev.schnow265.booksAPI.auth.AuthService;
import dev.schnow265.booksAPI.comms.search.SearchAuthors;
import dev.schnow265.booksAPI.comms.search.SearchBook;
import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.utils.httCats.CatProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class restAPI {

    static Logger logger = LoggerFactory.getLogger(restAPI.class);
    static boolean refresh = false;

    @Autowired
    private SearchBook searchBookService;

    @Autowired
    private SearchAuthors searchAuthorsService;

    @Autowired
    private AuthService authService;

    @GetMapping("/search/books")
    public List<Book> searchBooks(@RequestParam String name) {
        List<Book> res = searchBookService.searchBooks(name, refresh);
        refresh = false;
        try {
            logger.info("{} items found! Returning them now!", res.size());
        } catch (NullPointerException e) {
            logger.warn("NPE received.");
        }

        return res;
    }

    @GetMapping("/search/writer")
    public List<Book> searchAuthor(@RequestParam String name) {
        List<Book> res = searchAuthorsService.searchAuthor(name, refresh);
        refresh = false;

        logger.info("{} items found", res.size());
        return res;
    }

    @GetMapping("/search/refreshNext")
    public String forceRefresh() {
        refresh = true;
        return "The next querry will be force-refreshed from the API.";
    }

    @GetMapping("/secret")
    public String secretKey(@RequestHeader("X-API-KEY") Optional<String> key) {
        return authService.beAuthenticated(key);
    }

    @GetMapping("/cat/teapot")
    public String teapot() {
        return CatProcessor.getCat(HttpStatus.I_AM_A_TEAPOT);
    }
}
