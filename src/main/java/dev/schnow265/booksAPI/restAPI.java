package dev.schnow265.booksAPI;

import dev.schnow265.booksAPI.comms.SearchAuthors;
import dev.schnow265.booksAPI.comms.SearchBook;
import dev.schnow265.booksAPI.jpa.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class restAPI {

    static Logger logger = LoggerFactory.getLogger(restAPI.class);
    static boolean refresh = false;

    @Autowired
    private SearchBook searchBookService;

    @Autowired
    private SearchAuthors searchAuthorsService;

    @GetMapping("/search/books")
    public List<Book> searchBooks(@RequestParam String name) {
        List<Book> res = searchBookService.searchBooks(name, refresh);
        refresh = false;

        logger.info("{} items found! Returning them now!", res.size());
        return res;
    }

    @GetMapping("/search/writer")
    public List<Book> searchAuthor(@RequestParam String name) {
        List<Book> res = searchAuthorsService.searchAuthor(name, refresh);
        refresh = false;

        logger.info("{} items found", res.size());
        return res;
    }

    @GetMapping("/refreshNext")
    public String forceRefresh() {
        refresh = true;
        return "The next querry will be force-refreshed from the API.";
    }
}
