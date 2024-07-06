package dev.schnow265.booksAPI.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksApiApplicationTests.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("REST API Tests")
class RestTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("'/search/book' returns results")
    void searchBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/search/books?name=The%20Lord%20Of%20The%20%Rings")
                        .accept(MediaType.ALL))
                .andExpect(status().is(200));
    }

    @Test
    @Disabled("Not yet implemented")
    @DisplayName("'/search/writer' returns results")
    void searchAuthor() throws Exception {
    }
}