package com.keyn_bello.subscription_tracker.exceptions;

public class SubscriptionNotFoundException extends RuntimeException{

    public SubscriptionNotFoundException(String message) {
        super(message);
    }

    public SubscriptionNotFoundException(Long id) {
        super("Subscription not found with id: " + id);
    }
}