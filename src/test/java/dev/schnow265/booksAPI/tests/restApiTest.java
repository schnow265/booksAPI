package dev.schnow265.booksAPI.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BooksApiApplicationTests.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class restApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled("Not yet implemented")
    void searchBooks() {
    }

    @Test
    @Disabled("Not yet implemented")
    void searchAuthor() {
    }
}