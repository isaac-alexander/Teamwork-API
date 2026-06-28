package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.UserResponse;
import com.alexander.teamwork_api.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    // Converts User entity into a UserResponse DTO.
    public static UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}