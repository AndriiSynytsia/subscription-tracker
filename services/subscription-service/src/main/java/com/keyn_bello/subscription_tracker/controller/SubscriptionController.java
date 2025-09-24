package com.keyn_bello.subscription_tracker.controller;

import com.keyn_bello.subscription_tracker.dto.SubscriptionCreateRequestDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionResponseDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionUpdateRequestDto;
import com.keyn_bello.subscription_tracker.entity.Subscription;
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
                .billingCycle(subscriptionDto.billingCycle())
                .nextRenewalDate(subscriptionDto.nextRenewalDate())
                .notificationInterval(subscriptionDto.notificationInterval())
                .paymentMethod(subscriptionDto.paymentMethod())
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
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * REST endpoint to retrieve all subscriptions for a user
     *
     * @param authentication - authentication object containing the user's id
     * @return - ResponseEntity containing a list of Subscription objects and HTTP status code
     */
    @GetMapping("/me")
    public ResponseEntity<List<SubscriptionResponseDto>> getMySubscriptions(Authentication authentication) {
        System.out.println("Authentication: " + authentication);
        System.out.println("Authentication name: " + (authentication != null ? authentication.getName() : "null"));

        if (authentication == null) {
            throw new RuntimeException("Authentication is null");
        }
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
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionUpdateRequestDto updateDto) {
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);

        if (existingSubscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Subscription subscription = Subscription.builder()
                .id(id)
                .userId(existingSubscription.get().getUserId())
                .merchantName(updateDto.merchantName())
                .price(updateDto.price())
                .billingCycle(updateDto.billingCycle())
                .paymentMethod(updateDto.paymentMethod())
                .nextRenewalDate(updateDto.nextRenewalDate())
                .notificationInterval(updateDto.notificationInterval())
                .status(updateDto.status())
                .currency(updateDto.currency())
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
        List<SubscriptionResponseDto> upcomingRenewals = subscriptionService.getUpcomingRenewals(daysAhead)
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
        return ResponseEntity.ok(upcomingRenewals);
    }
}
