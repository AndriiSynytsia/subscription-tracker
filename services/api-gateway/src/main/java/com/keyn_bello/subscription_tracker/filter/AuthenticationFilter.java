package com.keyn_bello.subscription_tracker.filter;

import com.keyn_bello.subscription_tracker.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.length() <= 7) {
                return onError(exchange);
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                return onError(exchange);
            }

            String username = jwtUtil.extractUsername(token);
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", username)
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Define configuration properties here if needed
    }
}
