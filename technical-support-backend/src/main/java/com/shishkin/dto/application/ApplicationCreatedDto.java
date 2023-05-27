package com.shishkin.dto.application;

import com.shishkin.dto.employee.EmployeeDto;
import lombok.Value;

@Value
public class ApplicationCreatedDto {
      EmployeeDto initiator;
      String description;
      String category;
      String type;
      String priority;
      boolean isOffline;
      ApplicationObjectDto applicationObjectDto;
}
