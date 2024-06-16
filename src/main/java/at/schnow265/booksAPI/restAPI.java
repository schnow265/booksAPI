package at.schnow265.booksAPI;

import at.schnow265.booksAPI.comms.SearchBook;
import at.schnow265.booksAPI.jpa.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAsync
public class restAPI {

    static Logger logger = LoggerFactory.getLogger(restAPI.class);

    @Autowired
    private SearchBook searchBookService;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String name) {
        try {
            List<Book> res =  searchBookService.searchBooks(name);

            logger.info("{} items found! Returning them now!", res.size());
            return res;
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }
}
