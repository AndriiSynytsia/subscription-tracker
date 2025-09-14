package com.keyn_bello.subscription_tracker.dto;

import com.keyn_bello.subscription_tracker.entity.User;
import jakarta.validation.constraints.NotNull;

public record AuthResponse(
        @NotNull
        String token,
        @NotNull
        UserInfo user
) {
    public static AuthResponse from(String token, User user) {
        UserInfo userInfo = new UserInfo(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getId(),
                user.getProfileImageUrl()
        );
        return new AuthResponse(token, userInfo);
    }

    public record UserInfo(
            @NotNull
            String email,
            @NotNull
            String firstName,
            @NotNull
            String lastName,
            @NotNull
            Long userId,
            String profileImageUrl
    ) {
    }
}
