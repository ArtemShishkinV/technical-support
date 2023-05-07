package com.shishkin.controller;

import com.shishkin.security.EmployeeDetails;
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
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public ResponseEntity<EmployeeDetails> getAuthenticationInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetails user = (EmployeeDetails) authentication.getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
