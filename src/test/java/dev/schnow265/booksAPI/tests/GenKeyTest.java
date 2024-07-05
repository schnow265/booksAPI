package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.utils.gen.GenKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenKeyTest {

    @Test
    public void testGenerateApiKeyLength() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        assertEquals(keyLength, apiKey.length(), "API Key length should be " + keyLength);
    }

    @Test
    public void testGenerateApiKeyContainsUppercase() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsUppercase = apiKey.chars().anyMatch(Character::isUpperCase);
        assertTrue(containsUppercase, "API Key should contain at least one uppercase letter");
    }

    @Test
    public void testGenerateApiKeyContainsLowercase() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsLowercase = apiKey.chars().anyMatch(Character::isLowerCase);
        assertTrue(containsLowercase, "API Key should contain at least one lowercase letter");
    }

    @Test
    public void testGenerateApiKeyContainsDigit() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsDigit = apiKey.chars().anyMatch(Character::isDigit);
        assertTrue(containsDigit, "API Key should contain at least one digit");
    }
}