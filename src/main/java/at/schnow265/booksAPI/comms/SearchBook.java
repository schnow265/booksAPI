package at.schnow265.booksAPI.comms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import at.schnow265.booksAPI.jpa.Book;
import at.schnow265.booksAPI.jpa.BookRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchBook {

    private static final String API_URL = "https://openlibrary.org/search.json?q=";
    static Logger logger = LoggerFactory.getLogger(SearchBook.class);

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public List<Book> searchBooks(String query) throws Exception {
        logger.info("Called search books, searching for '{}' in the database...", query);
        // Check the database first
        Optional<Book> existingBook = bookRepository.findByTitle(query);
        if (existingBook.isPresent()) {
            return List.of(existingBook.get());
        }

        logger.info("Calling the API...");

        // If not found in database, call the API
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
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
            throw new RuntimeException("HTTP GET request failed with error code: " + responseCode);
        }
    }

    private List<Book> parseAndReturnResults(String response) {
        logger.info("Parsing JSON...");
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        Gson gson = new Gson();
        List<Book> books = gson.fromJson(jsonResponse.getAsJsonArray("docs"), new TypeToken<List<Book>>() {}.getType());

        //saveBooksAsync(books);

        return books;
    }

    @Async
    public void saveBooksAsync(List<Book> books) {
        logger.info("Saving {} elements in the database asynchronously...", books.size());

        // Save all books in a separate thread using saveAll method of BookRepository
        bookRepository.saveAll(books);

        logger.info("Initiated asynchronous saving of results in the database!");
    }
}
