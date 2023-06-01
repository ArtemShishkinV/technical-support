package com.shishkin.controller;

import com.shishkin.dto.AuthDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.security.EmployeeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private EmployeeDetailsService employeeDetailsService;

    @PostMapping
    public EmployeeDto auth(AuthDto authDto) {
        return null;
    }
}
