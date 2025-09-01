package com.keyn_bello.subscription_tracker.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionCreateRequestDto(
        @NotNull @NotBlank String merchantName,
        @NotNull @Positive BigDecimal price,
        @NotNull @Future @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nextRenewalDate,
        @PositiveOrZero Integer notificationInterval,
        @NotNull Long userId
) {}
