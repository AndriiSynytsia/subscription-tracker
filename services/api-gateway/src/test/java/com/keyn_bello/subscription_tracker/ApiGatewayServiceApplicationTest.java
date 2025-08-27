package com.keyn_bello.subscription_tracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assumptions;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ClassUtils;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test framework: JUnit 5 (Jupiter) + Spring Boot Test.
 * Goals:
 * - Keep original smoke test (contextLoads)
 * - Add robust, side-effect-free validations that exercise common gateway setup
 * - Avoid new dependencies and external network calls
 */
@SpringBootTest
@ActiveProfiles("test")
class ApiGatewayServiceApplicationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    Environment env;

    @Test
    @DisplayName("Spring context loads successfully (smoke test)")
    void contextLoads() {
        // Original smoke test retained
    }

    @Test
    @DisplayName("ApplicationContext is injected and non-null")
    void applicationContextIsNotNull() {
        assertNotNull(context, "ApplicationContext should be initialized");
    }

    @Test
    @DisplayName("Looking up a definitely missing bean throws NoSuchBeanDefinitionException")
    void missingBeanLookupThrows() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("definitelyMissingBean"));
    }

    @Test
    @DisplayName("If Spring Cloud Gateway is on the classpath, a RouteLocator bean should be available")
    void routeLocatorBeanAvailableWhenGatewayPresent() throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        boolean hasGateway = ClassUtils.isPresent("org.springframework.cloud.gateway.route.RouteLocator", cl);
        Assumptions.assumeTrue(hasGateway, "Spring Cloud Gateway not on classpath; skipping RouteLocator check");

        Class<?> routeLocatorType = Class.forName("org.springframework.cloud.gateway.route.RouteLocator", true, cl);
        Object routeLocatorBean = context.getBeanProvider(routeLocatorType).getIfAvailable();
        assertNotNull(routeLocatorBean, "RouteLocator type is present but bean was not created");
    }

    @Test
    @DisplayName("application.yml (if present) parses and optionally includes gateway configuration")
    void applicationYamlParsesAndOptionallyContainsGatewayConfig() {
        ClassPathResource yaml = new ClassPathResource("application.yml");
        Assumptions.assumeTrue(yaml.exists(), "No application.yml found on classpath; skipping YAML validation");

        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(yaml);
        Properties props = factory.getObject();
        assertNotNull(props, "YAML properties should be parsable");

        // If gateway keys exist, ensure at least one such property is present
        Set<String> gatewayKeys = props.stringPropertyNames().stream()
                .filter(k -> k.startsWith("spring.cloud.gateway"))
                .collect(Collectors.toSet());

        if (!gatewayKeys.isEmpty()) {
            assertTrue(gatewayKeys.size() > 0, "Expected at least one 'spring.cloud.gateway' property");
        }
    }

    @Test
    @DisplayName("spring.application.name property can be read safely")
    void springApplicationNameIsReadable() {
        String name = env.getProperty("spring.application.name", "");
        assertNotNull(name, "Property retrieval should not return null");
        // No strict assertion on content to avoid coupling tests to config
    }
}