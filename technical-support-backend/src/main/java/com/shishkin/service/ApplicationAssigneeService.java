package com.shishkin.service;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.application.ApplicationCreatedDto;

public interface ApplicationAssigneeService {
    Employee getExecutor(ApplicationCreatedDto applicationCreatedDto);
}
