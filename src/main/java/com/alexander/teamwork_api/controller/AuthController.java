package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.AuthResponse;
import com.alexander.teamwork_api.dto.LoginRequest;
import com.alexander.teamwork_api.dto.RegisterRequest;
import com.alexander.teamwork_api.dto.UserResponse;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        String token = userService.login(request);

        return new AuthResponse(token);
    }
}