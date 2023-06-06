package com.shishkin.service;

import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.application.ApplicationCreateRequestDto;

public interface ApplicationAssigneeService {
    Employee getExecutor(ApplicationCreateRequestDto applicationCreateRequestDto);
}
