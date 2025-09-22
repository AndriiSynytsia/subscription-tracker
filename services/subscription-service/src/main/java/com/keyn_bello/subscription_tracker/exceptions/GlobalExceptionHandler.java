package com.keyn_bello.subscription_tracker.exceptions;

import com.keyn_bello.subscription_tracker.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String MESSAGE_KEY = "message";
    public static final String FIELD_KEY = "field";

    private static ErrorResponse getErrorResponse(Exception exception, HttpServletRequest request, HttpStatus httpStatus) {
        return new ErrorResponse(
                exception.getMessage(),
                httpStatus.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );
    }

    private static String extractDataIntegrityMessage(DataIntegrityViolationException ex) {
        Throwable root = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause() : ex;
        String msg = root.getMessage();
        if (msg == null) return "Data integrity violation";
        String lower = msg.toLowerCase(Locale.ROOT);
        if (lower.contains("duplicate key") || lower.contains("unique")) {
            return "Conflict: duplicate resource violates a unique constraint";
        }
        return "Data integrity violation";
    }

    /**
     * Validation handler for MethodArgumentNotValidException
     *
     * @param exception - exception thrown when validation fails
     * @return - HTTP response with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put(MESSAGE_KEY, "Validation failed");
        body.put("errors", exception.getBindingResult().getFieldErrors().stream()
                .map(fe -> {
                    if (fe.getDefaultMessage() == null) {
                        log.warn("Field error has null default message for field: {}", fe.getField());
                    }
                    return Map.of(FIELD_KEY, fe.getField(), MESSAGE_KEY, fe.getDefaultMessage());
                }).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBind(BindException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put(MESSAGE_KEY, "Invalid request");
        body.put("errors", exception.getBindingResult().getFieldErrors().stream()
                .map(fe -> Map.of(
                        FIELD_KEY, fe.getField(),
                        MESSAGE_KEY, fe.getDefaultMessage() != null ?
                                fe.getDefaultMessage() :
                                fe.getCode()))
                .toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = getErrorResponse(exception, request, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception, HttpServletRequest request) {
        String message = extractDataIntegrityMessage(exception);
        ErrorResponse errorResponse = new ErrorResponse(
                message,
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}