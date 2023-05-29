package com.shishkin.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class DeviceDto {
    Long id;
    String title;
    String description;
    String type;
    String condition;
    EmployeeDto owner;
}
