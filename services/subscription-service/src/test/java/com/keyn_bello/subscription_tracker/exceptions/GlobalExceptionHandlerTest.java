package com.keyn_bello.subscription_tracker.exceptions;

import com.keyn_bello.subscription_tracker.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Mock
    private HttpServletRequest request;

    @Test
    void shouldHandleSubscriptionNotFoundException() {
        when(request.getRequestURI()).thenReturn("/api/subscriptions/1");

        SubscriptionNotFoundException exception = new SubscriptionNotFoundException("Not found");
        ResponseEntity<ErrorResponse> response = handler.handleSubscriptionNotFound(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Not found");
    }

    @Test
    void shouldHandleDuplicateSubscriptionException() {
        when(request.getRequestURI()).thenReturn("/api/subscriptions");

        DuplicateSubscriptionException exception = new DuplicateSubscriptionException("Duplicate");
        ResponseEntity<ErrorResponse> response = handler.handleDuplicateSubscription(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        when(request.getRequestURI()).thenReturn("/api/test");

        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ResponseEntity<ErrorResponse> response = handler.handleIllegalArgumentException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldHandleGenericException() {
        Exception exception = new RuntimeException("Unexpected error");
        ResponseEntity<ErrorResponse> response = handler.handleGenericException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldHandleDataIntegrityViolation() {
        when(request.getRequestURI()).thenReturn("/api/test");

        DataIntegrityViolationException exception = new DataIntegrityViolationException("Constraint violation");
        ResponseEntity<ErrorResponse> response = handler.handleDataIntegrityViolation(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

}
