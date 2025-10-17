package com.keyn_bello.subscription_tracker.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void shouldCreateUserWithBuilder() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .isActive(true)
                .build();

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getUsername()).isEqualTo("test@example.com");
        assertThat(user.isEnabled()).isTrue();
        assertThat(user.isAccountNonExpired()).isTrue();
        assertThat(user.isAccountNonLocked()).isTrue();
        assertThat(user.isCredentialsNonExpired()).isTrue();
        assertThat(user.getAuthorities()).isEmpty();
    }

    @Test
    void shouldSetTimestampsOnPersist() {
        User user = new User();
        user.onCreate();

        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getUpdatedAt()).isNotNull();
    }

    @Test
    void shouldUpdateTimestampOnUpdate() {
        User user = new User();
        user.onUpdate();

        assertThat(user.getUpdatedAt()).isNotNull();
    }
}
