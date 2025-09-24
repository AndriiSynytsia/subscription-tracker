package com.keyn_bello.subscription_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyn_bello.subscription_tracker.dto.SubscriptionCreateRequestDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionResponseDto;
import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.entity.SubscriptionStatus;
import com.keyn_bello.subscription_tracker.exceptions.DuplicateSubscriptionException;
import com.keyn_bello.subscription_tracker.exceptions.GlobalExceptionHandler;
import com.keyn_bello.subscription_tracker.exceptions.SubscriptionNotFoundException;
import com.keyn_bello.subscription_tracker.mapper.SubscriptionMapper;
import com.keyn_bello.subscription_tracker.service.SubscriptionService;
import com.keyn_bello.subscription_tracker.util.JwtAuthenticationFilter;
import com.keyn_bello.subscription_tracker.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubscriptionController.class)
@Import(GlobalExceptionHandler.class)
@WithMockUser(username = "123")
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SubscriptionService subscriptionService;

    @MockitoBean
    private SubscriptionMapper subscriptionMapper;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Nested
    @DisplayName("PUT /api/subscriptions/{id}")
    @WithMockUser(username = "123")
    class Update {
        /**
         * Test method to verify that a subscription is updated successfully
         */
        @Test
        @DisplayName("return 200 when update successful")
        void shouldUpdateSubscription_return200() throws Exception {
            //given
            Subscription existing = Subscription.builder()
                    .id(7L)
                    .userId(1L)
                    .merchantName("Old Name")
                    .price(new BigDecimal("9.99"))
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .notificationInterval(7)
                    .nextRenewalDate(LocalDate.now().plusDays(30))
                    .build();

            Subscription updated = Subscription.builder()
                    .id(7L)
                    .userId(1L)
                    .merchantName("Youtube")
                    .price(new BigDecimal("11.99"))
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .notificationInterval(7)
                    .nextRenewalDate(LocalDate.now().plusDays(30))
                    .build();

            //when
            when(subscriptionService.getSubscriptionById(7L)).thenReturn(Optional.of(existing));
            when(subscriptionService.updateSubscription(any())).thenReturn(updated);

            //then
            mockMvc.perform(put("/api/subscriptions/{id}", 7)
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(updated)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(7L))
                    .andExpect(jsonPath("$.merchantName").value("Youtube"))
                    .andExpect(jsonPath("$.price").value(11.99));

            var captor = ArgumentCaptor.forClass(Subscription.class);
            verify(subscriptionService).updateSubscription(captor.capture());
            assertEquals(7L, captor.getValue().getId());
        }

        /**
         * Test method to verify that a 404 Not Found is returned when the subscription to update is not found
         */
        @Test
        @DisplayName("return 404 when subscription not found")
        void shouldReturn404_whenUpdateSubscriptionNotFound() throws Exception {
            //given
            Subscription subscription = Subscription.builder()
                    .id(1L)
                    .userId(1L)
                    .merchantName("Test")
                    .price(new BigDecimal("10.0"))
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .notificationInterval(7)
                    .nextRenewalDate(LocalDate.now().plusDays(30))
                    .build();

            //when
            when(subscriptionService.updateSubscription(any())).thenThrow(new SubscriptionNotFoundException("Subscription not found"));

            //then
            mockMvc.perform(put("/api/subscriptions/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(subscription)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/subscriptions/{id}")
    class Delete {
        /**
         * Test method to verify that a subscription is deleted successfully
         */
        @Test
        @DisplayName("return 204 when deletions successful")
        void shouldDeleteSubscription_return204() throws Exception {
            //given
            Subscription subscription = Subscription.builder()
                    .id(1L)
                    .userId(1L)
                    .merchantName("Test")
                    .price(new BigDecimal("10.0"))
                    .build();

            //when
            when(subscriptionService.getSubscriptionById(1L)).thenReturn(Optional.of(subscription));

            //then
            mockMvc.perform(delete("/api/subscriptions/1")
                            .with(csrf())
                            .with(user("1"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            verify(subscriptionService).deleteSubscription(1L, subscription.getUserId());
        }

        /**
         * Test method to verify that a 404 Not Found is returned when the subscription to delete is not found
         */
        @Test
        @DisplayName("return 404 when subscription not found")
        void shouldReturn404_whenDeleteSubscriptionNotFound() throws Exception {
            doThrow(new SubscriptionNotFoundException("Subscription not found"))
                    .when(subscriptionService).deleteSubscription(1L, 1L);

            mockMvc.perform(delete("/api/subscriptions/1")
                            .with(csrf())
                            .with(user("1"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            verify(subscriptionService).deleteSubscription(1L, 1L);
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/renewals")
    class GetRenewals {

        @Test
        @DisplayName("Returns 400 when daysAhead is 0")
        void renewals_withZeroDaysAhead_return400() throws Exception {
            mockMvc.perform(get("/api/subscriptions/renewals").param("daysAhead", "0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            verifyNoInteractions(subscriptionService);
        }

        /**
         * Test method to verify that upcoming renewals are retrieved successfully
         */
        @Test
        @DisplayName("return 200 and DTO in response")
        void shouldReturnUpcomingRenewals_return200() throws Exception {
            //given
            Subscription subscription = Subscription.builder()
                    .id(1L)
                    .userId(1L)
                    .merchantName("Test")
                    .price(new BigDecimal("10.0"))
                    .build();

            //when
            when(subscriptionService.getUpcomingRenewals(7)).thenReturn(java.util.List.of(subscription));

            //then
            mockMvc.perform(get("/api/subscriptions/renewals?daysAhead=7")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1L))
                    .andExpect(jsonPath("$[0].merchantName").value("Test"));

            verify(subscriptionService).getUpcomingRenewals(7);
        }

        /**
         * Test method to verify that a 400 Bad Request is returned when the input for upcoming renewals is invalid
         */
        @Test
        @DisplayName("return 400 when invalid input")
        void shouldReturn400_whenInvalidDaysAhead() throws Exception {
            when(subscriptionService.getUpcomingRenewals(7)).thenThrow(new IllegalArgumentException("Invalid input"));

            mockMvc.perform(get("/api/subscriptions/renewals?daysAhead=7")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/{id}")
    class GetSubscription {
        /**
         * Test method to verify that a subscription is retrieved successfully
         */
        @Test
        @DisplayName("return 200 and DTO in response")
        void shouldGetSubscriptionById_return200() throws Exception {
            //given
            Subscription subscription = Subscription.builder()
                    .id(1L)
                    .userId(1L)
                    .merchantName("Test")
                    .price(new BigDecimal("10.0"))
                    .build();

            //when
            when(subscriptionService.getSubscriptionById(1L)).thenReturn(Optional.of(subscription));

            //then
            mockMvc.perform(get("/api/subscriptions/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.merchantName").value("Test"));
        }

        /**
         * Test method to verify that a 404 Not Found is returned when the subscription is not found
         */
        @Test
        @DisplayName(("return 404 when subscription not found"))
        void shouldReturn404_whenSubscriptionNotFound() throws Exception {
            when(subscriptionService.getSubscriptionById(1L)).thenThrow(new SubscriptionNotFoundException("Subscription not found"));

            mockMvc.perform(get("/api/subscriptions/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        /**
         * Test method to verify that all subscriptions for a user are retrieved successfully
         */
        //TODO: needs API Gateway service implementation
        @Test
        @DisplayName("return 200 when get all subscriptions by user")
        void shouldGetUserSubscriptions_return200() throws Exception {

            //when
            when(subscriptionService.getAllSubscriptionsByUser(123L)).thenReturn(java.util.List.of());

            //then
            mockMvc.perform(get("/api/subscriptions/me")
                            .with(user("123"))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(subscriptionService).getAllSubscriptionsByUser(123L);
        }
    }

    @Nested
    @DisplayName("POST /api/subscriptions")
    class Create {
        /**
         * Test method to verify that a subscription is created successfully
         */
        @Test
        @DisplayName("return 201 and maps DTO")
        void shouldCreateSubscription_return201_andMapDto() throws Exception {
            //given
            SubscriptionCreateRequestDto dto = new SubscriptionCreateRequestDto(
                    1L, "Spotify", new BigDecimal("10.0"), "USD",
                    BillingCycle.MONTHLY, LocalDate.now().plusDays(30),
                    7, PaymentMethod.CREDIT_CARD
            );

            Subscription createdSubscription = Subscription.builder()
                    .id(12L)
                    .userId(1L)
                    .merchantName(dto.merchantName())
                    .price(dto.price())
                    .billingCycle(BillingCycle.MONTHLY)
                    .nextRenewalDate(dto.nextRenewalDate())
                    .notificationInterval(7)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .build();

            //when
            when(subscriptionService.createSubscription(any())).thenReturn(createdSubscription);

            //then
            mockMvc.perform(post("/api/subscriptions")
                            .with(csrf() )
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(12L))
                    .andExpect(jsonPath("$.merchantName").value("Spotify"));

            var captor = ArgumentCaptor.forClass(Subscription.class);
            verify(subscriptionService).createSubscription(captor.capture());
            var sent = captor.getValue();

            assertEquals(1L, sent.getUserId());
            assertEquals("Spotify", sent.getMerchantName());
            assertEquals(new BigDecimal("10.0"), sent.getPrice());
            assertEquals(BillingCycle.MONTHLY, sent.getBillingCycle());
            assertEquals(dto.nextRenewalDate(), sent.getNextRenewalDate());
            assertEquals(7, sent.getNotificationInterval());
            assertEquals(PaymentMethod.CREDIT_CARD, sent.getPaymentMethod());
        }

        /**
         * Test method to verify that a 400 Bad Request is returned when the input is invalid
         */
        @Test
        @DisplayName("return 400 when invalid input")
        void shouldReturn400_whenInvalidInput() throws Exception {
            SubscriptionCreateRequestDto dto = new SubscriptionCreateRequestDto(1L, " ", new BigDecimal("10.0"), "USD", BillingCycle.MONTHLY, LocalDate.now().plusDays(30), 7, PaymentMethod.APPLE_PAY);

            mockMvc.perform(post("/api/subscriptions")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isBadRequest());

            verifyNoInteractions(subscriptionService);
        }

        /**
         * Test method to verify that a 409 Conflict is returned when a duplicate subscription is created
         */
        @Test
        @DisplayName("return 409 when duplicate subscription")
        void shouldReturn409_whenDuplicateSubscription() throws Exception {
            // given
            SubscriptionCreateRequestDto dto = new SubscriptionCreateRequestDto(
                    1L, "Netflix", new BigDecimal("15.99"), "USD", BillingCycle.MONTHLY,
                    LocalDate.now().plusDays(30), 7, PaymentMethod.CREDIT_CARD
            );

            when(subscriptionService.createSubscription(any()))
                    .thenThrow(new DuplicateSubscriptionException("Duplicate subscription"));

            // when & then
            mockMvc.perform(post("/api/subscriptions")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isConflict())
                    .andExpect(jsonPath("$.message").value("Duplicate subscription"));
        }
    }
}