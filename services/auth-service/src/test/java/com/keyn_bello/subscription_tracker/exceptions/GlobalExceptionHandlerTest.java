package com.keyn_bello.subscription_tracker.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleInvalidCredentialsException() {
        var exception = new InvalidCredentialsException("Invalid credentials");

        var response = handler.handleInvalidCredentials(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getMessage()).isEqualTo("INVALID_CREDENTIALS");
    }

    @Test
    void shouldHandleUserRegistrationException() {
        var exception = new UserRegistrationException("Registration failed", null);

        var response = handler.handleRegistrationException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getMessage()).isEqualTo("REGISTRATION_FAILED");
    }

    @Test
    void shouldHandleValidationException() {
        var exception = mock(MethodArgumentNotValidException.class);
        var bindingResult = mock(BindingResult.class);
        var fieldError = new FieldError("object", "field", "error message");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        var response = handler.handleValidationException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains("error");
    }

    @Test
    void shouldHandleGenericException() {
        var exception = new RuntimeException("Unexpected error");

        var response = handler.handleGenericException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getMessage()).isEqualTo("INTERNAL_SERVER_ERROR");
    }
}
