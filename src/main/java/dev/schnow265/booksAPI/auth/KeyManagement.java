package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.jpa.ApiKeyRepository;
import dev.schnow265.booksAPI.utils.gen.GenKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyManagement {

    private static final Logger logging = LoggerFactory.getLogger(KeyManagement.class);

    @Autowired
    private ApiKeyRepository repo;

    public Optional<ApiKey> verifyKey(String key) {
        Optional<ApiKey> keyList = repo.findByKey(key);

        if (keyList.isEmpty()) {
            logging.error("Invalid API Key \"{}\" provided!", key);
            throw new BadCredentialsException("Invalid API Key!");
        } else {
            return keyList;
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
}
