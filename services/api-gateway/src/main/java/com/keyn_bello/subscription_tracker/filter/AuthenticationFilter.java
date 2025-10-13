package com.keyn_bello.subscription_tracker.filter;

import com.keyn_bello.subscription_tracker.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null) {
                log.debug("No Authorization header found");
                return onError(exchange);
            }

            if (!authHeader.startsWith("Bearer ")) {
                log.debug("Authorization header doesn't start with 'Bearer ': {}", authHeader);
                return onError(exchange);
            }

            String token = authHeader.substring(7).trim();
            log.debug("Extracted token: {}", token.substring(0, Math.min(token.length(), 20)) + "...");

            if (token.isEmpty() || !token.contains(".")) {
                log.warn("Invalid token format - missing periods: {}", token);
                return onError(exchange);
            }

            if (!jwtUtil.isTokenValid(token)) {
                return onError(exchange);
            }

            String username = jwtUtil.extractUsername(token);
            if (username == null) {
                return onError(exchange);
            }

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", username)
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }


    private Mono<Void> onError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
    }
}
