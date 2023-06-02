package com.shishkin.service.impl;

import com.shishkin.config.jwt.JwtTokenProvider;
import com.shishkin.dto.auth.AuthRequestDto;
import com.shishkin.dto.auth.AuthResponseDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.service.AuthService;
import com.shishkin.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final EmployeeService employeeService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto authorize(AuthRequestDto user) {
        EmployeeDto employee = employeeService.findByEmail(user.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = tokenProvider.createToken(
                    employee.getEmail(), employee.getRole());
            return new AuthResponseDto(employee, token);
        }

        return null;
    }
}
