package dev.schnow265.booksAPI.utils.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class GenKey {
    // Define characters allowed in the API key
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 32; // Define the length of the API key
    private static final Logger logging = LoggerFactory.getLogger(GenKey.class);

    public static String generateApiKey(int length) {
        logging.info("Generating API key...");
        SecureRandom random = new SecureRandom();
        StringBuilder apiKey = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            apiKey.append(CHARACTERS.charAt(index));
        }

        logging.info("Generated API key: {}", apiKey);
        return apiKey.toString();
    }
}
