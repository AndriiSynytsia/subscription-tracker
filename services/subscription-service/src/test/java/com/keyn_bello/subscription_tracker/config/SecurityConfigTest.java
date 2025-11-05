package com.keyn_bello.subscription_tracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.assertj.core.api.Assertions.assertThat;

class SecurityConfigTest {

    @Test
    void shouldCreateCorsConfigurationSource() {
        SecurityConfig config = new SecurityConfig(null);
        CorsConfigurationSource source = config.corsConfigurationSource();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/subscriptions");

        assertThat(source).isNotNull();
        assertThat(source.getCorsConfiguration(request)).isNotNull();
        assertThat(source.getCorsConfiguration(request).getAllowedOrigins())
                .contains("http://localhost:5173");
    }
}
