package com.keyn_bello.subscription_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull
    @Size(min = 8, max = 100, message = "Password must be between 8 or 100 symbols")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must contain at least one lowercase, one uppercase, and one digit")
    @Column(name = "password", nullable = false)
    private String password;
    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]*$",
            message = "Name must start with capital letter and contain only letters")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]*$",
            message = "Name must start with capital letter and contain only letters")
    private String lastName;
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
