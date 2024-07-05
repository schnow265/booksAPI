package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import org.slf4j.Logger;

import java.util.Optional;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final Logger logging = LoggerFactory.getLogger(AuthenticationService.class);
    private static final KeyManagement k = new KeyManagement();

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        k.verifyKey(apiKey);
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}