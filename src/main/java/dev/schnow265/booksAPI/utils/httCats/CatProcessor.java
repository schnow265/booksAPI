package dev.schnow265.booksAPI.utils.httCats;

import org.springframework.http.HttpStatus;

public class CatProcessor {
    public static String getCat(HttpStatus status) {
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
