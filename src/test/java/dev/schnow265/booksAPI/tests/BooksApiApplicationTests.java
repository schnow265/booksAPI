package dev.schnow265.booksAPI.tests;

import dev.schnow265.booksAPI.BooksApiApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BooksApiApplication.class)
@AutoConfigureMockMvc
class BooksApiApplicationTests {

}
