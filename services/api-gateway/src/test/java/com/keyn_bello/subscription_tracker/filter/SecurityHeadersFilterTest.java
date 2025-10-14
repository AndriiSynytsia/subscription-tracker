package com.keyn_bello.subscription_tracker.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityHeadersFilterTest {

    @Test
    void shouldAddSecurityHeaders() {
        SecurityHeadersFilter filter = new SecurityHeadersFilter();
        ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/test"));
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        when(chain.filter(exchange)).thenReturn(Mono.empty().then());

        filter.apply(new SecurityHeadersFilter.Config()).filter(exchange, chain).block();

        HttpHeaders headers = exchange.getResponse().getHeaders();
        assertThat(headers.getFirst("X-Content-Type-Options")).isEqualTo("nosniff");
        assertThat(headers.getFirst("X-Frame-Options")).isEqualTo("DENY");
        assertThat(headers.getFirst("X-XSS-Protection")).isEqualTo("1; mode=block");
        assertThat(headers.getFirst("Strict-Transport-Security")).isEqualTo("max-age=31536000; includeSubDomains");
        assertThat(headers.getFirst("Content-Security-Policy")).isEqualTo("default-src 'self'");
        assertThat(headers.getFirst("Referrer-Policy")).isEqualTo("strict-origin-when-cross-origin");
    }
}
