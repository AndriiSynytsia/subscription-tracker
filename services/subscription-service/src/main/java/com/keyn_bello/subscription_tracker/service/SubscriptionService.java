package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.exceptions.DuplicateSubscriptionException;
import com.keyn_bello.subscription_tracker.exceptions.SubscriptionNotFoundException;
import com.keyn_bello.subscription_tracker.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    public static final String ERROR_MESSAGE_ALREADY_EXIST = "Subscription already exists for userId=%s and merchant=%s";
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    /**
     * Method creates a new subscription and saves it to the database
     *
     * @param subscription - Subscription object to be saved
     * @return - saved Subscription object
     */
    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        return saveSubscriptionWithErrorHandling(subscription, subscription.getUserId(), subscription.getMerchantName());
    }

    /**
     * Method retrieves a subscription by its id
     *
     * @param id - id of the subscription to be retrieved
     * @return - Subscription object with the given id
     */
    public Optional<Subscription> getSubscriptionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Subscription id cannot be null");
        }
        return subscriptionRepository.findById(id);
    }

    /**
     * Retrieves all subscriptions from the database
     *
     * @return - List of all subscriptions
     */
    public List<Subscription> getAllSubscriptionsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        return subscriptionRepository.findByUserId(userId);
    }

    /**
     * Method updates an existing subscription in the database
     *
     * @param subscription - Subscription object to be updated
     * @return - updated Subscription object
     */
    @Transactional
    public Subscription updateSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        if (subscription.getId() == null) {
            throw new IllegalArgumentException("Subscription id cannot be null");
        }

        if (!subscriptionRepository.existsById(subscription.getId())) {
            throw new SubscriptionNotFoundException(subscription.getId());
        }
        Subscription existing = subscriptionRepository.findById(subscription.getId()).orElseThrow(() -> new SubscriptionNotFoundException(subscription.getId()));

        Subscription updatedSubscription = Subscription.builder()
                .id(existing.getId())
                .userId(existing.getUserId())
                .merchantName(subscription.getMerchantName() != null ? subscription.getMerchantName() : existing.getMerchantName())
                .price(subscription.getPrice() != null ? subscription.getPrice() : existing.getPrice())
                .currency(subscription.getCurrency() != null ? subscription.getCurrency() : existing.getCurrency())
                .billingCycle(subscription.getBillingCycle() != null ? subscription.getBillingCycle() : existing.getBillingCycle())
                .nextRenewalDate(subscription.getNextRenewalDate() != null ? subscription.getNextRenewalDate() : existing.getNextRenewalDate())
                .notificationInterval(subscription.getNotificationInterval() != null ? subscription.getNotificationInterval() : existing.getNotificationInterval())
                .subscriptionStatus(subscription.getSubscriptionStatus() != null ? subscription.getSubscriptionStatus() : existing.getSubscriptionStatus())
                .paymentMethod(subscription.getPaymentMethod() != null ? subscription.getPaymentMethod() : existing.getPaymentMethod())
                .createdAt(existing.getCreatedAt())
                .build();

        if (!existing.getMerchantName().equalsIgnoreCase(updatedSubscription.getMerchantName())
                && subscriptionRepository.existsByUserIdAndMerchantName(existing.getUserId(), updatedSubscription.getMerchantName())) {
            throw new DuplicateSubscriptionException(
                    ERROR_MESSAGE_ALREADY_EXIST.formatted(existing.getUserId(), updatedSubscription.getMerchantName()));
        }

        return saveSubscriptionWithErrorHandling(updatedSubscription, existing.getUserId(), updatedSubscription.getMerchantName());
    }

    private Subscription saveSubscriptionWithErrorHandling(Subscription subscription, Long userId, String merchantName) {
        try {
            return subscriptionRepository.save(subscription);
        } catch (DataIntegrityViolationException e) {
            var cause = e.getCause();
            if (cause instanceof ConstraintViolationException || (cause != null && cause.getMessage() != null && cause.getMessage().toLowerCase().contains("unique"))) {
                throw new DuplicateSubscriptionException(ERROR_MESSAGE_ALREADY_EXIST.formatted(userId, merchantName));
            }
            throw e;
        }
    }

    /**
     * Method deletes a subscription from the database
     *
     * @param id     - id of the subscription to be deleted
     * @param userId - id of the user who owns the subscription
     */
    @Transactional
    public void deleteSubscription(Long id, Long userId) {
        if (id == null) {
            throw new IllegalArgumentException("Subscription id cannot be null");
        }
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription with id " + id + " not found"));

        if (!subscription.getUserId().equals(userId)) {
            throw new SubscriptionNotFoundException("Access denied: You can only delete your own subscription");
        }
        subscriptionRepository.deleteById(id);
    }

    /**
     * Retrieves all subscriptions that are due for renewal in the next x days
     *
     * @param daysAhead - number of days ahead to check for renewals
     * @return - List of subscriptions that are due for renewal in the next x days
     */
    public List<Subscription> getUpcomingRenewals(int daysAhead, Long userId) {
        if (daysAhead < 0) {
            throw new IllegalArgumentException("Days ahead cannot be negative");
        }
        LocalDate cutOffDate = LocalDate.now().plusDays(daysAhead);
        return subscriptionRepository.findByUserIdAndNextRenewalDateBefore(userId, cutOffDate);
    }
}
