package com.shishkin.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class AuthResponseDto {
    EmployeeDto user;
    String token;
}
