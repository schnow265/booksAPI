package dev.schnow265.booksAPI.utils.httCats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class CatProcessor {
    static Logger logger = LoggerFactory.getLogger(CatProcessor.class);
    public static String getCat(HttpStatus status) {
        logger.info(status.getReasonPhrase());
        switch (status) {
            case UNAUTHORIZED -> {
                return """
                        <img src="https://http.cat/401">
                        """;
            }
            case FORBIDDEN -> {
                return """
                        <img src="https://http.cat/403">
                        """;
            }
            case NOT_FOUND -> {
                return """
                        <img src="https://http.cat/404">
                        """;
            }
            case INTERNAL_SERVER_ERROR -> {
                return """
                        <img src="https://http.cat/500">
                        """;
            }
            case I_AM_A_TEAPOT -> {
                return """
                        <img src="https://http.cat/418">
                        """;
            }
            default -> {
                return status.getReasonPhrase();
            }
        }
    }
}
