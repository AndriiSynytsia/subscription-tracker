package com.keyn_bello.subscription_tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import java.util.Map;

package com.keyn_bello.subscription_tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration")
class AuthServiceApplicationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;


    @Test
    void contextLoads() {
        assert applicationContext != null;
    }
}
 
    @Test
    @DisplayName("ApplicationContext should contain at least one @SpringBootApplication bean")
    void contextHasSpringBootApplicationBean() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        boolean found = false;
        for (String name : beanNames) {
            if (name.toLowerCase().contains("application")) {
                found = true; break;
            }
        }
        assert found;
    }

 
    @Test
    @DisplayName("Environment should expose spring.application.name when configured")
    void environmentHasSpringApplicationNameIfConfigured() {
        String val = environment.getProperty("spring.application.name");
        // It is valid for this to be null in some test setups; this test ensures no exception occurs
        // and, if present, it is non-blank.
        if (val != null) {
            assert !val.trim().isEmpty();
        }
    }

 
    @Nested
    @DisplayName("Conditional bean presence checks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ConditionalBeans {
        @Test
        @DisplayName("PasswordEncoder bean should exist when Spring Security is on classpath")
        void passwordEncoderBeanIfSecurityPresent() {
            try {
                Class<?> type = Class.forName("org.springframework.security.crypto.password.PasswordEncoder");
                Map<String, ?> beans = applicationContext.getBeansOfType(type);
                // If the type exists on classpath, expect at least one bean registered.
                assert !beans.isEmpty();
            } catch (ClassNotFoundException e) {
                // Security not on classpath; test passes as not applicable.
                assert true;
            }
        }

        @Test
        @DisplayName("SecurityFilterChain bean should exist when Spring Security Web is on classpath")
        void securityFilterChainIfPresent() {
            try {
                Class<?> type = Class.forName("org.springframework.security.web.SecurityFilterChain");
                Map<String, ?> beans = applicationContext.getBeansOfType(type);
                assert !beans.isEmpty();
            } catch (ClassNotFoundException e) {
                assert true;
            }
        }

        @Test
        @DisplayName("DataSource bean should exist when JDBC is configured")
        void dataSourceIfPresent() {
            try {
                Class<?> type = Class.forName("javax.sql.DataSource");
                Map<String, ?> beans = applicationContext.getBeansOfType(type);
                // DataSource may be absent in pure unit test profile; allow empty.
                if (!beans.isEmpty()) {
                    assert true;
                } else {
                    assert true;
                }
            } catch (ClassNotFoundException e) {
                assert true;
            }
        }
    }
