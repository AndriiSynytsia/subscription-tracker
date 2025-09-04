package com.keyn_bello.subscription_tracker.dto;

import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import com.keyn_bello.subscription_tracker.entity.SubscriptionStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionUpdateRequestDto(
        @NotBlank @Size(max = 80) String merchantName,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal price,
        @NotNull BillingCycle billingCycle,
        @NotNull @FutureOrPresent LocalDate nextRenewalDate,
        @PositiveOrZero @Max(365) Integer notificationInterval,
        @NotNull PaymentMethod paymentMethod,
        SubscriptionStatus status
) {
}
