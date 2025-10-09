package com.keyn_bello.subscription_tracker.exceptions;

import org.springframework.lang.Nullable;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
