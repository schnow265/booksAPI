package dev.schnow265.booksAPI.auth;

import dev.schnow265.booksAPI.jpa.ApiKey;
import dev.schnow265.booksAPI.jpa.ApiKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final Logger logging = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private ApiKeyRepository apk;

    public Authentication getAuthentication(HttpServletRequest request) {
        KeyManagement k = new KeyManagement(apk);

        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        k.verifyKey(apiKey);
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}