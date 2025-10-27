package com.keyn_bello.subscription_tracker.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionTest {

    @Test
    void shouldCreateInvalidCredentialsException() {
        var exception = new InvalidCredentialsException("Invalid credentials");
        assertThat(exception.getMessage()).isEqualTo("Invalid credentials");
    }

    @Test
    void shouldCreateUserRegistrationException() {
        var cause = new RuntimeException("Cause");
        var exception = new UserRegistrationException("Registration failed", cause);

        assertThat(exception.getMessage()).isEqualTo("Registration failed");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
