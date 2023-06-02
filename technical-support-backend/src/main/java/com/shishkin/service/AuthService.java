package com.shishkin.service;

import com.shishkin.dto.auth.AuthRequestDto;
import com.shishkin.dto.auth.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authorize(AuthRequestDto user);
}
