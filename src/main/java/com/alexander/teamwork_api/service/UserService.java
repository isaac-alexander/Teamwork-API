package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.LoginRequest;
import com.alexander.teamwork_api.dto.RegisterRequest;
import com.alexander.teamwork_api.dto.UserResponse;
import com.alexander.teamwork_api.entity.Role;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.exception.InvalidCredentialsException;
import com.alexander.teamwork_api.exception.UserNotFoundException;
import com.alexander.teamwork_api.mapper.UserMapper;
import com.alexander.teamwork_api.repository.UserRepository;
import com.alexander.teamwork_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponse register(RegisterRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();

        User savedUser = userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }

    public UserResponse createEmployee(RegisterRequest request) {

        User employee = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();

        User savedEmployee = userRepository.save(employee);

        return UserMapper.toUserResponse(savedEmployee);
    }

}