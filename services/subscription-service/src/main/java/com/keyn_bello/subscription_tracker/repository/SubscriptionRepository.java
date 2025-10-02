package com.keyn_bello.subscription_tracker.repository;

import com.keyn_bello.subscription_tracker.entity.Subscription;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(@NotNull Long userId);

    List<Subscription> findByUserIdAndNextRenewalDateBefore(@NotNull Long userId, @NotNull LocalDate cutOffDate);

    List<Subscription> findByUserIdAndMerchantName(@NotNull Long userId, @NotBlank String merchantName);

    boolean existsByUserIdAndMerchantName(@NotNull Long userId, @NotBlank String merchantName);

    boolean existsByIdAndUserId(@NotNull Long id, @NotNull Long userId);
}
