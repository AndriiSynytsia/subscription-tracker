package com.keyn_bello.subscription_tracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    private static final int MIN_SECRET_LENGTH = 64;
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        validateSecret();
        this.signingKey = getSigningKey();
    }

    private void validateSecret() {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty");
        }
        if (secret.getBytes(StandardCharsets.UTF_8).length < MIN_SECRET_LENGTH) {
            throw new IllegalArgumentException("JWT secret must be at least " + MIN_SECRET_LENGTH + " bytes for HS512");
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }

        try {
            return extractAllClaims(token).getSubject();
        } catch (Exception e) {
            log.warn("Failed to extract username from token: {}", e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        if (token.split("\\.").length != 3) {
            log.debug("Invalid JWT format - expected 3 parts separated by dots, got: {}", token.split("\\.").length);
            return false;
        }

        try {
            extractAllClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.debug("Token expired: {}", e.getMessage());
            return false;
        } catch (SecurityException e) {
            log.warn("Invalid token signature: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.debug("Malformed token: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.debug("Invalid token format: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error validating token: {}", e.getMessage());
            return false;
        }
    }
}
