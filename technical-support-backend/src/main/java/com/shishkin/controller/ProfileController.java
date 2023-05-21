package com.shishkin.controller;

import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.security.EmployeeDetails;
import com.shishkin.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<EmployeeDto> getAuthenticationInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetails user = (EmployeeDetails) authentication.getPrincipal();
        EmployeeDto employeeDto = new EmployeeDto(user.employee());
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

}
