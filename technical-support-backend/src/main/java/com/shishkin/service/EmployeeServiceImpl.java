package com.shishkin.service;

import com.shishkin.domain.Employee;
import com.shishkin.exception.EmployeeNotFoundException;
import com.shishkin.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) throws EmployeeNotFoundException {
        log.debug("#find employee with id: " + id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "Employee with id " + id + " not found!"));
    }

    @Override
    public Employee create(Employee employee) {
        log.debug("#create employee " + employee);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        log.debug("#delete employee with id: " + id);
        Employee employee = this.findById(id);
        employeeRepository.delete(employee);
    }
}
