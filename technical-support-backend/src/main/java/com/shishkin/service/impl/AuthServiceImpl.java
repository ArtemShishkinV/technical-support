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
// Реализация сервиса авторизации
public class AuthServiceImpl implements AuthService {
    // Сервис, реализующий работу с сотрудниками
    private final EmployeeService employeeService;
    // Объект, отвечающих за генерацию JWT
    private final JwtTokenProvider tokenProvider;
    // Объект, выполняющий авторизацию
    // проверку электронной почты и пароля
    private final AuthenticationManager authenticationManager;

    // Метод, реализующий авторизацию
    @Override
    public AuthResponseDto authorize(AuthRequestDto user) {
        // Получение работника по электронной почте
        EmployeeDto employee = employeeService.findByEmail(user.getUsername());
        // Проверка правильности электронной почты и пароля
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            // Генерация успешного токена при успешной авторизации
            String token = tokenProvider.createToken(
                    employee.getEmail(), employee.getRole());

            // Возврат DTO с токеном и информацией о работнике
            return new AuthResponseDto(employee, token);
        }

        return null;
    }
}
