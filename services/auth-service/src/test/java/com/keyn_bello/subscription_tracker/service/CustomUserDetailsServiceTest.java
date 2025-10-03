package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.entity.User;
import com.keyn_bello.subscription_tracker.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomUserDetailsService Tests")
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @DisplayName("Should load user successfully when user exists")
    void shouldLoadUserSuccessfully() {
        //Given
        String email = "test@example.com";
        User user = User.builder()
                .id(1L)
                .email(email)
                .password("hashedPassword")
                .firstName("John")
                .lastName("Doe")
                .isActive(true)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        //When
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        //Then
        assertNotNull(userDetails);
        assertThat(userDetails.getUsername()).isEqualTo(email);
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
        assertEquals(email, userDetails.getUsername());
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> customUserDetailsService.loadUserByUsername(email))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found");

        verify(userRepository).findByEmail(email);
    }
}