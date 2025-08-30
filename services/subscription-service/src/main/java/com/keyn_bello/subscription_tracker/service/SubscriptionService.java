package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.exceptions.DuplicateSubscriptionException;
import com.keyn_bello.subscription_tracker.exceptions.SubscriptionNotFoundException;
import com.keyn_bello.subscription_tracker.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    /**
     * Method creates a new subscription and saves it to the database
     * @param subscription - Subscription object to be saved
     * @return - saved Subscription object
     */
    public Subscription createSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        if (subscriptionRepository.existsByUserIdAndMerchantName(subscription.getUserId(), subscription.getMerchantName())) {
            throw new DuplicateSubscriptionException("Subscription already exists for merchant: " + subscription.getMerchantName());
        }
        return subscriptionRepository.save(subscription);
    }

    /**
     * Method retrieves a subscription by its id
     * @param id - id of the subscription to be retrieved
     * @return - Subscription object with the given id
     */
    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    /**
     * Retrieves all subscriptions from the database
     * @return - List of all subscriptions
     */
    public List<Subscription> getAllSubscriptionsByUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    /**
     * Method updates an existing subscription in the database
     * @param subscription - Subscription object to be updated
     * @return - updated Subscription object
     */
    public Subscription updateSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        if (!subscriptionRepository.existsById(subscription.getId())) {
            throw new SubscriptionNotFoundException(subscription.getId());
        }

        return subscriptionRepository.save(subscription);
    }

    /**
     * Method deletes a subscription from the database
     * @param id - id of the subscription to be deleted
     */
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new SubscriptionNotFoundException("Subscription not found");
        }
        subscriptionRepository.deleteById(id);
    }

    /**
     * Retrieves all subscriptions that are due for renewal in the next x days
     * @param daysAhead - number of days ahead to check for renewals
     * @return - List of subscriptions that are due for renewal in the next x days
     */
    public List<Subscription> getUpcomingRenewals(int daysAhead) {
        LocalDate cutOffDate = LocalDate.now().plusDays(daysAhead);
        return subscriptionRepository.findByNextRenewalDateBefore(cutOffDate);
    }
}
