package com.keyn_bello.subscription_tracker.repository;

import com.keyn_bello.subscription_tracker.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
