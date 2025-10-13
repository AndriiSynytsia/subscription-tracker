package com.keyn_bello.subscription_tracker.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "a".repeat(64));
        jwtUtil.init();
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
    void extractUsername_shouldReturnNull_whenTokenIsInvalid() {
        assertNull(jwtUtil.extractUsername("invalid-token"));
    }
}
