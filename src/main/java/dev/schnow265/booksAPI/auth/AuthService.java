package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.jpa.ApiKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService() {
        logger.info("Received request for Authentication.");
    }

    public boolean isAuthenticated(String authKey) {
        logger.info("Checking if authKey {} is authenticated.", authKey);
        KeyManagement mgmt = new KeyManagement();

        Optional<ApiKey> keySet = mgmt.verifyKey(authKey);

        if (keySet.isPresent()) {
            logger.info("AuthKey {} is valid.", authKey);
            return true;
        } else {
            logger.info("AuthKey {} is not valid.", authKey);
            return false;
        }
    }
}
