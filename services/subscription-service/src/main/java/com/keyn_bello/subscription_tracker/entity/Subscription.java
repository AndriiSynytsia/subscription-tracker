package com.keyn_bello.subscription_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String merchantName;
    @Positive
    private BigDecimal price;
    @FutureOrPresent
    private LocalDate nextRenewalDate;
    // notificationInterval could be replaced later with Notification Service instance
    @Positive
    private int notificationInterval;
    @NotNull
    private Long userId;
}
