package com.keyn_bello.subscription_tracker.mapper;

import com.keyn_bello.subscription_tracker.dto.SubscriptionResponseDto;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SubscriptionMapper {
    public SubscriptionResponseDto toDto(Subscription subscription) {
        Objects.requireNonNull(subscription, "Subscription cannot be null");

        return new SubscriptionResponseDto(
                subscription.getId(),
                subscription.getUserId(),
                subscription.getMerchantName(),
                subscription.getPrice(),
                subscription.getCurrency(),
                subscription.getBillingCycle(),
                subscription.getNextRenewalDate(),
                subscription.getNotificationInterval(),
                subscription.getSubscriptionStatus(),
                subscription.getPaymentMethod(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}
