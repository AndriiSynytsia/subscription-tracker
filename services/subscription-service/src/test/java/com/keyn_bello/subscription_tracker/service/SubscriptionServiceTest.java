package com.keyn_bello.subscription_tracker.service;

import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.exceptions.SubscriptionNotFoundException;
import com.keyn_bello.subscription_tracker.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository repository;

    @InjectMocks
    private SubscriptionService service;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        subscription = Subscription.builder().id(1L).userId(1L).merchantName("Netflix").price(new BigDecimal("15.99")).build();
    }

    @Test
    void createSubscription_Success() {

        when(repository.save(subscription)).thenReturn(subscription);

        Subscription result = service.createSubscription(subscription);

        assertThat(result).isEqualTo(subscription);
        verify(repository).save(subscription);
    }

    @Test
    void getSubscriptionById_Success() {
        when(repository.findById(subscription.getId())).thenReturn(Optional.of(subscription));

        var result = service.getSubscriptionById(subscription.getId());

        assertThat(result).isPresent().contains(subscription);
    }

    @Test
    void getSubscriptionById_NotFound() {
        when(repository.findById(subscription.getId())).thenReturn(Optional.empty());

        var result = service.getSubscriptionById(subscription.getId());

        assertThat(result).isEmpty();
    }

    @Test
    void deleteSubscription_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(subscription));
        assertThatNoException().isThrownBy(() -> service.deleteSubscription(1L, 1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteSubscription_NotFound() {

        assertThatThrownBy(() -> service.deleteSubscription(1L, 1L)).isInstanceOf(SubscriptionNotFoundException.class);
    }

    @Test
    void getUpcomingRenewals_Success() {
        LocalDate endDate = LocalDate.now().plusDays(7);
        List<Subscription> subscriptions = List.of(Subscription.builder().id(1L).userId(1L).build());
        when(repository.findByUserIdAndNextRenewalDateBefore(1L, endDate)).thenReturn(subscriptions);

        var result = service.getUpcomingRenewals(7, 1L);

        assertThat(result).hasSize(1);
    }
}