package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.LoginRequest;
import com.alexander.teamwork_api.dto.RegisterRequest;
import com.alexander.teamwork_api.dto.UserResponse;
import com.alexander.teamwork_api.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    // Returns the currently authenticated user.
    User getAuthenticatedUser(
            Authentication authentication);

    // Finds a user by email.
    User getUserByEmail(String email);

    // Finds a user by ID.
    User getUserById(Long id);

    // Registers a new employee.
    UserResponse register(RegisterRequest request);

    // Authenticates a user and returns a JWT token.
    String login(LoginRequest request);

    // Creates a new employee.
    UserResponse createEmployee(RegisterRequest request);

}