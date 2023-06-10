package com.shishkin.dto.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class EmployeeCreateModelsDto {
    List<Role> roles;
    List<DepartmentDto> departments;
}
