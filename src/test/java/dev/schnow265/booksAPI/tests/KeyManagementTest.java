package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.auth.KeyManagement;
import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.jpa.ApiKeyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
class KeyManagementTest {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private KeyManagement keyManagement;

    @Test
    @DisplayName("Create a key")
    void createKey() {
        keyManagement.createKey();
    }

    @Test
    @DisplayName("Create and verify a key")
    void verifyKey() {
        String key = keyManagement.createKey();
        Optional<ApiKey> apiKey = keyManagement.verifyKey(key);
        assertNotNull(apiKey.orElse(null));
    }

    @Test
    @DisplayName("Check invalid Key")
    void invalidKey() {
        Optional<ApiKey> apiKey = keyManagement.verifyKey("invalid");
        assertNull(apiKey.orElse(null));
    }
}
