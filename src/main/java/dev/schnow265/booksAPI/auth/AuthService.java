package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.utils.httCats.CatProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public boolean isAuthenticated(String authKey) {
        logger.info("Checking if authKey '{}' is authenticated.", authKey);
        KeyManagement mgmt = new KeyManagement();

        Optional<ApiKey> keySet = mgmt.verifyKey(authKey);

        if (keySet.isPresent()) {
            logger.info("AuthKey '{}' is valid.", authKey);
            return true;
        } else {
            logger.info("AuthKey '{}' is not valid.", authKey);
            return false;
        }
    }

    public String beAuthenticated(Optional<String> key) {
        if (key.isPresent()) {
            logger.info("Attempting authentication...");
            if (isAuthenticated(key.get())) {
                logger.info("Successfully authenticated!");
                return "You have been authenticated!";
            } else {
                logger.info("Failed to authenticate! Key may be invalid!");
                return CatProcessor.getCat(HttpStatus.UNAUTHORIZED);
            }
        } else {
            logger.warn("No key provided!");
            return CatProcessor.getCat(HttpStatus.UNAUTHORIZED);
        }
    }
}
