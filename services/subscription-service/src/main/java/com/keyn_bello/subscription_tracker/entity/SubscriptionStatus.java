package com.keyn_bello.subscription_tracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SubscriptionStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    CANCELLED,
    EXPIRED
}
