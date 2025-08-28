package com.keyn_bello.subscription_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String merchantName;
    @Positive
    private double price;
    @FutureOrPresent
    private LocalDate nextRenewalDate;
    // notificationInterval could be replaced later with Notification Service instance
    @NotNull
    private int notificationInterval;
    @NotNull
    private Long userId;
}
