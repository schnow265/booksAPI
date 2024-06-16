package at.schnow265.booksAPI;

import at.schnow265.booksAPI.comms.SearchBook;
import at.schnow265.booksAPI.jpa.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class restAPI {

    static Logger logger = LoggerFactory.getLogger(restAPI.class);

    @GetMapping("/")
    public static String root() {
        return "HELLO WORLD!";
    }

    @Autowired
    private SearchBook searchBookService;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String name) {
        try {
            logger.info("Searching for '{}' in the Database...", name);
            List<Book> res =  searchBookService.searchBooks(name);

            logger.info("Elements found! Returning them now!");
            return res;
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }

    @GetMapping("/error")
    public static String error() {
        return "An error occurred. Check the logs.";
    }
}
