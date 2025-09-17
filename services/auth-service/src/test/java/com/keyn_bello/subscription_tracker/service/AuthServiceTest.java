package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.dto.AuthResponse;
import com.keyn_bello.subscription_tracker.dto.RegisterRequest;
import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.exceptions.UserRegistrationException;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import com.keyn_bello.subscription_tracker.util.JwtUtil;
import io.micrometer.core.instrument.MeterRegistry;
import org.hibernate.sql.ast.tree.expression.CaseSimpleExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private MeterRegistry meterRegistry;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUserSuccessfully() {
        //Given
        RegisterRequest request = new RegisterRequest("test@example.com", "Password1234", "John", "Doe");
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

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Given
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!", "John", "Doe");
        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        // When & Then
        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(UserRegistrationException.class)
                .hasMessage("Email already registered");

        verify(userRepository, never()).save(any(User.class));
        verify(passwordEncoder, never()).encode(any());
        verify(jwtUtil, never()).generateToken(any(), any());
    }

    @Test
    void shouldThrowExceptionWhenPasswordEncodingFails() {
        //Given
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!", "John", "Doe");
        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenThrow(new RuntimeException("Encoding failed"));

        //When & Then
        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(UserRegistrationException.class)
                .hasMessage("Registration failed")
                .hasCauseInstanceOf(RuntimeException.class);
    }

    @Test
    void shouldThrowExceptionWhenUserSavingFails() {
        //Given
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!", "John", "Doe");
        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Saving failed"));

        //When & Then
         assertThatThrownBy(() -> authService.register(request))
                 .isInstanceOf(UserRegistrationException.class)
                 .hasMessage("Registration failed")
                 .hasCauseInstanceOf(RuntimeException.class);
    }

    @Test
    void shouldThrowExceptionWhenJwtGenerationFails() {
        //Given
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!", "John", "Doe");
        User savedUser = User.builder()
                .id(1L)
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();
        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtUtil.generateToken(savedUser.getEmail(), savedUser.getId())).thenThrow(new RuntimeException("JWT error"));

        //When & Then
        assertThatThrownBy(()-> authService.register(request))
                .isInstanceOf(UserRegistrationException.class)
                .hasMessage("Registration failed")
                .hasCauseInstanceOf(RuntimeException.class);
    }
}