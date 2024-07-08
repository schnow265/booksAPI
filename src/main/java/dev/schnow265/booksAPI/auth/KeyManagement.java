package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.jpa.ApiKeyRepository;
import dev.schnow265.booksAPI.utils.gen.GenKey;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyManagement {

    private static final Logger logging = LoggerFactory.getLogger(KeyManagement.class);

    @Autowired
    private ApiKeyRepository repo;

    @PostConstruct
    public void init() {
        logging.info("KeyManagement initialized with repo: {}", repo);
    }

    public Optional<ApiKey> verifyKey(String key) {
        try {
            Optional<ApiKey> keyList = repo.findByKey(key);

            if (keyList.isEmpty()) {
                logging.error("Invalid API Key \"{}\" provided!", key);
                return Optional.empty();
            } else {
                return keyList;
            }
        } catch (Exception e) {
            logging.info("An error occurred! See below for Details!");
            logging.error(e.getMessage());

            throw new RuntimeException(e.getMessage());
        }
    }

    public String createKey() {
        try {
            String apiKey = GenKey.generateApiKey(32);
            logging.info("Adding key to Database");
            ApiKey key = new ApiKey();
            key.setKey(apiKey);
            repo.save(key);
            logging.info("Key created!");
            return apiKey;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean useOTKey(String oneTimeKey) {
        Optional<ApiKey> verifiedKey = verifyKey(oneTimeKey);

        if (verifiedKey.isPresent()) {
            repo.delete(verifiedKey.get());
            return true;
        } else {
            return false;
        }
    }
}
