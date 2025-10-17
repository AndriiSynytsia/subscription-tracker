package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.dto.AuthResponse;
import com.keyn_bello.subscription_tracker.dto.LoginRequest;
import com.keyn_bello.subscription_tracker.dto.RegisterRequest;
import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.exceptions.InvalidCredentialsException;
import com.keyn_bello.subscription_tracker.exceptions.UserRegistrationException;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import com.keyn_bello.subscription_tracker.util.JwtUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Timed(description = "Time taken to process authentication requests", value = "auth_service_processing_time", extraTags = {"version", "1.0"})
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Timed(description = "Time taken to register a new user", value = "auth_service_register_time", extraTags = {"version", "1.0"})
    public AuthResponse register(RegisterRequest request) {
        log.info("Attempting to register user with email: {}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            throw new UserRegistrationException("Email already registered", null);
        }

        try {
            User user = User.builder()
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .isActive(true)
                    .build();

            User savedUser = userRepository.save(user);
            String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getId());

            log.info("User registered successfully: {}", savedUser.getEmail());
            return AuthResponse.from(token, savedUser);
        } catch (DataAccessException e) {
            log.error("Database error during registration for email: {}", request.email(), e);
            throw new UserRegistrationException("Registration failed due to database error", e);
        }
    }

    @Timed(description = "Time taken to login a user", value = "auth_service_login_time", extraTags = {"version", "1.0"})
    public AuthResponse login(LoginRequest request) {
        final String email = request.email().trim().toLowerCase();
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(email, user.getId());
        return AuthResponse.from(token, user);
    }
}