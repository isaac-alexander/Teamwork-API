package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.RegisterRequest;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/employees")
    public User createEmployee(
            @RequestBody RegisterRequest request) {

        return userService.createEmployee(request);
    }
}