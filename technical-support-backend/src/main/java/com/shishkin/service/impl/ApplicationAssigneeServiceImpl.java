package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.Priority;
import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Office;
import com.shishkin.domain.employee.Role;
import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.exception.EmployeeNotFoundException;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.OfficeRepository;
import com.shishkin.service.ApplicationAssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationAssigneeServiceImpl implements ApplicationAssigneeService {
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public Employee getExecutor(ApplicationCreatedDto application) {
        var supports = getSupports(application);
        supports.forEach(employee -> System.out.println(employee.getStaffNumber()));
        var supportEmployment = supports.stream()
                .collect(Collectors.toMap(Function.identity(),
                        employee -> calcEmployment(employee.getApplicationsExecutor())));
        var minEmploymentSupports = getSupportsWithMinEmployment(supportEmployment);
        return getExecutorToApplication(minEmploymentSupports, application);
    }

    private Employee getExecutorToApplication(List<Employee> employees, ApplicationCreatedDto application) {
        if (employees.size() == 0)
            throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND,
                    "Support specialist for " + application + " was not assignee!");

        if ("Критический".equals(application.getPriority())) {
            List<Employee> filteredEmployees = employees.stream().filter(Employee::isOnline).toList();
            if (!filteredEmployees.isEmpty()) employees = filteredEmployees;
        }

        if (!application.isOffline())
            return employees.stream().filter(employee -> !filterByOffice(employee, application))
                    .findFirst().orElse(employees.get(0));

        return employees.get(0);
    }

    private List<Employee> getSupportsWithMinEmployment(Map<Employee, Integer> supportEmployment) {
        var minEmployment = supportEmployment.values().stream().min(Integer::compareTo);
        return supportEmployment.entrySet().stream()
                .filter(entry -> entry.getValue().equals(minEmployment.get()))
                .map(Map.Entry::getKey)
                .toList();
    }


    private List<Employee> getSupports(ApplicationCreatedDto application) {
        var supports = employeeRepository.findAllByRole(Role.SUPPORT);
        if (application.isOffline()) supports =
                supports.stream()
                        .filter(employee -> filterByOffice(employee, application))
                        .collect(Collectors.toList());
        return supports;
    }

    private int calcEmployment(List<Application> applicationSet) {
        return applicationSet.stream()
                .map(Application::getPriority)
                .map(Priority::getPoints)
                .reduce(0, Integer::sum);
    }

    private boolean filterByOffice(Employee executor, ApplicationCreatedDto applicationCreatedDto) {
        Office office = officeRepository.getById(applicationCreatedDto.getInitiator().getOfficeDto().getId());
        return executor.getWorkplace().getOffice().equals(office);
    }

}
