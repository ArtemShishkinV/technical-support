package com.shishkin.controller;

import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.AuthDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.exception.EmployeeNotFoundException;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.security.EmployeeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping
    public EmployeeDto auth(@RequestBody AuthDto authDto) {
        System.out.println(authDto.getUsername() + ":" + authDto.getPassword());
        Employee employee = employeeRepository.findByEmail(authDto.getUsername())
                .orElseThrow(() -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND,
                "Employee with " + authDto.getUsername() + " not found!"));
        if (authDto.getPassword().isEmpty() &&
                !passwordEncoder.encode(authDto.getPassword()).equals(employee.getPassword()))
            throw new AuthorizationServiceException("Invalid password!");
        return new EmployeeDto(employee);
    }
}
