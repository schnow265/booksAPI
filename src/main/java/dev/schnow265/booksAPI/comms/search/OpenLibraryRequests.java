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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenLibraryRequests {
    static Logger logger = LoggerFactory.getLogger(OpenLibraryRequests.class);

    @Autowired
    private  BookRepository bookRepository;

    private List<Book> parseAndReturnResults(String response) {
        logger.info("Parsing API Response...");

        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        Gson gson = new Gson();
        List<Book> books = gson.fromJson(jsonResponse.getAsJsonArray("docs"), new TypeToken<List<Book>>() {
        }.getType());

        try {
            logger.info("Attempting to save {} Books in the database...", books.size());
            for (Book book : books) {
                //noinspection UseBulkOperation
                bookRepository.save(book); // This is being done to not run into a "this crap is way too big" exception
            }
            logger.info("Completed the save!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return books;
    }

    public List<Book> sendRequest(String query, String API_URL) throws IOException {
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
        return null;
    }

    public void forceRefresh(boolean forceReload, String query) {
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
    }
}
