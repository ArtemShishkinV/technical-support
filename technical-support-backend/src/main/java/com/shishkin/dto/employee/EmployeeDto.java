package com.shishkin.dto.employee;

import com.shishkin.domain.employee.Employee;
import lombok.Value;

import java.time.LocalDate;

@Value
public class EmployeeDto {
    Long staffNumber;
    String login;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthDay;
    String phoneNumber;
    boolean isOnline;
    String department;
    String role;
    String post;
    WorkplaceDto workplace;
    OfficeDto officeDto;

    public EmployeeDto(Employee employee) {
        this.staffNumber = employee.getStaffNumber();
        this.login = employee.getLogin();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.middleName = employee.getMiddleName();
        this.email = employee.getEmail();
        this.birthDay = employee.getBirthDay();
        this.phoneNumber = employee.getPhoneNumber();
        this.isOnline = employee.isOnline();
        this.department = employee.getDepartment().getTitle();
        this.post = employee.getJobPost().getTitle();
        this.role = employee.getRole().getCode();
        this.workplace = new WorkplaceDto(employee.getWorkplace());
        this.officeDto = new OfficeDto(employee.getWorkplace().getOffice());
    }
}
