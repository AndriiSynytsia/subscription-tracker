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
import reactor.test.StepVerifier;

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

        when(chain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(filter.apply(new SecurityHeadersFilter.Config()).filter(exchange, chain))
                .verifyComplete();

        HttpHeaders headers = exchange.getResponse().getHeaders();
        assertThat(headers.getFirst("X-Content-Type-Options")).isEqualTo("nosniff");
    }

}
