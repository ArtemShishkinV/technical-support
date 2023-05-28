package com.shishkin.dto.employee;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.Department;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class DepartmentDto {
    Long id;
    String title;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.title = department.getTitle();
    }
}
