package com.keyn_bello.subscription_tracker.dto;

import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import com.keyn_bello.subscription_tracker.entity.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SubscriptionResponseDto(
        Long id,
        Long userId,
        String merchantName,
        BigDecimal price,
        String currency,
        BillingCycle billingCycle,
        LocalDate nextRenewalDate,
        int notificationInterval,
        SubscriptionStatus status,
        PaymentMethod paymentMethod,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
