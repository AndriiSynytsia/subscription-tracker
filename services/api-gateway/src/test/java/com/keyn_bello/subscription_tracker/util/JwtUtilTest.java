package com.keyn_bello.subscription_tracker.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "a".repeat(64));
        jwtUtil.init();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "short"})
    @NullSource
    void init_shouldThrowException_whenSecretIsInvalid(String invalidSecret) {
        JwtUtil util = new JwtUtil();
        ReflectionTestUtils.setField(util, "secret", invalidSecret);
        assertThrows(IllegalArgumentException.class, util::init);
    }


    @Test
    void isTokenValid_shouldReturnFalse_whenTokenIsNull() {
        assertFalse(jwtUtil.isTokenValid(null));
    }

    @Test
    void isTokenValid_shouldReturnFalse_whenTokenIsEmpty() {
        assertFalse(jwtUtil.isTokenValid(""));
    }

    @Test
    void isTokenValid_shouldReturnFalse_whenTokenHasWrongFormat() {
        assertFalse(jwtUtil.isTokenValid("invalid.format"));
    }

    @Test
    void isTokenValid_shouldReturnTrue_whenTokenIsValid() {
        String validToken = createValidToken("testuser");
        assertTrue(jwtUtil.isTokenValid(validToken));
    }

    @Test
    void isTokenValid_shouldReturnFalse_whenTokenIsExpired() {
        String expiredToken = createExpiredToken("testuser");
        assertFalse(jwtUtil.isTokenValid(expiredToken));
    }

    @Test
    void isTokenValid_shouldReturnFalse_whenTokenIsMalformed() {
        assertFalse(jwtUtil.isTokenValid("malformed.jwt.token"));
    }

    @Test
    void extractUsername_shouldReturnNull_whenTokenIsNull() {
        assertNull(jwtUtil.extractUsername(null));
    }

    @Test
    void extractUsername_shouldReturnNull_whenTokenIsEmpty() {
        assertNull(jwtUtil.extractUsername(""));
    }

    @Test
    void extractUsername_shouldReturnNull_whenTokenIsInvalid() {
        assertNull(jwtUtil.extractUsername("invalid-token"));
    }

    @Test
    void extractUsername_shouldReturnUsername_whenTokenIsValid() {
        String username = "testuser";
        String validToken = createValidToken(username);
        assertEquals(username, jwtUtil.extractUsername(validToken));
    }

    @Test
    void extractAllClaims_shouldReturnClaims_whenTokenIsValid() {
        String validToken = createValidToken("testuser");
        assertNotNull(jwtUtil.extractAllClaims(validToken));
        assertEquals("testuser", jwtUtil.extractAllClaims(validToken).getSubject());
    }

    private String createValidToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey())
                .compact();
    }

    private String createExpiredToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis() - 7200000))
                .expiration(new Date(System.currentTimeMillis() - 3600000))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor("a".repeat(64).getBytes(StandardCharsets.UTF_8));
    }
}
