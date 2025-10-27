package com.keyn_bello.subscription_tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyn_bello.subscription_tracker.dto.LoginRequest;
import com.keyn_bello.subscription_tracker.dto.RegisterRequest;
import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthServiceIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16").withDatabaseName("testdb").withUsername("test").withPassword("test");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registerUser_Success() throws Exception {
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!", "John", "Doe");

        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated()).andExpect(jsonPath("$.token").exists()).andExpect(jsonPath("$.user.email").value("test@example.com"));
    }

    @Test
    void loginUser_Success() throws Exception {
        String password = new BCryptPasswordEncoder().encode("P@ssword123");
        userRepository.save(User.builder().email("login@example.com").password(password).firstName("Jane").lastName("Doe").isActive(true).build());

        LoginRequest request = new LoginRequest("login@example.com", "P@ssword123");

        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andExpect(jsonPath("$.token").exists());
    }
}
