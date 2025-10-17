package com.keyn_bello.subscription_tracker.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "jwtSecret", "mySecretKeyThatIsAtLeast256BitsLongForHS256Algorithm");
        ReflectionTestUtils.invokeMethod(jwtUtil, "initSecretKey");
    }

    @Test
    void generateToken_Success() {
        String token = jwtUtil.generateToken("test@example.com", 123L);

        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void extractUserId_Success() {
        String token = jwtUtil.generateToken("test@example.com", 123L);

        Long userId = jwtUtil.extractUserId(token);

        assertThat(userId).isEqualTo(123L);
    }

    @Test
    void extractEmail_Success() {
        String token = jwtUtil.generateToken("test@example.com", 123L);

        String email = jwtUtil.extractEmail(token);

        assertThat(email).isEqualTo("test@example.com");
    }
}
