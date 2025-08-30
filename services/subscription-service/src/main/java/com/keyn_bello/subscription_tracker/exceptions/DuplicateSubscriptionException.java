package com.keyn_bello.subscription_tracker.exceptions;

public class DuplicateSubscriptionException extends RuntimeException {
    public DuplicateSubscriptionException(String message) {
        super(message);
    }
}
