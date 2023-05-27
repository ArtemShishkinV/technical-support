package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.Priority;
import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Office;
import com.shishkin.domain.employee.Role;
import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.OfficeRepository;
import com.shishkin.service.ApplicationAssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
        var supportEmployment = supports.stream()
                .collect(Collectors.toMap(Function.identity(), employee -> calcEmployment(employee.getApplicationsExecutor())));
        var minEmployement = supportEmployment.values().stream().min(Integer::compareTo);

        return null;
    }

    private List<Employee> getSupports(ApplicationCreatedDto application) {
        var supports =
                employeeRepository.findAllByRoleAndOnline(
                        Role.SUPPORT,
                        "Критический".equals(application.getPriority())
                );
        if (application.isOffline()) supports =
                supports.stream()
                        .filter(employee -> filterByOffice(employee, application))
                        .collect(Collectors.toList());
        return supports;
    }

    private int calcEmployment(Set<Application> applicationSet) {
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
