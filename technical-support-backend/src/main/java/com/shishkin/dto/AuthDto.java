package com.shishkin.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class AuthDto {
    String email;
    String password;
}
