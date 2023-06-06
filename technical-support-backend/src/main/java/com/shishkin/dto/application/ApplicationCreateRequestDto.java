package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationCreateRequestDto {
      EmployeeDto initiator;
      String description;
      String category;
      String type;
      String priority;
      boolean isOffline;
      Long applicationObjectId;
}
