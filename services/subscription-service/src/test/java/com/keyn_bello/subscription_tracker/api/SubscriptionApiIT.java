package com.keyn_bello.subscription_tracker.api;

import com.keyn_bello.subscription_tracker.dto.SubscriptionCreateRequestDto;
import com.keyn_bello.subscription_tracker.dto.SubscriptionResponseDto;
import com.keyn_bello.subscription_tracker.entity.BillingCycle;
import com.keyn_bello.subscription_tracker.entity.PaymentMethod;
import com.keyn_bello.subscription_tracker.entity.Subscription;
import com.keyn_bello.subscription_tracker.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
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

    private static final LocalDate NOW = LocalDate.now();
    @ServiceConnection
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
    @Autowired
    TestRestTemplate rest;

    @Autowired
    private JwtUtil jwtUtil;

    private String createTestToken(Long userId) {
        return jwtUtil.generateToken(userId.toString());
    }


    private SubscriptionCreateRequestDto validDto(String merchant, Long userId) {
        return new SubscriptionCreateRequestDto(
                userId,
                merchant,
                BigDecimal.valueOf(11.99),
                "USD",
                BillingCycle.MONTHLY,
                NOW.plusDays(10),
                7,
                PaymentMethod.CREDIT_CARD
        );
    }

    private @NotNull HttpHeaders createAuthHeaders(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(createTestToken(userId));
        return headers;
    }

    @Nested
    @DisplayName("POST /api/subscriptions")
    class Create {

        @Test
        @DisplayName("201 Created and returns saved entity")
        void create_returns201_andBody() {
            HttpHeaders headers = createAuthHeaders(1L);

            ResponseEntity<Subscription> resp =
                    rest.exchange("/api/subscriptions",
                            HttpMethod.POST,
                            new HttpEntity<>(validDto("Netflix", 1L), headers),
                            Subscription.class
                    );

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isNotNull();
            assertThat(resp.getBody().getMerchantName()).isEqualTo("Netflix");
        }

        @Test
        @DisplayName("403 FORBIDDEN without token")
        void create_returns401_withoutAuth() {
            var dto = validDto("Netflix", 1L);
            ResponseEntity<String> resp = rest.postForEntity("/api/subscriptions", dto, String.class);
            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        }


        @Test
        @DisplayName("400 Bad Request on bean validation failure (blank merchant)")
        void create_returns400_onInvalidDto() {
            var dto = new SubscriptionCreateRequestDto(
                    1L, "  ", new BigDecimal("10.00"), "USD",
                    BillingCycle.MONTHLY, LocalDate.now().plusDays(30),
                    7, PaymentMethod.CREDIT_CARD
            );

            HttpHeaders headers = createAuthHeaders(1L);

            ResponseEntity<String> resp =
                    rest.exchange("/api/subscriptions",
                            HttpMethod.POST,
                            new HttpEntity<>(dto, headers),
                            String.class
                    );

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody()).contains("errors");

        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/{id}")
    class GetById {
        @Test
        @DisplayName("200 OK when found")
        void get_found_returns200() {
            var dto = new SubscriptionCreateRequestDto(
                    1L,
                    "Spotify",
                    BigDecimal.valueOf(11.99),
                    "USD",
                    BillingCycle.MONTHLY,
                    LocalDate.now().plusDays(30),
                    7,
                    PaymentMethod.CREDIT_CARD
            );

            HttpHeaders headers = createAuthHeaders(1L);

            var created = rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(dto,
                            headers),
                    Subscription.class).getBody();
            assertThat(created).isNotNull();


            ResponseEntity<Subscription> resp =
                    rest.exchange("/api/subscriptions/{id}",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            Subscription.class,
                            created.getId());

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isEqualTo(created.getId());
            assertThat(resp.getBody().getMerchantName()).isEqualTo("Spotify");
        }

        @Test
        @DisplayName("403 Forbidden when accessing other user's subscription")
        void get_returns403_wrongUser() {
            var headersUser = createAuthHeaders(1L);
            var headers = createAuthHeaders(2L); // Different user
            var created = rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Netflix", 1L), headersUser),
                    Subscription.class).getBody();

            ResponseEntity<String> resp = rest.exchange("/api/subscriptions/" + (created != null ? created.getId() : null),
                    HttpMethod.GET, new HttpEntity<>(headers), String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        }

        @Test
        @DisplayName("404 Not Found when missing")
        void get_missing_returns404() {
            HttpHeaders headers = createAuthHeaders(1L);

            ResponseEntity<String> resp =
                    rest.exchange("/api/subscriptions/{id}",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            String.class,
                            9999);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/me")
    class ListByUser {
        @Test
        @DisplayName("200 OK returns list for user")
        void listByUser_returns200_list() {
            HttpHeaders headers = createAuthHeaders(42L);

            rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Netflix", 42L), headers),
                    Subscription.class);
            rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Spotify", 42L), headers),
                    Subscription.class);
            rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Disney+", 42L), headers),
                    Subscription.class);

            ResponseEntity<List<SubscriptionResponseDto>> resp =
                    rest.exchange("/api/subscriptions/me", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<SubscriptionResponseDto>>() {
                    });

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody()).extracting(SubscriptionResponseDto::merchantName)
                    .containsExactlyInAnyOrder("Netflix", "Spotify", "Disney+");
        }
    }

    @Nested
    @DisplayName("PUT /api/subscriptions/{id}")
    class Update {
        @Test
        @DisplayName("200 OK updates entity (path id overrides body id)")
        void update_returns200() {
            HttpHeaders headers = createAuthHeaders(42L);

            var created = rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("YouTube", 42L), headers),
                    Subscription.class).getBody();
            assertThat(created).isNotNull();

            var body = Subscription.builder()
                    .id(999L)
                    .userId(created.getUserId())
                    .merchantName("YouTube Premium")
                    .price(new BigDecimal("11.99"))
                    .currency("USD")
                    .billingCycle(BillingCycle.MONTHLY)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .nextRenewalDate(NOW.plusDays(30))
                    .notificationInterval(3)
                    .createdAt(created.getCreatedAt())
                    .build();

            ResponseEntity<Subscription> resp =
                    rest.exchange("/api/subscriptions/" + created.getId(),
                            HttpMethod.PUT,
                            new HttpEntity<>(body, headers),
                            Subscription.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody().getId()).isEqualTo(created.getId());
            assertThat(resp.getBody().getMerchantName()).isEqualTo("YouTube Premium");
            assertThat(resp.getBody().getPrice()).isEqualByComparingTo("11.99");
        }

        @Test
        @DisplayName("404 Not Found when updating missing id")
        void update_missing_returns404() {
            HttpHeaders headers = createAuthHeaders(1L);

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

            var req = new HttpEntity<>(body, headers);

            ResponseEntity<String> resp =
                    rest.exchange("/api/subscriptions/9999",
                            HttpMethod.PUT,
                            req,
                            String.class);

            assertThat(resp.getStatusCode()).isIn(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("DELETE /api/subscriptions/{id}")
    class Delete {
        @Test
        @DisplayName("204 No Content when deleted")
        void delete_returns204() {
            HttpHeaders headers = createAuthHeaders(1L);

            var created = rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("HBO", 1L), headers),
                    Subscription.class).getBody();
            assertThat(created).isNotNull();

            ResponseEntity<Void> resp =
                    rest.exchange(URI.create("/api/subscriptions/" + created.getId()),
                            HttpMethod.DELETE,
                            new HttpEntity<>(headers),
                            Void.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        }

        @Test
        @DisplayName("404 Not Found when deleting missing id")
        void delete_missing_returns404() {
            HttpHeaders headers = createAuthHeaders(1L);

            ResponseEntity<String> resp =
                    rest.exchange("/api/subscriptions/9999",
                            HttpMethod.DELETE,
                            new HttpEntity<>(headers),
                            String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("GET /api/subscriptions/renewals?daysAhead=")
    class Renewals {
        @Test
        @DisplayName("200 OK returns list for valid daysAhead")
        void renewals_returns200_list() {
            HttpHeaders headers = createAuthHeaders(1L);
            rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Netflix", 1L), headers),
                    Subscription.class);
            rest.exchange("/api/subscriptions",
                    HttpMethod.POST,
                    new HttpEntity<>(validDto("Spotify", 1L), headers),
                    Subscription.class);

            ResponseEntity<List<SubscriptionResponseDto>> resp =
                    rest.exchange("/api/subscriptions/renewals?daysAhead=10",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            new ParameterizedTypeReference<List<SubscriptionResponseDto>>() {
                            });

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(resp.getBody()).isNotNull();
            assertThat(resp.getBody()).hasSizeGreaterThanOrEqualTo(0);
        }

        @Test
        @DisplayName("400 Bad Request when daysAhead < 1 (bean validation)")
        void renewals_invalidParam_returns400() {
            HttpHeaders headers = createAuthHeaders(1L);

            ResponseEntity<String> resp =
                    rest.exchange("/api/subscriptions/renewals?daysAhead=0",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            String.class);

            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
