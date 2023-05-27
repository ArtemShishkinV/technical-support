package com.shishkin.dto.application;

import com.shishkin.domain.application.Application;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.Value;

import java.time.LocalDateTime;

@Value
class BasedApplicationDto {
    Long id;
    String description;
    EmployeeDto initiator;
    EmployeeDto executor;
    String status;
    String priority;
    boolean isOffline;
    LocalDateTime createdAt;
    LocalDateTime expirationAt;

    BasedApplicationDto(Application application) {
        this.id = application.getId();
        this.description = application.getDescription();
        this.initiator = new EmployeeDto(application.getInitiator());
        this.executor = new EmployeeDto(application.getExecutor());
        this.status = application.getStatus().getTitle();
        this.isOffline = application.isOffline();
        this.priority = application.getPriority().getTitle();
        this.createdAt = application.getCreatedAt();
        this.expirationAt = application.getExpirationAt();
    }
}
