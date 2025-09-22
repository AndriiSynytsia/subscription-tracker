package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.dto.AuthResponse;
import com.keyn_bello.subscription_tracker.dto.LoginRequest;
import com.keyn_bello.subscription_tracker.dto.RegisterRequest;
import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.exceptions.UserRegistrationException;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import com.keyn_bello.subscription_tracker.util.JwtUtil;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AuthService}.
 * Test cover user registration and login functionality including all scenario
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Tests")
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    /**
     * Tests for user login functionality.
     * Covers various scenarios including successful login, user not found, invalid credentials, and deactivated account.
     */
    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        /**
         * Test case to verify successful login.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should login user successfully when user use valid credentials")
        void shouldLoginUserSuccessfully() {
            //Given
            LoginRequest request = new LoginRequest("test@example.com", "P@ssword123!");
            User user = User.builder()
                    .id(1L)
                    .email("test@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .password("hashedPassword")
                    .isActive(true)
                    .build();

            when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
            when(passwordEncoder.matches(request.password(), user.getPassword())).thenReturn(true);
            when(jwtUtil.generateToken(user.getEmail(), user.getId())).thenReturn("jwt-token");

            //When
            AuthResponse response = authService.login(request);

            //Then
            assertThat(response.token()).isEqualTo("jwt-token");
            assertThat(response.user().email()).isEqualTo(user.getEmail());
            assertThat(response.user().firstName()).isEqualTo(user.getFirstName());
            assertThat(response.user().lastName()).isEqualTo(user.getLastName());
            assertThat(response.user().userId()).isEqualTo(user.getId());

            verify(userRepository, times(1)).findByEmail(request.email());
            verify(passwordEncoder, times(1)).matches(request.password(), user.getPassword());
            verify(jwtUtil, times(1)).generateToken(user.getEmail(), user.getId());
        }

        /**
         * Test case to verify exception handling when user not found.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when user not found")
        void shouldThrowExceptionWhenUserNotFound() {
            //Given
            LoginRequest request = new LoginRequest("test@example.com", "P@ssword123!");

            when(userRepository.findByEmail(request.email())).thenReturn(Optional.empty());

            //When & Then
            assertThatThrownBy(() -> authService.login(request))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid credentials");

            verify(passwordEncoder, never()).matches(any(), any());
            verify(jwtUtil, never()).generateToken(any(), any());
        }

        /**
         * Test case to verify exception handling when password does not match.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when password does not match")
        void shouldThrowExceptionWhenPasswordDoesNotMatch() {
            //Given
            LoginRequest request = new LoginRequest("test@example.com", "WrongPassword");
            User user = User.builder()
                    .id(1L)
                    .email("test@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .password("hashedPassword")
                    .isActive(true)
                    .build();
            when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
            when(passwordEncoder.matches(request.password(), user.getPassword())).thenReturn(false);

            //When & Then
            assertThatThrownBy(() -> authService.login(request))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid credentials");

            verify(jwtUtil, never()).generateToken(any(), any());
        }

        /**
         * Test case to verify exception handling when user is not active.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when user is not active")
        void shouldThrowExceptionWhenUserIsNotActive() {
            //Given
            LoginRequest request = new LoginRequest("test@example.com", "P@ssword123!");
            User user = User.builder()
                    .id(1L)
                    .email("test@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .password("hashedPassword")
                    .isActive(false)
                    .build();

            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(passwordEncoder.matches(request.password(), user.getPassword())).thenReturn(true);

            //When & Then
            assertThatThrownBy(() -> authService.login(request))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Account is deactivated");

            verify(jwtUtil, never()).generateToken(any(), any());
        }

        /**
         * Test case to verify exception handling when database error occurs during registration.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw UserRegistrationException when database error occurs")
        void shouldThrowExceptionWhenDatabaseErrorOccurs() {
            //Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123!", "John", "Doe");
            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
            when(userRepository.save(any(User.class))).thenThrow(new org.springframework.dao.DataIntegrityViolationException("Database constraint violation"));

            //When & Then
            assertThatThrownBy(() -> authService.register(request))
                    .isInstanceOf(UserRegistrationException.class)
                    .hasMessage("Registration failed due to database error")
                    .hasCauseInstanceOf(org.springframework.dao.DataIntegrityViolationException.class);

            verify(userRepository).existsByEmail(request.email());
            verify(passwordEncoder).encode(request.password());
            verify(userRepository).save(any(User.class));
            verify(jwtUtil, never()).generateToken(any(), any());
        }

    }

    /**
     * Tests for user registration functionality.
     * Covers various scenarios including successful registration, email already registered, and registration failure.
     */
    @Nested
    @DisplayName("User Registration")
    class RegisterTests {

        /**
         * Test case to verify successful user registration.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should register user successfully")
        void shouldRegisterUserSuccessfully() {
            //Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword1234", "John", "Doe");
            User savedUser = User.builder()
                    .id(1L)
                    .email("test@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .build();

            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
            when(userRepository.save(any(User.class))).thenReturn(savedUser);
            when(jwtUtil.generateToken(savedUser.getEmail(), savedUser.getId())).thenReturn("jwt-token");

            //when
            AuthResponse response = authService.register(request);

            //Then
            assertThat(response.token()).isEqualTo("jwt-token");
            assertThat(response.user().email()).isEqualTo("test@example.com");
            assertThat(response.user().firstName()).isEqualTo("John");
            assertThat(response.user().lastName()).isEqualTo("Doe");
            assertThat(response.user().userId()).isEqualTo(1L);

            ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
            verify(userRepository).save(userCaptor.capture());
            User capturedUser = userCaptor.getValue();
            assertThat(capturedUser.getEmail()).isEqualTo("test@example.com");
            assertThat(capturedUser.getPassword()).isEqualTo("hashedPassword");
            assertThat(capturedUser.isActive()).isTrue();
        }

        /**
         * Test case to verify exception handling when email already exists.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when email already exists")
        void shouldThrowExceptionWhenEmailAlreadyExists() {
            // Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123!", "John", "Doe");
            when(userRepository.existsByEmail(request.email())).thenReturn(true);

            // When & Then
            assertThatThrownBy(() -> authService.register(request))
                    .isInstanceOf(UserRegistrationException.class)
                    .hasMessage("Email already registered");

            verify(userRepository, never()).save(any(User.class));
            verify(passwordEncoder, never()).encode(any());
            verify(jwtUtil, never()).generateToken(any(), any());
        }

        /**
         * Test case to verify exception handling when password encoding fails.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when password encoding fails")
        void shouldThrowExceptionWhenPasswordEncodingFails() {
            //Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123!", "John", "Doe");
            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(passwordEncoder.encode(request.password())).thenThrow(new UserRegistrationException("Encoding failed", new RuntimeException()));

            //When & Then
            assertThatThrownBy(() -> authService.register(request))
                    .isInstanceOf(UserRegistrationException.class)
                    .hasMessage("Encoding failed")
                    .hasCauseInstanceOf(RuntimeException.class);
        }

        /**
         * Test case to verify exception handling when user saving fails.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when user saving fails")
        void shouldThrowExceptionWhenUserSavingFails() {
            //Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123!", "John", "Doe");
            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
            when(userRepository.save(any(User.class))).thenThrow(new UserRegistrationException("Saving failed", new RuntimeException()));

            //When & Then
            assertThatThrownBy(() -> authService.register(request))
                    .isInstanceOf(UserRegistrationException.class)
                    .hasMessage("Saving failed")
                    .hasCauseInstanceOf(RuntimeException.class);
        }

        /**
         * Test case to verify exception handling when JWT generation fails.
         * Mocks the necessary dependencies and verifies the expected behavior.
         */
        @Test
        @DisplayName("Should throw an exception when JWT generation fails")
        void shouldThrowExceptionWhenJwtGenerationFails() {
            //Given
            RegisterRequest request = new RegisterRequest("test@example.com", "P@ssword123!", "John", "Doe");
            User savedUser = User.builder()
                    .id(1L)
                    .email("test@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .build();
            when(userRepository.existsByEmail(request.email())).thenReturn(false);
            when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
            when(userRepository.save(any(User.class))).thenReturn(savedUser);
            when(jwtUtil.generateToken(savedUser.getEmail(), savedUser.getId())).thenThrow(new UserRegistrationException("JWT error", new RuntimeException()));

            //When & Then
            assertThatThrownBy(() -> authService.register(request))
                    .isInstanceOf(UserRegistrationException.class)
                    .hasMessage("JWT error")
                    .hasCauseInstanceOf(RuntimeException.class);
        }
    }
}