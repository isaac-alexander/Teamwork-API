package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JwtService jwtService;

    @GetMapping("/token")
    public String token() {
        return jwtService.generateToken("alex@gmail.com");
    }
}