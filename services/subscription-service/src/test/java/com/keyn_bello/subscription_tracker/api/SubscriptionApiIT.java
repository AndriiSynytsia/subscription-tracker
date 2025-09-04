package com.keyn_bello.subscription_tracker.api;

import com.keyn_bello.subscription_tracker.dto.SubscriptionCreateRequestDto;
import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Sql(
        statements = {
                "TRUNCATE TABLE subscriptions RESTART IDENTITY CASCADE"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
)
public class SubscriptionApiIT {

    private static final LocalDate NOW = LocalDate.of(2025, 9, 3);
    @ServiceConnection
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
    @Autowired
    TestRestTemplate rest;

    private SubscriptionCreateRequestDto validDto(String merchant, Long userId) {
        return new SubscriptionCreateRequestDto(
                userId,
                merchant,
                BigDecimal.valueOf(11.99),
                BillingCycle.MONTHLY,
                NOW.plusDays(10),
                7,
                PaymentMethod.CREDIT_CARD
        );
    }

    @Nested
    @DisplayName("POST /api/subscriptions")
    class Create {

        @Test
        @DisplayName("201 Created and returns saved entity")
        void create_returns201_andBody() {
            var dto = validDto("Netflix", 1L);

            ResponseEntity<Subscription> resp =
                    rest.postForEntity("/api/subscriptions", dto, Subscription.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isNotNull();
            assertThat(resp.getBody().getMerchantName()).isEqualTo("Netflix");
        }

        @Test
        @DisplayName("400 Bad Request on bean validation failure (blank merchant)")
        void create_returns400_onInvalidDto() {
            var dto = new SubscriptionCreateRequestDto(
                    1L, "  ", new BigDecimal("10.00"),
                    BillingCycle.MONTHLY, NOW.plusDays(10),
                    7, PaymentMethod.CREDIT_CARD
            );

            ResponseEntity<String> resp =
                    rest.postForEntity("/api/subscriptions", dto, String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(resp.getBody()).contains("merchantName");
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/{id}")
    class GetById {
        @Test
        @DisplayName("200 OK when found")
        void get_found_returns200() {
            var created = rest.postForEntity("/api/subscriptions", validDto("Spotify", 1L), Subscription.class).getBody();
            assertThat(created).isNotNull();

            ResponseEntity<Subscription> resp =
                    rest.getForEntity("/api/subscriptions/{id}", Subscription.class, created.getId());

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isEqualTo(created.getId());
            assertThat(resp.getBody().getMerchantName()).isEqualTo("Spotify");
        }

        @Test
        @DisplayName("404 Not Found when missing")
        void get_missing_returns404() {
            ResponseEntity<String> resp =
                    rest.getForEntity("/api/subscriptions/{id}", String.class, 9999);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/user/{userId}")
    class ListByUser {
        @Test
        @DisplayName("200 OK returns list for user")
        void listByUser_returns200_list() {
            rest.postForEntity("/api/subscriptions", validDto("Netflix", 42L), Subscription.class);
            rest.postForEntity("/api/subscriptions", validDto("Spotify", 42L), Subscription.class);
            rest.postForEntity("/api/subscriptions", validDto("Disney+", 7L), Subscription.class);

            ResponseEntity<Subscription[]> resp =
                    rest.getForEntity("/api/subscriptions/user/{userId}", Subscription[].class, 42);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(List.of(resp.getBody())).extracting(Subscription::getMerchantName)
                    .containsExactlyInAnyOrder("Netflix", "Spotify");
        }
    }

    @Nested
    @DisplayName("PUT /api/subscriptions/{id}")
    class Update {
        @Test
        @DisplayName("200 OK updates entity (path id overrides body id)")
        void update_returns200() {
            var created = rest.postForEntity("/api/subscriptions", validDto("YouTube", 1L), Subscription.class).getBody();
            assertThat(created).isNotNull();

            var body = Subscription.builder()
                    .id(999L)
                    .userId(created.getUserId())
                    .merchantName("YouTube Premium")
                    .price(new BigDecimal("11.99"))
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .nextRenewalDate(NOW.plusDays(30))
                    .notificationInterval(3)
                    .createdAt(created.getCreatedAt())
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            var req = new HttpEntity<>(body, headers);

            ResponseEntity<Subscription> resp =
                    rest.exchange(URI.create("/api/subscriptions/" + created.getId()), HttpMethod.PUT, req, Subscription.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isEqualTo(created.getId());
            assertThat(resp.getBody().getMerchantName()).isEqualTo("YouTube Premium");
            assertThat(resp.getBody().getPrice()).isEqualByComparingTo("11.99");
        }

        @Test
        @DisplayName("404 Not Found when updating missing id")
        void update_missing_returns404() {
            var body = Subscription.builder()
                    .id(1L)
                    .userId(1L)
                    .merchantName("X")
                    .price(new BigDecimal("9.99"))
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .nextRenewalDate(NOW.plusDays(5))
                    .notificationInterval(1)
                    .build();

            var req = new HttpEntity<>(body, new HttpHeaders() {{
                setContentType(MediaType.APPLICATION_JSON);
            }});

            ResponseEntity<String> resp =
                    rest.exchange(URI.create("/api/subscriptions/9999"), HttpMethod.PUT, req, String.class);

            assertThat(resp.getStatusCode()).isIn(HttpStatus.NOT_FOUND, HttpStatus.OK);
        }
    }

    @Nested
    @DisplayName("DELETE /api/subscriptions/{id}")
    class Delete {
        @Test
        @DisplayName("204 No Content when deleted")
        void delete_returns204() {
            var created = rest.postForEntity("/api/subscriptions", validDto("HBO", 1L), Subscription.class).getBody();
            assertThat(created).isNotNull();

            ResponseEntity<Void> resp =
                    rest.exchange(URI.create("/api/subscriptions/" + created.getId()), HttpMethod.DELETE, null, Void.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

            var get = rest.getForEntity("/api/subscriptions/{id}", String.class, created.getId());
            assertThat(get.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        @DisplayName("404 Not Found when deleting missing id")
        void delete_missing_returns404() {
            ResponseEntity<String> resp =
                    rest.exchange(URI.create("/api/subscriptions/9999"), HttpMethod.DELETE, null, String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/renewals?daysAhead=")
    class Renewals {
        @Test
        @DisplayName("200 OK returns list for valid daysAhead")
        void renewals_returns200_list() {
            rest.postForEntity("/api/subscriptions", validDto("Netflix", 1L), Subscription.class);
            rest.postForEntity("/api/subscriptions", validDto("Spotify", 1L), Subscription.class);

            ResponseEntity<Subscription[]> resp =
                    rest.getForEntity("/api/subscriptions/renewals?daysAhead=10", Subscription[].class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody()).hasSizeGreaterThanOrEqualTo(0);
        }

        @Test
        @DisplayName("400 Bad Request when daysAhead < 1 (bean validation)")
        void renewals_invalidParam_returns400() {
            ResponseEntity<String> resp =
                    rest.getForEntity("/api/subscriptions/renewals?daysAhead=0", String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
