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
    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        if (secret == null || secret.getBytes(StandardCharsets.UTF_8).length < 64) {
            throw new IllegalArgumentException("JWT secret must be at least 64 bytes for HS512");
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
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
        } catch (Exception e) {
            log.error("Unexpected error validating token", e);
            return false;
        }
    }
}
