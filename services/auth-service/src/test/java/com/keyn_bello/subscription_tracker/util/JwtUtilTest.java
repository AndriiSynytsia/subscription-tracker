package com.keyn_bello.subscription_tracker.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "jwtSecret", "0123456789ABCDEF0123456789ABCDEF");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMinutes", 30L);
        ReflectionTestUtils.invokeMethod(jwtUtil, "initSecretKey");
    }

    @Test
    void shouldGenerateValidToken() {
        String token = jwtUtil.generateToken("test@example.com", 1L);
        assertThat(token).isNotNull();
        assertThat(jwtUtil.extractEmail(token)).isEqualTo("test@example.com");
        assertThat(jwtUtil.extractUserId(token)).isEqualTo(1L);
    }

    @Test
    void shouldValidateToken() {
        String token = jwtUtil.generateToken("test@example.com", 1L);
        UserDetails userDetails = User.builder()
                .username("test@example.com")
                .password("password")
                .authorities(Collections.emptyList())
                .build();

        assertThat(jwtUtil.isTokenValid(token, userDetails)).isTrue();
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        UserDetails userDetails = User.builder()
                .username("test@example.com")
                .password("password")
                .authorities(Collections.emptyList())
                .build();

        String validToken = jwtUtil.generateToken("test@example.com", 1L);
        String invalidToken = validToken.substring(0, validToken.length() - 5) + "wrong";

        assertThat(jwtUtil.isTokenValid(invalidToken, userDetails)).isFalse();
    }

    @Test
    void shouldThrowExceptionForInvalidSecret() {
        JwtUtil invalidJwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(invalidJwtUtil, "jwtSecret", "short");

        assertThrows(IllegalStateException.class, () ->
                ReflectionTestUtils.invokeMethod(invalidJwtUtil, "initSecretKey"));
    }
}
