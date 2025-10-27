package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.entity.SubscriptionStatus;
import com.keyn_bello.subscription_tracker.exceptions.AccessDeniedException;
import com.keyn_bello.subscription_tracker.exceptions.DuplicateSubscriptionException;
import com.keyn_bello.subscription_tracker.exceptions.SubscriptionNotFoundException;
import com.keyn_bello.subscription_tracker.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository repository;

    @InjectMocks
    private SubscriptionService service;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        subscription = Subscription.builder()
                .id(1L)
                .userId(1L)
                .merchantName("Netflix")
                .price(new BigDecimal("15.99"))
                .billingCycle(BillingCycle.MONTHLY)
                .nextRenewalDate(LocalDate.now().plusDays(30))
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();
    }

    @Test
    void createSubscription_Success() {
        when(repository.save(subscription)).thenReturn(subscription);

        Subscription result = service.createSubscription(subscription);

        assertThat(result).isEqualTo(subscription);
        verify(repository).save(subscription);
    }

    @Test
    void createSubscription_NullSubscription_ThrowsException() {
        assertThatThrownBy(() -> service.createSubscription(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Subscription cannot be null");
    }

    @Test
    void createSubscription_DuplicateSubscription_ThrowsException() {
        when(repository.save(subscription)).thenThrow(new DataIntegrityViolationException("", new SQLException("unique constraint violation")));

        assertThatThrownBy(() -> service.createSubscription(subscription))
                .isInstanceOf(DuplicateSubscriptionException.class);
    }

    @Test
    void createSubscription_DuplicateSubscription_MessageBased_ThrowsException() {
        when(repository.save(subscription)).thenThrow(new DataIntegrityViolationException("", new RuntimeException("unique constraint violated")));

        assertThatThrownBy(() -> service.createSubscription(subscription))
                .isInstanceOf(DuplicateSubscriptionException.class);
    }

    @Test
    void getSubscriptionById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));

        Optional<Subscription> result = service.getSubscriptionById(1L);

        assertThat(result).isPresent().contains(subscription);
    }

    @Test
    void getSubscriptionById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Subscription> result = service.getSubscriptionById(1L);

        assertThat(result).isEmpty();
    }

    @Test
    void getSubscriptionById_NullId_ThrowsException() {
        assertThatThrownBy(() -> service.getSubscriptionById(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Subscription id cannot be null");
    }

    @Test
    void getAllSubscriptionsByUser_Success() {
        when(repository.findByUserId(1L)).thenReturn(List.of(subscription));

        List<Subscription> result = service.getAllSubscriptionsByUser(1L);

        assertThat(result).hasSize(1).contains(subscription);
    }

    @Test
    void getAllSubscriptionsByUser_NullUserId_ThrowsException() {
        assertThatThrownBy(() -> service.getAllSubscriptionsByUser(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User id cannot be null");
    }

    @Test
    void updateSubscription_Success() {
        Subscription updatedSub = Subscription.builder()
                .id(1L)
                .userId(1L)
                .merchantName("Spotify")
                .price(new BigDecimal("15.99"))
                .billingCycle(BillingCycle.MONTHLY)
                .nextRenewalDate(LocalDate.now().plusDays(30))
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));
        when(repository.save(any(Subscription.class))).thenReturn(updatedSub);

        Subscription result = service.updateSubscription(updatedSub);

        assertThat(result.getMerchantName()).isEqualTo("Spotify");
    }

    @Test
    void updateSubscription_NullSubscription_ThrowsException() {
        assertThatThrownBy(() -> service.updateSubscription(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Subscription cannot be null");
    }

    @Test
    void updateSubscription_NullId_ThrowsException() {
        Subscription updatedSub = Subscription.builder()
                .id(null)
                .userId(1L)
                .merchantName("Spotify")
                .price(new BigDecimal("15.99"))
                .billingCycle(BillingCycle.MONTHLY)
                .nextRenewalDate(LocalDate.now().plusDays(30))
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();

        assertThatThrownBy(() -> service.updateSubscription(updatedSub))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Subscription id cannot be null");
    }

    @Test
    void updateSubscription_NotFound_ThrowsException() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.updateSubscription(subscription))
                .isInstanceOf(SubscriptionNotFoundException.class);
    }

    @Test
    void updateSubscription_DuplicateMerchant_ThrowsException() {
        Subscription updatedSub = Subscription.builder()
                .id(1L)
                .userId(1L)
                .merchantName("Spotify")
                .price(new BigDecimal("15.99"))
                .billingCycle(BillingCycle.MONTHLY)
                .nextRenewalDate(LocalDate.now().plusDays(30))
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));
        when(repository.existsByUserIdAndMerchantName(1L, "Spotify")).thenReturn(true);

        assertThatThrownBy(() -> service.updateSubscription(updatedSub))
                .isInstanceOf(DuplicateSubscriptionException.class);
    }

    @Test
    void deleteSubscription_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));

        assertThatNoException().isThrownBy(() -> service.deleteSubscription(1L, 1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteSubscription_NullId_ThrowsException() {
        assertThatThrownBy(() -> service.deleteSubscription(null, 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Subscription id cannot be null");
    }

    @Test
    void deleteSubscription_NotFound_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteSubscription(1L, 1L))
                .isInstanceOf(SubscriptionNotFoundException.class);
    }

    @Test
    void deleteSubscription_WrongUser_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));

        assertThatThrownBy(() -> service.deleteSubscription(1L, 2L))
                .isInstanceOf(AccessDeniedException.class)
                .hasMessage("Access denied: You can only delete your own subscription");
    }

    @Test
    void getUpcomingRenewals_Success() {
        LocalDate cutOffDate = LocalDate.now().plusDays(7);
        when(repository.findByUserIdAndNextRenewalDateBefore(1L, cutOffDate))
                .thenReturn(List.of(subscription));

        List<Subscription> result = service.getUpcomingRenewals(7, 1L);

        assertThat(result).hasSize(1).contains(subscription);
    }

    @Test
    void getUpcomingRenewals_NegativeDays_ThrowsException() {
        assertThatThrownBy(() -> service.getUpcomingRenewals(-1, 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Days ahead cannot be negative");
    }
}
