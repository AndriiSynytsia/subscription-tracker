package com.keyn_bello.subscription_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiConfigTest {

    @Test
    void shouldCreateOpenApiConfiguration() {
        OpenApiConfig config = new OpenApiConfig();
        OpenAPI openAPI = config.subscriptionServiceAPI();

        assertThat(openAPI).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("Subscription Service API");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0");
    }
}
