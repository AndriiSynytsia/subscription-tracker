package com.keyn_bello.subscription_tracker.controller;

import com.keyn_bello.subscription_tracker.dto.AuthResponse;
import com.keyn_bello.subscription_tracker.dto.LoginRequest;
import com.keyn_bello.subscription_tracker.dto.RegisterRequest;
import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AuthControllerIntegrationTest {

    @Container
    @SuppressWarnings("resource")
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("jwt-secret", () -> "test-secret-key-for-integration-tests-32-chars");
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterUserEndToEnd() {
        //Given
        RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123", "John", "Doe");

        //When
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity("/api/auth/register", request, AuthResponse.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(response.getBody());
        assertThat(response.getBody().token()).isNotNull();
        assertThat(response.getBody().user().email()).isEqualTo("test@example.com");

        //Database check
        Optional<User> savedUser = userRepository.findByEmail("test@example.com");
        assertThat(savedUser).isPresent();
    }

    @Test
    void shouldLoginUserEndToEnd() {
        //Given
        User user = User.builder()
                .email("login@example.com")
                .password(passwordEncoder.encode("P@ssword123"))
                .firstName("John")
                .lastName("Doe")
                .isActive(true)
                .build();
        userRepository.save(user);

        LoginRequest request = new LoginRequest("login@example.com", "P@ssword123");

        //When
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity("/api/auth/login", request, AuthResponse.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().token()).isNotNull();
        assertThat(response.getBody().user().email()).isEqualTo("login@example.com");
    }

    @Test
    void shouldReturnBadRequestForDuplicateEmail() {
        RegisterRequest request = new RegisterRequest("duplicate@example.com", "P@ssword123", "John", "Doe");
        restTemplate.postForEntity("/api/auth/register", request, AuthResponse.class);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/auth/register", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnUnauthorizedForInvalidCredentials() {
        LoginRequest request = new LoginRequest("nonexistent@example.com", "wrongpassword");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/auth/login", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void shouldReturnHealthStatus() {
        String response = restTemplate.getForObject("/api/auth/health", String.class);
        assertThat(response).contains("\"status\":\"UP\"");
    }
}
