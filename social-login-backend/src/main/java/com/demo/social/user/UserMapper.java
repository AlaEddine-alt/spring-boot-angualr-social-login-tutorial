package com.demo.social.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public UserDto toUserDto( User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getProfilePictureUrl(),
                user.isEmailVerified(),
                user.isEnabled(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getProvider(),
                user.getProviderId()
        );
    }
}
