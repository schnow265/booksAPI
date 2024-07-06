package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.restAPI;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(restAPI.class)
@RunWith(SpringRunner.class)
class restApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchBooks() {
    }

    @Test
    void searchAuthor() {
    }
}