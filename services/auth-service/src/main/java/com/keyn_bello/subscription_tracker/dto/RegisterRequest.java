package com.keyn_bello.subscription_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 or 100 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
                message = "Password must contain at least one lowercase, one uppercase, one digit and one special character")
        String password,
        @NotBlank(message = "First name is required")
        @Pattern(regexp = "^[A-Z][a-z]*$",
                message = "First name must start with capital letter and contain only letters")
        String firstName,
        @NotBlank(message = "Last name is required")
        @Pattern(regexp = "^[A-Z][a-z]*$",
                message = "Last name must start with capital letter and contain only letters")
        String lastName
) {
}
