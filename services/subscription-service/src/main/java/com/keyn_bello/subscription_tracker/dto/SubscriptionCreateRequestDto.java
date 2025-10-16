package com.keyn_bello.subscription_tracker.dto;

import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionCreateRequestDto(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Merchant name is required")
        @Size(max = 100, message = "Merchant name must not exceed 100 characters")
        String merchantName,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Invalid price format")
        BigDecimal price,

        @NotBlank(message = "Currency is required")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter code")
        @Size(max = 3)
        String currency,

        @NotNull(message = "Billing cycle is required")
        BillingCycle billingCycle,

        @NotNull(message = "Next renewal date is required")
        @Future(message = "Next renewal date must be in the future")
        LocalDate nextRenewalDate,

        @NotNull(message = "Notification interval is required")
        @PositiveOrZero
        @Max(365)
        Integer notificationInterval,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod
) {
}
