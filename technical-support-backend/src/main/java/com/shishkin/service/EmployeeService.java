package com.shishkin.service;

import com.shishkin.domain.employee.Employee;
import com.shishkin.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id) throws EmployeeNotFoundException;

    Employee create(Employee employee);

    void delete(long id) throws EmployeeNotFoundException;
}
