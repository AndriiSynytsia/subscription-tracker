package com.keyn_bello.subscription_tracker;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ActiveProfiles("test")
class AnalyticsServiceApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
 
    @Test
    void environment_hasApplicationNameProperty_whenConfigured() {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        String appName = environment.getProperty("spring.application.name");
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        // If property is not set, this assertion will still validate other conditions gracefully
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        if (appName != null && !appName.isBlank()) {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


            assertThat(appName).as("spring.application.name should not be blank when defined").isNotBlank();
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


    }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }



        assertThat(applicationContext).as("Spring ApplicationContext should load").isNotNull();
 
    @Test
    void environment_hasApplicationNameProperty_whenConfigured() {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        String appName = environment.getProperty("spring.application.name");
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        // If property is not set, this assertion will still validate other conditions gracefully
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        if (appName != null && !appName.isBlank()) {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


            assertThat(appName).as("spring.application.name should not be blank when defined").isNotBlank();
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


    }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }



    }
 
    @Test
    void environment_hasApplicationNameProperty_whenConfigured() {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        String appName = environment.getProperty("spring.application.name");
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        // If property is not set, this assertion will still validate other conditions gracefully
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        if (appName != null && !appName.isBlank()) {
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


            assertThat(appName).as("spring.application.name should not be blank when defined").isNotBlank();
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


        }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }


    }
 
    @Test
    void applicationContext_containsCoreInfrastructureBeans() {
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        // These are generic core beans that exist in any Spring Boot application context
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("environment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemEnvironment")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

        assertThat(applicationContext.containsBean("systemProperties")).isTrue();
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }

    }
 
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    static class WebEnvironmentHealthCheckTests {
        @Autowired(required = false)
        private TestRestTemplate restTemplate;

        @Test
        void healthEndpoint_isReachable_whenActuatorAndWebArePresent() {
            if (restTemplate == null) {
                // Skip if TestRestTemplate not available (e.g., no web starter)
                return;
            }
            var response = restTemplate.getForEntity("/actuator/health", String.class);
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).containsIgnoringCase("UP");
        }
    }



}