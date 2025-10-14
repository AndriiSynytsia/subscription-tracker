package com.keyn_bello.subscription_tracker.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public class SecurityHeadersFilter extends AbstractGatewayFilterFactory<SecurityHeadersFilter.Config> {

    public SecurityHeadersFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->
             chain.filter(exchange).then(Mono.fromRunnable(() -> {
                        HttpHeaders headers = exchange.getResponse().getHeaders();
                        headers.add("X-Content-Type-Options", "nosniff");
                        headers.add("X-Frame-Options", "DENY");
                        headers.add("X-XSS-Protection", "1; mode=block");
                        headers.add("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
                        headers.add("Content-Security-Policy", "default-src 'self'");
                        headers.add("Referrer-Policy", "strict-origin-when-cross-origin");
                    }));
    }

    public static class Config {
    }
}
