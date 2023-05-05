package com.shishkin.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {
    public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect password for user with login: %s";
    private final EmployeeDetailsService detailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = detailsService.loadUserByUsername(authentication.getName());
        String actualPassword = authentication.getCredentials().toString();

        if (!userDetails.getPassword().equals(actualPassword)) {
            throw new BadCredentialsException(String.format(INCORRECT_PASSWORD_MESSAGE, userDetails.getUsername()));
        }

        return new UsernamePasswordAuthenticationToken(userDetails, actualPassword, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
