package com.keyn_bello.subscription_tracker.controller;

import com.keyn_bello.subscription_tracker.dto.SubscriptionCreateRequestDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionResponseDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionUpdateRequestDto;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.entity.SubscriptionStatus;
import com.keyn_bello.subscription_tracker.mapper.SubscriptionMapper;
import com.keyn_bello.subscription_tracker.service.SubscriptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling subscription-related requests
 */
@RestController
@RequestMapping("/api/subscriptions")
@Validated
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper = new SubscriptionMapper();

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * REST endpoint to create a new subscription
     *
     * @param subscriptionDto - Subscription Dto object containing the details of the subscription to be created
     * @return - ResponseEntity containing the created Subscription object and HTTP status code
     */
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody SubscriptionCreateRequestDto subscriptionDto) {
        Subscription subscription = Subscription.builder()
                .userId(subscriptionDto.userId())
                .merchantName(subscriptionDto.merchantName())
                .price(subscriptionDto.price())
                .currency(subscriptionDto.currency())
                .billingCycle(subscriptionDto.billingCycle())
                .nextRenewalDate(subscriptionDto.nextRenewalDate())
                .notificationInterval(subscriptionDto.notificationInterval())
                .paymentMethod(subscriptionDto.paymentMethod())
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();

        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    /**
     * REST endpoint to retrieve a subscription by its id
     *
     * @param id - id of the subscription to be retrieved
     * @return - ResponseEntity containing the retrieved Subscription object and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id, Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);

        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!subscription.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(subscription.get());
    }

    /**
     * REST endpoint to retrieve all subscriptions for a user
     *
     * @param authentication - authentication object containing the user's id
     * @return - ResponseEntity containing a list of Subscription objects and HTTP status code
     */
    @GetMapping("/me")
    public ResponseEntity<List<SubscriptionResponseDto>> getMySubscriptions(Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());
        var userSubscriptions = subscriptionService.getAllSubscriptionsByUser(userId)
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
        return ResponseEntity.ok(userSubscriptions);
    }

    /**
     * REST endpoint to update a subscription
     *
     * @param id        - id of the subscription to be updated
     * @param updateDto - updated Subscription object
     * @return - ResponseEntity containing the updated Subscription object and HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionUpdateRequestDto updateDto, Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);

        if (existingSubscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!existingSubscription.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Subscription subscription = Subscription.builder()
                .id(id)
                .userId(existingSubscription.get().getUserId())
                .merchantName(updateDto.merchantName())
                .price(updateDto.price())
                .currency(updateDto.currency())
                .billingCycle(updateDto.billingCycle())
                .paymentMethod(updateDto.paymentMethod())
                .nextRenewalDate(updateDto.nextRenewalDate())
                .notificationInterval(updateDto.notificationInterval())
                .subscriptionStatus(updateDto.subscriptionStatus())
                .build();
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
        return ResponseEntity.ok(subscriptionMapper.toDto(updatedSubscription));
    }

    /**
     * REST endpoint to delete a subscription by its id
     *
     * @param id - id of the subscription to be deleted
     * @return - ResponseEntity with HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id, Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);

        if (existingSubscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!existingSubscription.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        subscriptionService.deleteSubscription(id, userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * REST endpoint to retrieve all subscriptions that are due for renewal in the next x days
     *
     * @param daysAhead - number of days ahead to check for renewals
     * @return - ResponseEntity containing a list of Subscription objects and HTTP status code
     */
    @GetMapping("/renewals")
    public ResponseEntity<List<SubscriptionResponseDto>> getUpcomingRenewals(@RequestParam @Min(1) @Max(365) int daysAhead, Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());

        List<SubscriptionResponseDto> upcomingRenewals = subscriptionService.getUpcomingRenewals(daysAhead, userId)
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
        return ResponseEntity.ok(upcomingRenewals);
    }
}
