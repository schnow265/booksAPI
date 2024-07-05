package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.auth.KeyManagement;

import dev.schnow265.booksAPI.jpa.ApiKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class KeyManagementTest {

    private static final KeyManagement k = new KeyManagement();

    @Test
    @DisplayName("Create a key")
    void createKey() {
        k.createKey();
    }

    @Test
    @DisplayName("Ceate and verify a key")
    void verifyKey() {
        String key = k.createKey();

        Optional<ApiKey> apk = k.verifyKey(key);

        assertNotNull(apk);
    }
}