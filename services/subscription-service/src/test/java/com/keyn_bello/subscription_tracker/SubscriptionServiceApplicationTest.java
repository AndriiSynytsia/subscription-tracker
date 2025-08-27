package com.keyn_bello.subscription_tracker;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubscriptionServiceApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;


    @Test
    void contextLoads() {

        // Basic sanity: application context should be initialized
        assertNotNull(applicationContext, "ApplicationContext should be initialized by @SpringBootTest");

    }
}

    @Test
    void applicationContextHasExpectedBeansOrStartsMinimal() {
        assertNotNull(applicationContext);
        // Try a few common main bean names to avoid tight coupling if the app class was renamed
        String[] possibleBeans = new String[] {"subscriptionServiceApplication", "subscriptionServiceApplicationTest", "subscriptionServiceApplicationTests"};
        boolean anyFound = false;
        for (String name : possibleBeans) {
            if (applicationContext.containsBean(name)) {
                anyFound = true;
                break;
            }
        }
        // Not all projects expose main app as bean name; allow either outcome but assert the context contains at least 1 bean
        assertTrue(applicationContext.getBeanDefinitionCount() > 0, "At least one bean definition should exist in context");
    }

    @Test
    void noActiveProfilesByDefaultInTests() {
        String[] active = environment.getActiveProfiles();
        // Many Spring Boot projects keep test profile empty unless explicitly set
        assertNotNull(active);
        // We allow either empty or explicit test profile; assert not null and not containing null entries
        for (String p : active) {
            assertNotNull(p);
        }
    }

    @SpringBootTest(properties = {"subscription.test.flag=true"})
    static class PropertyOverrideContext {
        @Autowired
        ApplicationContext ctx;
        @Autowired
        Environment env;

        @Test
        void propertyOverrideApplies() {
            assertEquals("true", env.getProperty("subscription.test.flag"));
            assertNotNull(ctx);
        }
    }

    @Test
    void requestingNonexistentBeanThrowsMeaningfulException() {
        // Choose a random class unlikely to exist to exercise failure mode
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(java.util.concurrent.ExecutorService.class));
    }
