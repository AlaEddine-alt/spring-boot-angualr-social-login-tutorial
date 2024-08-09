package com.demo.social.user;

import com.demo.social.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String profilePictureUrl,
        boolean emailVerified,
        boolean enabled,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String provider,
        String providerId


) {
}
