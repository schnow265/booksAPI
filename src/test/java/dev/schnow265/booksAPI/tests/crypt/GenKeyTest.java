package dev.schnow265.booksAPI.tests.crypt;

import dev.schnow265.booksAPI.BooksApiApplication;
import dev.schnow265.booksAPI.utils.gen.GenKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BooksApiApplication.class)
@AutoConfigureMockMvc
@DisplayName("Key Generation Algorithm Tests")
class GenKeyTest {

    @Test
    @DisplayName("Verify Length")
    public void testGenerateApiKeyLength() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        assertEquals(keyLength, apiKey.length(), "API Key length should be " + keyLength);
    }

    @Test
    @DisplayName("Key contains Uppercase Letters")
    public void testGenerateApiKeyContainsUppercase() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsUppercase = apiKey.chars().anyMatch(Character::isUpperCase);
        assertTrue(containsUppercase, "API Key should contain at least one uppercase letter");
    }

    @Test
    @DisplayName("Key contains Lowercase Letters")
    public void testGenerateApiKeyContainsLowercase() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsLowercase = apiKey.chars().anyMatch(Character::isLowerCase);
        assertTrue(containsLowercase, "API Key should contain at least one lowercase letter");
    }

    @Test
    @DisplayName("Key contains digits")
    public void testGenerateApiKeyContainsDigit() {
        int keyLength = 32;
        String apiKey = GenKey.generateApiKey(keyLength);
        boolean containsDigit = apiKey.chars().anyMatch(Character::isDigit);
        assertTrue(containsDigit, "API Key should contain at least one digit");
    }
}