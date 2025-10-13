package com.keyn_bello.subscription_tracker.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class GatewayHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("gateway", "API Gateway is running")
                .withDetail("status", "UP")
                .build();
    }
}
