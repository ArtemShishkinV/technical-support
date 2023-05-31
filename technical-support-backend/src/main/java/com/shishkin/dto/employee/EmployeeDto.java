package com.shishkin.dto.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.Department;
import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.JobPost;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class EmployeeDto {
    Long staffNumber;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthDay;
    String phoneNumber;
    boolean isOnline;
    String role;
    DepartmentDto department;
    JobPostDto post;
    WorkplaceDto workplace;
    OfficeDto officeDto;

    public EmployeeDto(Employee employee) {
        this.staffNumber = employee.getStaffNumber();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.middleName = employee.getMiddleName();
        this.email = employee.getEmail();
        this.birthDay = employee.getBirthDay();
        this.phoneNumber = employee.getPhoneNumber();
        this.isOnline = employee.isOnline();
        this.role = employee.getRole().getCode();
        this.department = new DepartmentDto(employee.getDepartment());
        this.post = new JobPostDto(employee.getJobPost());
        this.workplace = new WorkplaceDto(employee.getWorkplace());
        this.officeDto = new OfficeDto(employee.getWorkplace().getOffice());
    }
}
