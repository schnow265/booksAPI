package dev.schnow265.booksAPI.comms.search;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dev.schnow265.booksAPI.jpa.Book;
import dev.schnow265.booksAPI.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SearchAuthors {
    private static final String API_URL = "https://openlibrary.org/search/authors.json?limit=99999&q=";
    static Logger logger = LoggerFactory.getLogger(SearchAuthors.class);

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final BookRepository bookRepository;

    public SearchAuthors(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> searchAuthor(String name, boolean refresh) {
        logger.info("Looking for Author '{}' in the Database", name);

        try {
            String encodedQuery = URLEncoder.encode(name, StandardCharsets.UTF_8);
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
        return null;
    }

    private List<Book> parseAndReturnResults(String response) {
        logger.info("Parsing API Response...");
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse.getAsJsonArray("docs"), new TypeToken<List<Book>>() {
        }.getType());
    }
}
