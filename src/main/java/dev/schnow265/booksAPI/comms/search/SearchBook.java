package dev.schnow265.booksAPI.comms.search;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SearchBook {

    private static final String API_URL = "https://openlibrary.org/search.json?limit=99999&q=";
    static Logger logger = LoggerFactory.getLogger(SearchBook.class);

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public List<Book> searchBooks(String query, boolean forceReload) {
        if (forceReload) {
            logger.info("Purging the database of matches for '{}' to get fresh results.", query);
            List<Book> victims = bookRepository.findByTitle(query);
            try {
                bookRepository.deleteAll(victims);
                logger.info("All victims removed!");
            } catch (IllegalArgumentException e) {
                logger.error("Something happened when removing the victims!");
                logger.error(Arrays.toString(e.getStackTrace()));
            }
        }

        logger.info("Searching for '{}' in the database...", query);
        // Check the database first
        List<Book> books = bookRepository.findByTitle(query);

        if (!books.isEmpty()) {
            // Books found, process each book
            for (Book book : books) {
                logger.info("Found Book '{}' by {} in local cache.", book.getTitle(), book.getAuthorName());
            }
            return books;
        } else {
            try {
                // No books found
                logger.info("No books with title '{}' found in local cache.", query);
                logger.info("Not found in Postgres, calling the API...");

                // If not found in database, call the API
                String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
                URL url = new URL(API_URL + encodedQuery);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    logger.info("API has answered, running through the results.");
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    return parseAndReturnResults(response.toString());
                } else {
                    logger.error("HTTP GET request failed with error code: {}", responseCode);
                }
            } catch (IOException e) {
                logger.error(Arrays.toString(e.getStackTrace()));
                return null;
            }
        }
        return null;
    }

    private List<Book> parseAndReturnResults(String response) {
        logger.info("Parsing API Response...");
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        Gson gson = new Gson();
        List<Book> books = gson.fromJson(jsonResponse.getAsJsonArray("docs"), new TypeToken<List<Book>>() {
        }.getType());
        saveBooksAsync(books);
        return books;
    }

    public void saveBooksAsync(List<Book> books) {
        executor.submit(() -> {
            Logger al = LoggerFactory.getLogger("saveBooksAsync");

            al.info("Saving {} Books in the database...", books.size());

            // Save all books in a separate thread using saveAll method of BookRepository
            bookRepository.saveAll(books);

            al.info("Completed the save!");
        });
    }
}
