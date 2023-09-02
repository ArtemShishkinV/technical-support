package com.shishkin.controller;

import com.shishkin.dto.auth.AuthRequestDto;
import com.shishkin.dto.auth.AuthResponseDto;
import com.shishkin.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// URL, обрабатываемые контроллером
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    // Сервис для осуществления авторизации
    private final AuthService authService;

    // Реализует авторизацию пользователя
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto user) {
        log.info("UserResourceImpl : authenticate");
        log.info(user.getUsername() + ":" + user.getPassword());
        AuthResponseDto authResponseDto = authService.authorize(user);
        if (authResponseDto == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(authResponseDto);
    }
}
