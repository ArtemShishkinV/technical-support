package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.application.base.Application;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class BasedApplicationDto {
    Long id;
    String description;
    String solution;
    EmployeeDto initiator;
    EmployeeDto executor;
    String status;
    String priority;
    boolean isOffline;
    LocalDateTime createdAt;
    LocalDateTime solvedAt;

    public BasedApplicationDto(Application application) {
        this.id = application.getId();
        this.description = application.getDescription();
        this.initiator = new EmployeeDto(application.getInitiator());
        this.executor = new EmployeeDto(application.getExecutor());
        this.status = application.getStatus().getTitle();
        this.isOffline = application.isOffline();
        this.priority = application.getPriority().getTitle();
        this.createdAt = application.getCreatedAt();
        this.solvedAt = application.getSolvedAt();
        this.solution = application.getSolution();
    }
}
