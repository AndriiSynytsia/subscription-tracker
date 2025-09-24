package com.keyn_bello.subscription_tracker.dto;

import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionCreateRequestDto(
        @NotNull Long userId,
        @NotBlank @Size(max = 80) String merchantName,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal price,
        @NotBlank @Size(max = 3) String currency,
        @NotNull BillingCycle billingCycle,
        @NotNull @Future LocalDate nextRenewalDate,
        @PositiveOrZero @Max(365) Integer notificationInterval,
        @NotNull PaymentMethod paymentMethod
) {
}
