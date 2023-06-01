package com.shishkin.controller;

import com.shishkin.config.JwtTokenProvider;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.AuthDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.exception.EmployeeNotFoundException;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.security.EmployeeDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody AuthDto user) {
        log.info("UserResourceImpl : authenticate");
        log.info(user.getUsername() + ":" + user.getPassword());
        JSONObject jsonObject = new JSONObject();
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String email = user.getUsername();
                jsonObject.put("name", authentication.getName());
                jsonObject.put("authorities", authentication.getAuthorities());
                jsonObject.put("token", tokenProvider.createToken(email, employeeRepository.findByEmail(email).get().getRole()));
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
            }
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
        return null;
    }

//    public EmployeeDto auth(@RequestBody AuthDto authDto) {
//        System.out.println(authDto.getUsername() + ":" + authDto.getPassword());
//        Employee employee = employeeRepository.findByEmail(authDto.getUsername())
//                .orElseThrow(() -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND,
//                "Employee with " + authDto.getUsername() + " not found!"));
//        if (authDto.getPassword().isEmpty() &&
//                !passwordEncoder.encode(authDto.getPassword()).equals(employee.getPassword()))
//            throw new AuthorizationServiceException("Invalid password!");
//        return new EmployeeDto(employee);
//    }
}
