package com.shishkin.service.impl.application;

import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.application.base.Priority;
import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Office;
import com.shishkin.domain.employee.Role;
import com.shishkin.dto.application.ApplicationCreateRequestDto;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.OfficeRepository;
import com.shishkin.service.ApplicationAssigneeService;
import lombok.AllArgsConstructor;
import lombok.val;
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

    // Получение исполнителя заявки
    @Override
    public Employee getExecutor(ApplicationCreateRequestDto application) {
        var supports = employeeRepository.findAllByRole(Role.SUPPORT);
        var supportEmployment = supports.stream()
                .collect(Collectors.toMap(Function.identity(),
                        employee -> calcEmployment(employee.getApplicationsExecutor())));
        var minEmploymentSupports = getSupportsWithMinEmployment(supportEmployment);

        return getExecutorToApplication(minEmploymentSupports, application);
    }

    //
    private Employee getExecutorToApplication(List<Employee> employees, ApplicationCreateRequestDto application) {
        if (employees.size() == 1)
            return employees.get(0);

        var supports = getSupportByOfflineCondition(application, employees);
        return getSupportsByApplicationPriority(supports, application.getPriority()).get(0);
    }

    // Получение списка сотрудников, зависящего от приоритета заявки
    private List<Employee> getSupportsByApplicationPriority(List<Employee> employees,
                                                            String applicationPriority) {
        if ("Критический".equals(applicationPriority)) {
            List<Employee> filteredEmployees = employees.stream().filter(Employee::isOnline).toList();
            if (!filteredEmployees.isEmpty()) employees = filteredEmployees;
        }
        return employees;
    }

    // Получение списка сотрудников с минимальной занятостью
    private List<Employee> getSupportsWithMinEmployment(Map<Employee, Integer> supportEmployment) {
        var minEmployment = supportEmployment.values().stream().min(Integer::compareTo);
        return supportEmployment.entrySet().stream()
                .filter(entry -> entry.getValue().equals(minEmployment.get()))
                .map(Map.Entry::getKey)
                .toList();
    }

    // Получение только тех специалистов, которые удовлетворяют требованию по офлайн решению,
    // если такие не найдены, то возвращается исходный список
    private List<Employee> getSupportByOfflineCondition(ApplicationCreateRequestDto application, List<Employee> employees) {
        //Разделяем специалистов на 2 группы, тех кто в том же офисе, что и инициатор заявки
        // и тех, кто в других офисах
        val supports = employees.stream()
                .collect(Collectors.partitioningBy(employee -> filterByOffice(employee, application)));
        List<Employee> offlineSupports;
        if (application.isOffline()) {
            offlineSupports = supports.get(true);
        } else {
            offlineSupports = supports.get(false);
        }
        return offlineSupports.isEmpty() ? employees : offlineSupports;
    }

    //Вычисление занятости сотрудник по его активным заявкам
    private int calcEmployment(List<Application> applicationSet) {
        return applicationSet.stream()
                .map(Application::getPriority)
                .map(Priority::getPoints)
                .reduce(0, Integer::sum);
    }

    //Фильтр для отбора специалистов из того же офиса, что и инициатор заявки
    private boolean filterByOffice(Employee executor, ApplicationCreateRequestDto applicationCreateRequestDto) {
        Office office = officeRepository.getById(applicationCreateRequestDto.getInitiator().getOfficeDto().getId());
        return executor.getWorkplace().getOffice().equals(office);
    }

}
