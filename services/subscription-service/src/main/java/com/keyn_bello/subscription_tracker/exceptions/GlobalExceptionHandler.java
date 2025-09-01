package com.keyn_bello.subscription_tracker.exceptions;

import com.keyn_bello.subscription_tracker.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorResponse getErrorResponse(Exception exception, HttpServletRequest request, HttpStatus httpStatus) {
        return new ErrorResponse(
                exception.getMessage(),
                httpStatus.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );
    }

    /**
     * Exception handler for SubscriptionNotFoundException
     *
     * @param exception - custom exception thrown when a subscription is not found
     * @param request   - HTTP request object
     * @return - HTTP response with error details
     */
    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubscriptionNotFound(SubscriptionNotFoundException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = getErrorResponse(exception, request, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Exception handler for DuplicateSubscriptionException
     *
     * @param exception - custom exception thrown when a duplicate subscription is found
     * @param request   - HTTP request object
     * @return - HTTP response with error details
     */
    @ExceptionHandler(DuplicateSubscriptionException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateSubscription(DuplicateSubscriptionException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = getErrorResponse(exception, request, HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Exception handler for IllegalArgumentException
     *
     * @param exception - exception thrown when an illegal argument is passed
     * @param request   - HTTP request object
     * @return - HTTP response with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = getErrorResponse(exception, request, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
